package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CountryServiceImpl implements CountryService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/countries.json");
    private final StringBuilder sb;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;

    public CountryServiceImpl(
            CountryRepository countryRepository,
            @Qualifier("gson") Gson gson,
            ValidationUtils validationUtils,
            ModelMapper modelMapper
    ) {
        this.sb = new StringBuilder();
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {

        return countryRepository.count() > 0;
    }

    @Override
    public String readCountryFileContent() throws IOException {

        return Files.readString(FILE_PATH);
    }

    @Override
    public String importCountries() throws IOException {

        CountryImportDTO[] countryImportDTOS = gson.fromJson(readCountryFileContent(), CountryImportDTO[].class);

        for (CountryImportDTO dto : countryImportDTOS) {
            if (!validationUtils.isValid(dto) ||
                    countryRepository.findByName(dto.getName()).isPresent()
            ) {
                sb.append("Invalid country").append(System.lineSeparator());
                continue;
            }
            Country country = modelMapper.map(dto, Country.class);

            countryRepository.save(country);

            sb.append(String.format("Successfully imported country %s", dto.getName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
