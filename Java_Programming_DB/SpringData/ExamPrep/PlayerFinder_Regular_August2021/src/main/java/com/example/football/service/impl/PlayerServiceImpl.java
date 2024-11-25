package com.example.football.service.impl;

import com.example.football.models.dto.player.PlayerImportDTO;
import com.example.football.models.dto.player.PlayerImportWrapperDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.models.entity.enums.PositionTypeEnum;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.validation.ValidationUtils;
import com.example.football.util.xmlParser.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/players.xml");
    public static final String SUCCESSFULLY_IMPORTED_PLAYER_TEMPLATE = "Successfully imported Player %s %s - %s";
    public static final String INVALID_PLAYER = "Invalid Player";
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final StatRepository statRepository;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private StringBuilder sb;
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(
            PlayerRepository playerRepository,
            XmlParser xmlParser,
            ValidationUtils validationUtils,
            StatRepository statRepository,
            ModelMapper modelMapper,
            TeamRepository teamRepository,
            TownRepository townRepository
    ) {
        this.sb = new StringBuilder();
        this.playerRepository = playerRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {

        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {

        return Files.readString(FILE_PATH);
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        PlayerImportWrapperDTO playerImportWrapperDTO = xmlParser.fromFile(FILE_PATH.toFile(), PlayerImportWrapperDTO.class);
        List<PlayerImportDTO> playerImportDTOs = playerImportWrapperDTO.getPlayers();

        for (PlayerImportDTO dto : playerImportDTOs) {
            Optional<Stat> statOpt = statRepository.findById(dto.getStat().getId());
            if(!validationUtils.isValid(dto) ||
                    playerRepository.findByEmail(dto.getEmail()).isPresent() ||
                    statOpt.isEmpty()
            ) {
                sb.append(INVALID_PLAYER).append(System.lineSeparator());
                continue;
            }

            Player player = mapToPlayer(dto, statOpt);
            playerRepository.saveAndFlush(player);

            sb.append(String.format(SUCCESSFULLY_IMPORTED_PLAYER_TEMPLATE,
                            player.getFirstName(), player.getLastName(), player.getPosition()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }


    @Override
    public String exportBestPlayers() {
        LocalDate fromDate = LocalDate.of(1995, 1, 1);
        LocalDate toDate = LocalDate.of(2003, 1, 1);
        List<Player> players = playerRepository.findAllByBirthDateBetweenOrderByStat_ShootingDescStat_PassingDescStat_EnduranceDescLastNameAsc(fromDate, toDate);
        List<String> result = players.stream().map(Player::toString).toList();
        return String.join("", result);
    }

    private Player mapToPlayer(PlayerImportDTO dto, Optional<Stat> statOpt) {
        Player player = modelMapper.map(dto, Player.class);
        player.setStat(statOpt.get());
        Optional<Team> teamOpt = teamRepository.findFirstByName(dto.getTeam().getName());
        teamOpt.ifPresent(player::setTeam);
        Optional<Town> townOpt = townRepository.findFirstByName(dto.getTown().getName());
        townOpt.ifPresent(player::setTown);
        PositionTypeEnum positionTypeEnum = PositionTypeEnum.valueOf(dto.getPosition());
        player.setPosition(positionTypeEnum);

        return player;
    }
}
