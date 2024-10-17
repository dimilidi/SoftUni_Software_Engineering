package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.country.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CountryServiceImpl implements CountryService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/countries.json");
    private final String INVALID_COUNTRY = "Invalid country";
    private final String SUCCESSFUL_IMPORT_TEMPLATE = "Successfully imported %s %s - %s";

    private final StringBuilder sb;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CountryServiceImpl(
            CountryRepository countryRepository,
            @Qualifier("gson") Gson gson,
            ValidationUtil validationUtil,
            ModelMapper modelMapper) {
        this.sb = new StringBuilder();
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importCountries() throws IOException {

        CountryImportDTO[] countryImportDTOS = gson.fromJson(readCountriesFromFile(), CountryImportDTO[].class);

        for (CountryImportDTO countryDTO : countryImportDTOS) {

            if (!validationUtil.isValid(countryDTO) || countryRepository.findByName(countryDTO.getName()).isPresent()) {
                sb.append(INVALID_COUNTRY).append(System.lineSeparator());
                continue;
            }

            Country country = modelMapper.map(countryDTO, Country.class);
            countryRepository.saveAndFlush(country);
            sb.append(String.format(SUCCESSFUL_IMPORT_TEMPLATE,
                    country.getClass().getSimpleName().toLowerCase(),
                    country.getName(),
                    country.getCapital()
                    )).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
