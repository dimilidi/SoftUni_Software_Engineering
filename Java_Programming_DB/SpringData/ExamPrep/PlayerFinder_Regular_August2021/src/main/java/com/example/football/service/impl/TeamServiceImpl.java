package com.example.football.service.impl;

import com.example.football.models.dto.team.TeamImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.example.football.util.validation.ValidationUtils;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class TeamServiceImpl implements TeamService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/teams.json");
    public static final String SUCCESSFULLY_IMPORTED_TEAM_TEMPLATE = "Successfully imported Team %s - %d";
    public static final String INVALID_TEAM = "Invalid Team";
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private StringBuilder sb;
    private final TeamRepository teamRepository;

    public TeamServiceImpl(
            TeamRepository teamRepository,
            @Qualifier("gson") Gson gson,
            ValidationUtils validationUtils,
            ModelMapper modelMapper,
            TownRepository townRepository
    ) {
        sb = new StringBuilder();
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {

        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {

        return Files.readString(FILE_PATH);
    }

    @Override
    public String importTeams() throws IOException {

        TeamImportDTO[] teamImportDTOS = gson.fromJson(readTeamsFileContent(), TeamImportDTO[].class);

        for (TeamImportDTO dto : teamImportDTOS) {
            if (!validationUtils.isValid(dto) ||
                    teamRepository.findFirstByName(dto.getName()).isPresent()
            ) {
                sb.append(INVALID_TEAM).append(System.lineSeparator());
                continue;
            }

            Team team = modelMapper.map(dto, Team.class);
            System.out.println(dto.getTownName());
            Town town = townRepository.findFirstByName(dto.getTownName()).get();
            team.setTown(town);

            teamRepository.saveAndFlush(team);

            sb.append(String.format(SUCCESSFULLY_IMPORTED_TEAM_TEMPLATE, team.getName(), team.getFanBase())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
