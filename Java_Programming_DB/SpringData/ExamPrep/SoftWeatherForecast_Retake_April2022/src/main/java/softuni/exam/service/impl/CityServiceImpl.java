package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.Converters;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.city.CityImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.CITIES_PATH;

@Service
public class CityServiceImpl implements CityService {
    private final StringBuilder sb;
    private final CityRepository cityRepository;
    private final Gson gson;
    private final CountryRepository countryRepository;
    private final ValidationUtils validationUtils;
    private final ModelMapper mapper;

    public CityServiceImpl(CityRepository cityRepository, Gson gson, CountryRepository countryRepository, ValidationUtils validationUtils, ModelMapper mapper) {
        this.sb = new StringBuilder();
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.countryRepository = countryRepository;
        this.validationUtils = validationUtils;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(CITIES_PATH);
    }

    @Override
    public String importCities() throws IOException {
        final List<CityImportDTO> citiesImportDTO =
                Arrays.stream(this.gson.fromJson(this.readCitiesFileContent(), CityImportDTO[].class))
                        .toList();

        for (CityImportDTO cityDTO : citiesImportDTO) {

            final Optional<City> optionalCity = this.cityRepository.findFirstByCityName(cityDTO.getCityName());
            final Optional<Country> optionalCountry = this.countryRepository.findById(cityDTO.getCountry());

            if (!this.validationUtils.isValid(cityDTO) || optionalCity.isPresent()
                    || optionalCountry.isEmpty()) {

                this.sb.append(String.format(INVALID_ENTITY, CITY))
                        .append(System.lineSeparator());
                continue;
            }

            final City city = this.mapper.map(cityDTO, City.class);

            city.setCountry(optionalCountry.get());

            this.cityRepository.saveAndFlush(city);

            this.sb.append(String.format(SUCCESSFUL_IMPORT, CITY,
                            city.getCityName(), city.getPopulation()))
                    .append(System.lineSeparator());
        }

        return this.sb.toString().trim();
    }
}