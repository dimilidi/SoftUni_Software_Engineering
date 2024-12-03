package softuni.exam.service.impl;

import com.google.gson.Gson;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AttractionImportDTO;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.AttractionService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AttractionServiceImpl implements AttractionService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/attractions.json");
    private final AttractionRepository attractionRepository;
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;
    private StringBuilder sb;

    public AttractionServiceImpl(
            AttractionRepository attractionRepository,
            @Qualifier("gson") Gson gson,
            ValidationUtils validationUtils,
            ModelMapper modelMapper,
            CountryRepository countryRepository
    ) {
        this.sb = new StringBuilder();
        this.attractionRepository = attractionRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {

        return attractionRepository.count() > 0;
    }

    @Override
    public String readAttractionsFileContent() throws IOException {

        return Files.readString(FILE_PATH);
    }

    @Override
    public String importAttractions() throws IOException {
        AttractionImportDTO[] attractionImportDTOS = gson.fromJson(readAttractionsFileContent(), AttractionImportDTO[].class);

        for (AttractionImportDTO dto : attractionImportDTOS) {
            if (!validationUtils.isValid(dto) ||
                    attractionRepository.findByName(dto.getName()).isPresent()
            ) {
                sb.append("Invalid attraction").append(System.lineSeparator());
                continue;
            }
            Attraction attraction = modelMapper.map(dto, Attraction.class);
            Optional<Country> countryOpt = countryRepository.findById(dto.getCountry());
            countryOpt.ifPresent(attraction::setCountry);
            attractionRepository.save(attraction);

            sb.append(String.format("Successfully imported attraction %s", dto.getName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String exportAttractions() {
        return attractionRepository.findAllByTypeInAndElevationGreaterThanEqualOrderByNameAscCountryNameAsc(List.of("historical site", "archaeological site"), 300)
                .stream()
                .map(Attraction::toString)
                .collect(Collectors.joining());
    }
}
