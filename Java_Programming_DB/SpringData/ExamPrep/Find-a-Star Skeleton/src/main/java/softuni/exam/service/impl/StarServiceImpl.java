package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.ConstellationSeedDto;
import softuni.exam.models.dto.jsons.StarSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Optional;

@Service
public class StarServiceImpl implements StarService {

    public static final String FILE_PATH = "src/main/resources/files/json/stars.json";
    private final StarRepository starRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final ConstellationRepository constellationRepository;

    public StarServiceImpl(
            StarRepository starRepository,
            Gson gson,
            ModelMapper modelMapper,
            ValidationUtil validationUtil,
            ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.constellationRepository = constellationRepository;
    }

    @Override
    public boolean areImported() {
        return starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder sb = new StringBuilder();
        StarSeedDto[] starSeedDtos = this.gson.fromJson(readStarsFileContent(), StarSeedDto[].class);
        for (StarSeedDto starSeedDto : starSeedDtos) {
            Optional<Star> starOptional = starRepository.findByName(starSeedDto.getName());

            if (!validationUtil.isValid(starSeedDto) || starOptional.isPresent()) {
                sb.append("Invalid star\n");
            } else {
                Star star = modelMapper.map(starSeedDto, Star.class);
                Optional<Constellation> constellationOptional = constellationRepository.findById(starSeedDto.getConstellation());
                if (constellationOptional.isPresent()) {
                    Constellation constellation = constellationOptional.get();
                    star.setConstellation(constellation);
                }

                this.starRepository.saveAndFlush(star);

                sb.append(String.format(
                        Locale.US, "Successfully imported star %s - %.2f light years\n",
                        starSeedDto.getName(), starSeedDto.getLightYears()));
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String exportStars() {
        return "";
    }
}
