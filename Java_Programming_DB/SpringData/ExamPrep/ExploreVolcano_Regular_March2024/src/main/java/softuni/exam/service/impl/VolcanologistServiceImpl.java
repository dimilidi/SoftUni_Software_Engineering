package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.volcanologist.VolcanologistImportDTO;
import softuni.exam.models.dto.volcanologist.VolcanologistsImportWrapperDTO;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class VolcanologistServiceImpl implements VolcanologistService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/volcanologists.xml");
    public static final String URL = "src/main/resources/files/xml/volcanologists.xml";
    private final String INVALID_VOLCANOLOGIST = "Invalid volcanologist";
    private final String SUCCESSFUL_IMPORT_TEMPLATE = "Successfully imported %s %s %s";

    private final StringBuilder sb;
    private final VolcanologistRepository volcanologistRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final VolcanoRepository volcanoRepository;
    private final ModelMapper modelMapper;

    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository, XmlParser xmlParser, ValidationUtil validationUtil, VolcanoRepository volcanoRepository, ModelMapper modelMapper) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.sb = new StringBuilder();
        this.volcanologistRepository = volcanologistRepository;
        this.volcanoRepository = volcanoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        VolcanologistsImportWrapperDTO volcanologistsImportWrapperDTO = xmlParser.fromFile(URL, VolcanologistsImportWrapperDTO.class);
        List<VolcanologistImportDTO> volcanologistImportDTOS = volcanologistsImportWrapperDTO.getVolcanologists();

        for (VolcanologistImportDTO volcanologistDTO : volcanologistImportDTOS) {
            if (!validationUtil.isValid(volcanologistDTO)
                    || volcanologistRepository.findByFirstNameAndLastName(volcanologistDTO.getFirstName(), volcanologistDTO.getLastName()).isPresent()
                    || volcanoRepository.findById(volcanologistDTO.getExploringVolcano()).isEmpty()) {
                sb.append(INVALID_VOLCANOLOGIST).append(System.lineSeparator());
                continue;
            }

            Volcanologist volcanologist = modelMapper.map(volcanologistDTO, Volcanologist.class);
            volcanologist.setExploringVolcano(volcanoRepository
                    .findById(volcanologistDTO.getExploringVolcano())
                    .orElse(null));

            volcanologistRepository.saveAndFlush(volcanologist);
            sb.append(String.format(SUCCESSFUL_IMPORT_TEMPLATE,
                    volcanologist.getClass().getSimpleName().toLowerCase(),
                    volcanologist.getFirstName(),
                    volcanologist.getLastName()
            )).append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}
