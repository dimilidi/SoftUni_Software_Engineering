package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.AstronomerRootDto;
import softuni.exam.models.dto.xmls.AstronomerSeedDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    public static final String FILE_PATH = "src/main/resources/files/xml/astronomers.xml";
    private final AstronomerRepository astronomerRepository;
    private final StarRepository starRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;


    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
        // new String(Files.readAllBytes(Path.of(ASTRONOMER_FILE_PATH)));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();


        JAXBContext jaxbContext = JAXBContext.newInstance(AstronomerRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        AstronomerRootDto astronomerRootDto = (AstronomerRootDto)unmarshaller.unmarshal(new File(FILE_PATH));

        for (AstronomerSeedDto astronomerSeedDto : astronomerRootDto.getAstronomerSeedDtos()) {

            Optional<Astronomer> astronomerOptional =  this.astronomerRepository.findByFirstNameAndLastName(astronomerSeedDto.getFirstName(), astronomerSeedDto.getLastName());
            Optional<Star> starOptional = this.starRepository.findById(astronomerSeedDto.getStar());

            if(!validationUtil.isValid(astronomerSeedDto) || astronomerOptional.isPresent() ||starOptional.isEmpty()){
                sb.append("Invalid astronomer").append(System.lineSeparator());
                continue;
            }

            Astronomer astronomer = this.modelMapper.map(astronomerSeedDto, Astronomer.class);
            astronomer.setObservingStar(starOptional.get());

            this.astronomerRepository.save(astronomer);

            sb.append(String.format(Locale.US,"Successfully imported astronomer %s %s - %.2f",
                    astronomerSeedDto.getFirstName(),
                    astronomerSeedDto.getLastName(),
                    astronomerSeedDto.getAverageObservationHours())).append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
