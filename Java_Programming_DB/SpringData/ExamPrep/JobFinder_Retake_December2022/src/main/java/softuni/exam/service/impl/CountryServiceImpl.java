package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.country.CountryImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.Messages.*;

@Service
public class CountryServiceImpl implements CountryService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/countries.json");
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final StringBuilder sb;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;

    public CountryServiceImpl(
            CountryRepository countryRepository,
            @Qualifier("gson") Gson gson,
            ModelMapper modelMapper,
            ValidationUtils validationUtils
    ) {
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
        this.sb = new StringBuilder();
        this.countryRepository = countryRepository;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importCountries() throws IOException {

       List<CountryImportDto> countriesImportDto =  Arrays
               .stream(gson.fromJson(readCountriesFileContent(), CountryImportDto[].class))
               .toList();

        for (CountryImportDto dto : countriesImportDto) {

            final Optional<Country> optionalCountry =
                    countryRepository.findFirstByName(dto.getName());

            if (!validationUtils.isValid(dto) || optionalCountry.isPresent()) {
                sb.append(String.format(INVALID_ENTITY, COUNTRY)).append(System.lineSeparator());
                continue;
            }

            final Country country = modelMapper.map(dto, Country.class);

            countryRepository.saveAndFlush(country);

            sb.append(String.format(SUCCESSFUL_IMPORT, COUNTRY,
                            country.getName(), country.getCode()))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
