package com.example.football.service.impl;

import com.example.football.models.dto.stat.StatImportDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.models.dto.stat.StatImportWrapperDTO;
import com.example.football.service.StatService;
import com.example.football.util.validation.ValidationUtils;
import com.example.football.util.xmlParser.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class StatServiceImpl implements StatService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/stats.xml");
    public static final String SUCCESSFULLY_IMPORTED_STAT_TEMPLATE = "Successfully imported Stat %.2f - %.2f - %.2f";
    public static final String INVALID_STAT = "Invalid Stat";
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private StringBuilder sb;
    private final StatRepository statRepository;

    public StatServiceImpl(
            StatRepository statRepository,
            XmlParser xmlParser,
            ValidationUtils validationUtils,
            ModelMapper modelMapper
    ) {
        this.sb = new StringBuilder();
        this.statRepository = statRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {

        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {
        StatImportWrapperDTO statImportWrapperDTO = xmlParser.fromFile(FILE_PATH.toFile(), StatImportWrapperDTO.class);
        List<StatImportDTO> statsImportsDTOs = statImportWrapperDTO.getStats();

        for (StatImportDTO dto : statsImportsDTOs) {
            if(!validationUtils.isValid(dto) ||

                    statRepository.findByPassingAndShootingAndEndurance(dto.getPassing(), dto.getShooting(), dto.getEndurance()).isPresent()
            ) {
                sb.append(INVALID_STAT).append(System.lineSeparator());
                continue;
            }
            Stat stat = modelMapper.map(dto, Stat.class);
            statRepository.saveAndFlush(stat);

            sb.append(String.format(SUCCESSFULLY_IMPORTED_STAT_TEMPLATE,
                            stat.getShooting(), stat.getPassing(), stat.getEndurance()))
                    .append(System.lineSeparator());
        }


        return sb.toString().trim();
    }
}
