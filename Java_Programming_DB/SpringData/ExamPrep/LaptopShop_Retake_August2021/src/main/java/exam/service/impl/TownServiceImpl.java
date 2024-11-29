package exam.service.impl;

import exam.model.entity.Town;
import exam.model.dto.town.TownImportDTO;
import exam.model.dto.town.TownImportWrapperDTO;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.validation.ValidationUtils;
import exam.util.xmlParser.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class TownServiceImpl implements TownService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/towns.xml");
    private final StringBuilder sb;
    private final TownRepository townRepository;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, XmlParser xmlParser, ValidationUtils validationUtils, ModelMapper modelMapper) {
        this.sb = new StringBuilder();
        this.townRepository = townRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importTowns() throws JAXBException, IOException {
        TownImportWrapperDTO townImportWrapperDTO = xmlParser.fromFile(FILE_PATH.toFile(), TownImportWrapperDTO.class);
        Set<TownImportDTO> townImportDTOs = townImportWrapperDTO.getTowns();

        for (TownImportDTO dto : townImportDTOs) {
            if(!validationUtils.isValid(dto) ||
                    townRepository.findByName(dto.getName()).isPresent()
            ) {
                sb.append("Invalid Town").append(System.lineSeparator());
                continue;
            }

            Town town = modelMapper.map(dto, Town.class);
            townRepository.save(town);
            sb.append(String.format("Successfully imported Town %s", dto.getName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
