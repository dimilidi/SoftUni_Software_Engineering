package org.lididimi.nltworkshop.service.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.lididimi.nltworkshop.data.entities.Company;
import org.lididimi.nltworkshop.repositories.CompanyRepository;
import org.lididimi.nltworkshop.service.CompanyService;
import org.lididimi.nltworkshop.service.model.CompanyImportModel;
import org.lididimi.nltworkshop.service.model.CompanyRootImportModel;
import org.lididimi.nltworkshop.util.xmlParser.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/xmls/companies.xml");
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final XmlMapper xmlMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, XmlParser xmlParser, XmlMapper xmlMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.xmlMapper = xmlMapper;
    }

    @Override
    public boolean isImported() {
        return companyRepository.count() > 0;
    }

    @Override
    public void seedData() throws IOException {
       CompanyRootImportModel companyRootImportModel = xmlMapper.readValue(FILE_PATH.toFile(), CompanyRootImportModel.class);

        List<CompanyImportModel> importModels = companyRootImportModel.getCompanies();

        for (CompanyImportModel model : importModels) {
            Company company = modelMapper.map(model, Company.class);
            companyRepository.saveAndFlush(company);
        }
    }

    @Override
    public String readFile() throws IOException {
        System.out.println(Files.readString(FILE_PATH));
        return Files.readString(FILE_PATH);
    }
}
