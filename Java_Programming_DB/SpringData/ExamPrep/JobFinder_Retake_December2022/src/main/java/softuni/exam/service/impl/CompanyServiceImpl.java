package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.company.CompanyImportDto;
import softuni.exam.models.dto.company.CompanyImportWrapperDto;
import softuni.exam.models.entity.Company;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.util.validation.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static softuni.exam.constants.Messages.*;

@Service
public class CompanyServiceImpl implements CompanyService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/companies.xml");
    private final StringBuilder sb;
    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            XmlParser xmlParser, ValidationUtils validationUtils,
            ModelMapper modelMapper,
            CountryRepository countryRepository
    ) {
        this.sb = new StringBuilder();
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {
        CompanyImportWrapperDto companyImportWrapperDto = xmlParser.fromFile(FILE_PATH.toFile(), CompanyImportWrapperDto.class);
        List<CompanyImportDto> companyImportDtos = companyImportWrapperDto.getCompanies();

        for (CompanyImportDto dto : companyImportDtos) {
            if (!validationUtils.isValid(dto) ||
                    companyRepository.findFirstByName(dto.getName()).isPresent()
            ) {
                sb.append(String.format(INVALID_ENTITY, COMPANY)).append(System.lineSeparator());
                continue;
            }

            Company company = modelMapper.map(dto, Company.class);
            companyRepository.saveAndFlush(company);
            sb.append(String.format(SUCCESSFUL_IMPORT, COMPANY, company.getName(), dto.getCountryId()))
             .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
