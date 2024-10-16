package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.country.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.COUNTRIES_PATH;

@Service
public class CountryServiceImpl implements CountryService {
    private final StringBuilder sb;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper mapper;

    public CountryServiceImpl(
            CountryRepository countryRepository,
            Gson gson,
            ValidationUtils validationUtils,
            ModelMapper mapper
    ) {
        this.sb = new StringBuilder();
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(COUNTRIES_PATH);
    }

    @Override
    public String importCountries() throws IOException {

        final List<CountryImportDTO> countriesImportDTO =
                Arrays.stream(this.gson.fromJson(this.readCountriesFromFile(), CountryImportDTO[].class))
                        .toList();

        for (CountryImportDTO countryDTO : countriesImportDTO) {
            final Optional<Country> optionalCountry =
                    this.countryRepository.findFirstByCountryName(countryDTO.getCountryName());

            if (!this.validationUtils.isValid(countryDTO) || optionalCountry.isPresent()) {
                this.sb.append(String.format(INVALID_ENTITY, COUNTRY))
                        .append(System.lineSeparator());
                continue;
            }

            final Country country = this.mapper.map(countryDTO, Country.class);

            this.countryRepository.saveAndFlush(country);

            this.sb.append(String.format(SUCCESSFUL_IMPORT, COUNTRY,
                            country.getCountryName(), country.getCurrency()))
                    .append(System.lineSeparator());
        }

        return this.sb.toString().trim();
    }
}