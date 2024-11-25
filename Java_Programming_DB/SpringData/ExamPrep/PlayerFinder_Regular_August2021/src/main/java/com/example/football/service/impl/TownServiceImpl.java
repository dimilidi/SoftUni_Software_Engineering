package com.example.football.service.impl;

import com.example.football.models.dto.town.TownImportDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.validation.ValidationUtils;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class TownServiceImpl implements TownService {


    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/towns.json");
    public static final String SUCCESSFULLY_IMPORTED_TOWN_TEMPLATE = "Successfully imported Town %s - %d";
    public static final String INVALID_TOWN = "Invalid Town";
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private StringBuilder sb;
    private final TownRepository townRepository;

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
                    townRepository.findFirstByName(dto.getName()).isPresent()
            ) {
                sb.append(INVALID_TOWN).append(System.lineSeparator());
                continue;
            }

            Town town = modelMapper.map(dto, Town.class);
            townRepository.saveAndFlush(town);

            sb.append(String.format(SUCCESSFULLY_IMPORTED_TOWN_TEMPLATE, town.getName(), town.getPopulation())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
