package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VisitorImportDTO;
import softuni.exam.models.dto.VisitorImportWrapperDTO;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.models.entity.Visitor;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.repository.VisitorRepository;
import softuni.exam.service.VisitorService;
import softuni.exam.util.validation.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

//ToDo - Implement all the methods

@Service
public class VisitorServiceImpl implements VisitorService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/visitors.xml");
    private final VisitorRepository visitorRepository;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final PersonalDataRepository personalDataRepository;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;
    private final AttractionRepository attractionRepository;
    private StringBuilder sb;

    public VisitorServiceImpl(
            VisitorRepository visitorRepository,
            XmlParser xmlParser,
            ValidationUtils validationUtils,
            PersonalDataRepository personalDataRepository,
            ModelMapper modelMapper,
            CountryRepository countryRepository,
            AttractionRepository attractionRepository
    ) {
        this.sb = new StringBuilder();
        this.visitorRepository = visitorRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.personalDataRepository = personalDataRepository;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
        this.attractionRepository = attractionRepository;
    }

    @Override
    public boolean areImported() {

        return visitorRepository.count() > 0;
    }

    @Override
    public String readVisitorsFileContent() throws IOException {

        return Files.readString(FILE_PATH);
    }

    @Override
    public String importVisitors() throws JAXBException, FileNotFoundException {
        VisitorImportWrapperDTO visitorImportWrapperDTO = xmlParser.fromFile(FILE_PATH.toFile(), VisitorImportWrapperDTO.class);

        List<VisitorImportDTO> visitors = visitorImportWrapperDTO.getVisitors();

        for (VisitorImportDTO dto : visitors) {
            Optional<PersonalData> personalDataOpt = personalDataRepository.findById(dto.getPersonalData());
            if(!validationUtils.isValid(dto) ||
                    (visitorRepository.findByFirstName(dto.getFirstName()).isPresent() &&
                    visitorRepository.findByLastName(dto.getLastName()).isPresent())||
                    visitorRepository.findByPersonalData(personalDataOpt.get()).isPresent()) {
                sb.append("Invalid visitor").append(System.lineSeparator());
                continue;
            }

            Visitor visitor = modelMapper.map(dto, Visitor.class);
            personalDataOpt.ifPresent(visitor::setPersonalData);
            Optional<Country> countryOptional = countryRepository.findById(dto.getCountry());
            countryOptional.ifPresent(visitor::setCountry);
            Optional<Attraction> attractionOptional = attractionRepository.findById(dto.getAttraction());
            attractionOptional.ifPresent(visitor::setAttraction);

            visitorRepository.saveAndFlush(visitor);

            sb.append(String.format("Successfully imported visitor %s %s",
                            visitor.getFirstName(), visitor.getLastName()))
                    .append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}
