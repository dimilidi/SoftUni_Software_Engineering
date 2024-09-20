package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.ConstellationSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    public static final String FILE_PATH = "src/main/resources/files/json/constellations.json";
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder sb = new StringBuilder();
        //   ConstellationSeedDto[] constellationSeedDtos = this.gson.fromJson(this.readConstellationsFromFile(), ConstellationSeedDto[].class);
        ConstellationSeedDto[] constellationSeedDtos = this.gson.fromJson(new FileReader(FILE_PATH), ConstellationSeedDto[].class);


        System.out.println("*****************************");
        System.out.println("constellationSeedDtos.length = " + readConstellationsFromFile() );
        System.out.println("*****************************");
        for (ConstellationSeedDto constellationSeedDto : constellationSeedDtos) {
            Optional<Constellation> constellationOpt =  this.constellationRepository.findByName(constellationSeedDto.getName());

            System.out.println(constellationSeedDto.getName() + " - " + constellationSeedDto.getDescription());
            if (!this.validationUtil.isValid(constellationSeedDto) || constellationOpt.isPresent()) {
                sb.append("Invalid constellation").append(System.lineSeparator());
                continue;
            }



            Constellation constellation = this.modelMapper.map(constellationSeedDto, Constellation.class);
            /*constellation.setDescription(constellationSeedDto.getDescription());
            constellation.setName(constellationSeedDto.getName());
*/
            this.constellationRepository.saveAndFlush(constellation);

            sb.append(String.format("Successfully imported constellation %s - %s",
                    constellation.getName(), constellation.getDescription())).append(System.lineSeparator());

        }
        return sb.toString();

    }
}
