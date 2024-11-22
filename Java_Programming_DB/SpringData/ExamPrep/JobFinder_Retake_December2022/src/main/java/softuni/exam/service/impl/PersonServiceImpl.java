package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.person.PersonImportDto;
import softuni.exam.models.entity.Person;
import softuni.exam.models.entity.enums.StatusTypeEnum;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.PersonService;
import softuni.exam.util.validation.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static softuni.exam.constants.Messages.PERSON;
import static softuni.exam.constants.Messages.SUCCESSFUL_IMPORT;

@Service
public class PersonServiceImpl implements PersonService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/people.json");
    private final StringBuilder sb;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;
    private final PersonRepository personRepository;
    private final ValidationUtils validationUtils;

    public PersonServiceImpl(
            PersonRepository personRepository,
            @Qualifier("gson") Gson gson,
            ValidationUtils validationUtils,
            ModelMapper modelMapper,
            CountryRepository countryRepository
    ) {
        this.validationUtils = validationUtils;
        this.sb = new StringBuilder();
        this.personRepository = personRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return personRepository.count() > 0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importPeople() throws IOException {

        List<PersonImportDto> peopleImportDto = Arrays.stream(gson.fromJson(readPeopleFromFile(), PersonImportDto[].class)).toList();
        for (PersonImportDto dto : peopleImportDto) {
            if (!isValidPerson(dto)) {
                sb.append("Invalid person").append(System.lineSeparator());
                continue;
            }

            Person person = mapToPerson(dto);

            personRepository.saveAndFlush(person);
            sb.append(String.format(SUCCESSFUL_IMPORT, PERSON, person.getFirstName(), person.getLastName()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private Person mapToPerson(PersonImportDto dto) {
        Person person = modelMapper.map(dto, Person.class);
        StatusTypeEnum statusTypeEnum = StatusTypeEnum.fromJsonValue(dto.getStatusType());
        person.setStatusType(statusTypeEnum);
        Long countryId = dto.getCountry();
        person.setCountry(countryRepository.findById(countryId).orElseThrow(NoSuchElementException::new));

        return person;
    }

    private boolean isValidPerson(PersonImportDto dto) {
        return validationUtils.isValid(dto) &&
                personRepository.findFirstByFirstName(dto.getFirstName()).isEmpty() &&
                personRepository.findFirstByEmail(dto.getEmail()).isEmpty() &&
                personRepository.findFirstByPhone(dto.getPhone()).isEmpty();
    }
}
