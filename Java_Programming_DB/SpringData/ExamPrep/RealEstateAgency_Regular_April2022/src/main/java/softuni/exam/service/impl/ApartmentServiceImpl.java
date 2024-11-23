package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.apartment.ApartmentImportDTO;
import softuni.exam.models.dto.apartment.ApartmentImportWrapperDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.models.entity.enums.ApartmentTypeEnum;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.util.validation.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

import static softuni.exam.constants.Messages.*;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/apartments.xml");
    private final StringBuilder sb;
    private final ApartmentRepository apartmentRepository;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;

    public ApartmentServiceImpl(
            ApartmentRepository apartmentRepository,
            XmlParser xmlParser,
            ValidationUtils validationUtils,
            TownRepository townRepository,
            ModelMapper modelMapper
    ) {
        this.xmlParser = xmlParser;
        this.sb = new StringBuilder();
        this.apartmentRepository = apartmentRepository;
        this.validationUtils = validationUtils;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        ApartmentImportWrapperDTO apartmentImportWrapperDTO = xmlParser.fromFile(FILE_PATH.toFile(), ApartmentImportWrapperDTO.class);
        List<ApartmentImportDTO> apartmentImportDTOS = apartmentImportWrapperDTO.getApartments();

        for (ApartmentImportDTO dto : apartmentImportDTOS) {
            Town town = townRepository.findFirstByTownName(dto.getTown()).orElseThrow(NoSuchElementException::new);
            if (!validationUtils.isValid(dto) ||
                    apartmentRepository.findFirstByTownAndArea(town, dto.getArea()).isPresent()
            ) {
                sb.append(String.format(INVALID_ENTITY, APARTMENT)).append(System.lineSeparator());
                continue;
            }

            Apartment apartment = modelMapper.map(dto, Apartment.class);
            ApartmentTypeEnum apartmentType = ApartmentTypeEnum.valueOf(dto.getApartmentType().toUpperCase());
            apartment.setApartmentType(apartmentType);
            apartment.setTown(town);
            apartmentRepository.saveAndFlush(apartment);
            sb.append(String.format(SUCCESSFUL_IMPORT, APARTMENT, dto.getApartmentType(), String.format("%.2f", apartment.getArea())))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
