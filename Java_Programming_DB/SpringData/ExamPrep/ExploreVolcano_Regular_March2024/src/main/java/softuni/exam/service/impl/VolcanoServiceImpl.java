package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.volcano.VolcanoExportDTO;
import softuni.exam.models.dto.volcano.VolcanoImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Volcano;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VolcanoServiceImpl implements VolcanoService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/volcanoes.json");
    private final String INVALID_VOLCANO = "Invalid volcano";
    private final String SUCCESSFUL_IMPORT_TEMPLATE = "Successfully imported %s %s of type %s";

    private final StringBuilder sb;
    private final VolcanoRepository volcanoRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    public VolcanoServiceImpl(
            VolcanoRepository volcanoRepository,
            @Qualifier("gson") Gson gson,
            ValidationUtil validationUtil,
            ModelMapper modelMapper,
            CountryRepository countryRepository) {
        this.sb = new StringBuilder();
        this.volcanoRepository = volcanoRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importVolcanoes() throws IOException {
        VolcanoImportDTO[] volcanoImportDTOS = gson.fromJson(readVolcanoesFileContent(), VolcanoImportDTO[].class);

        for (VolcanoImportDTO volcanoDTO : volcanoImportDTOS) {
            if (!validationUtil.isValid(volcanoDTO) || volcanoRepository.findByName(volcanoDTO.getName()).isPresent()) {
                sb.append(INVALID_VOLCANO).append(System.lineSeparator());
                continue;
            }

            Volcano volcano = modelMapper.map(volcanoDTO, Volcano.class);
            Country country = countryRepository.findById(volcanoDTO.getCountry()).orElseThrow(NoSuchElementException::new);
            volcano.setCountry(country);

            volcanoRepository.saveAndFlush(volcano);

            sb.append(String.format(SUCCESSFUL_IMPORT_TEMPLATE,
                    volcano.getClass().getSimpleName().toLowerCase(),
                    volcano.getName(),
                    volcano.getVolcanoType()
            )).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String exportVolcanoes() {

        List<Volcano> volcanoes = volcanoRepository.findAllByIsActiveTrueAndElevationGreaterThanAndLastEruptionNotNullOrderByElevationDesc(3000)
                .orElseThrow(() -> new NoSuchElementException("Volcano not found."));
        volcanoes.stream()
                .map(v -> {
                    VolcanoExportDTO exportDTO = modelMapper.map(v, VolcanoExportDTO.class);
                    exportDTO.setCountry(v.getCountry().getName());
                    return exportDTO;
                })
                .forEach(sb::append);

        return sb.toString().trim();
    }
}
