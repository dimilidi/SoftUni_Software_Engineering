package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.entity.Town;
import softuni.exam.models.dto.town.TownImportDTO;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.constants.Messages.*;

@Service
public class TownServiceImpl implements TownService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/towns.json");
    private final StringBuilder sb;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;

    public TownServiceImpl(
            TownRepository townRepository,
            @Qualifier("gson") Gson gson,
            ValidationUtils validationUtils,
            ModelMapper modelMapper
    ) {
        this.sb = new StringBuilder();
        this.townRepository = townRepository;
        this.gson = gson;
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
    public String importTowns() throws IOException {
        TownImportDTO[] townImportDTOS = gson.fromJson(readTownsFileContent(), TownImportDTO[].class);

        for (TownImportDTO dto : townImportDTOS) {
            if(!validationUtils.isValid(dto) ||
                    townRepository.findFirstByTownName(dto.getTownName()).isPresent()
            ) {
                sb.append(String.format(INVALID_ENTITY, TOWN)).append(System.lineSeparator());
                continue;
            }

            Town town = modelMapper.map(dto, Town.class);
            townRepository.saveAndFlush(town);

            sb.append(String.format(SUCCESSFUL_IMPORT,TOWN, town.getTownName(), town.getPopulation()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
