package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonalDataImportDTO;
import softuni.exam.models.dto.PersonalDataWrapperDTO;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.service.PersonalDataService;
import softuni.exam.util.validation.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//ToDo - Implement all the methods

@Service
public class PersonalDataServiceImpl implements PersonalDataService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/personal_data.xml");
    private final PersonalDataRepository personalDataRepository;
    private final StringBuilder sb;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;

    public PersonalDataServiceImpl(
            PersonalDataRepository personalDataRepository,
            XmlParser xmlParser,
            ValidationUtils validationUtils,
            ModelMapper modelMapper
    ) {
        this.sb = new StringBuilder();
        this.personalDataRepository = personalDataRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return personalDataRepository.count() > 0;
    }

    @Override
    public String readPersonalDataFileContent() throws IOException {

        return Files.readString(FILE_PATH);
    }

    @Override
    public String importPersonalData() throws JAXBException, FileNotFoundException {
        PersonalDataWrapperDTO personalDataWrapperDTO = xmlParser.fromFile(FILE_PATH.toFile(), PersonalDataWrapperDTO.class);

        List<PersonalDataImportDTO> personalDataDTOs = personalDataWrapperDTO.getPersonal_datas();

        for (PersonalDataImportDTO dto : personalDataDTOs) {
            if(!validationUtils.isValid(dto) ||
                    personalDataRepository.findByCardNumber(dto.getCardNumber()).isPresent()
            ) {
                sb.append("Invalid personal data").append(System.lineSeparator());
                continue;
            }

            PersonalData personalData = modelMapper.map(dto, PersonalData.class);
            personalDataRepository.save(personalData);

            sb.append(String.format("Successfully imported personal data for visitor with card number %s", personalData.getCardNumber())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
