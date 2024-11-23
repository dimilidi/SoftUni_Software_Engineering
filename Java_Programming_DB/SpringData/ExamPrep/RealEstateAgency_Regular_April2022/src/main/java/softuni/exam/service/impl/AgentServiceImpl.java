package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.agent.AgentImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static softuni.exam.constants.Messages.*;

@Service
public class AgentServiceImpl implements AgentService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/agents.json");
    private final StringBuilder sb;
    private final AgentRepository agentRepository;
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    public AgentServiceImpl(AgentRepository agentRepository, @Qualifier("gson") Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper, TownRepository townRepository) {
        this.sb = new StringBuilder();
        this.agentRepository = agentRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importAgents() throws IOException {
        AgentImportDTO[] agentImportDTOS = gson.fromJson(readAgentsFromFile(), AgentImportDTO[].class);

        for (AgentImportDTO dto : agentImportDTOS) {
            if(!validationUtils.isValid(dto) ||
                    agentRepository.findFirstByFirstName(dto.getFirstName()).isPresent()
            ) {
                sb.append(String.format(INVALID_ENTITY, AGENT)).append(System.lineSeparator());
                continue;
            }

            Agent agent = modelMapper.map(dto, Agent.class);
            Optional<Town> town = townRepository.findFirstByTownName(dto.getTown());
            town.ifPresent(agent::setTown);
            agentRepository.saveAndFlush(agent);

            sb.append(String.format(SUCCESSFUL_IMPORT_AGENT, dto.getFirstName(), dto.getLastName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
