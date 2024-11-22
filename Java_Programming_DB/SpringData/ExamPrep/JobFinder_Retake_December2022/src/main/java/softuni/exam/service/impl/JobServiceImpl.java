package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.job.ImportJobDto;
import softuni.exam.models.dto.job.ImportJobWrapperDto;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.JobService;
import softuni.exam.util.validation.ValidationUtils;
import softuni.exam.util.xmlParser.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.Messages.*;

@Service
public class JobServiceImpl implements JobService {
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;
    private StringBuilder sb;
    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/jobs.xml");
    private final JobRepository jobRepository;

    public JobServiceImpl(
            JobRepository jobRepository,
            XmlParser xmlParser,
            ValidationUtils validationUtils,
            ModelMapper modelMapper,
            CompanyRepository companyRepository
    ) {
        this.sb = new StringBuilder();
        this.jobRepository = jobRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean areImported() {
        return jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Transactional
    @Override
    public String importJobs() throws IOException, JAXBException {
        ImportJobWrapperDto importJobWrapperDto = xmlParser.fromFile(FILE_PATH.toFile(), ImportJobWrapperDto.class);
        List<ImportJobDto> importJobsDtos = importJobWrapperDto.getJobs();

        for (ImportJobDto dto : importJobsDtos) {
            if (!validationUtils.isValid(dto) ||
                    companyRepository.findById(dto.getCompany()).isEmpty() ||
                    jobRepository.findByTitle(dto.getTitle()).isPresent()
            ) {
                sb.append(String.format(INVALID_ENTITY, JOB)).append(System.lineSeparator());
                continue;
            }

            Optional<Company> companyOpt = companyRepository.findById(dto.getCompany());
            if (companyOpt.isPresent()) {
                Company company = companyOpt.get();
                Job job = modelMapper.map(dto, Job.class);
                job.setCompany(company);

                jobRepository.saveAndFlush(job);
                company.getJobs().add(job);
                companyRepository.save(company);

                sb.append(String.format(SUCCESSFUL_IMPORT_JOB, job.getTitle())).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }

    @Override
    public String getBestJobs() {
        List<Job>  bestJobs= jobRepository.findAllBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc(5000.00, 30.00);

        List<String> jobsList = bestJobs.stream().map(Job::toString).toList();
        String result = String.join("\n", jobsList);

        return result;
    }
}
