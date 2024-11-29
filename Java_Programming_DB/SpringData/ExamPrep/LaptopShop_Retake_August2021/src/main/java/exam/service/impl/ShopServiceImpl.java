package exam.service.impl;

import exam.model.entity.Shop;
import exam.model.entity.Town;
import exam.model.dto.shop.ShopImportDTO;
import exam.model.dto.shop.ShopImportWrapperDTO;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import exam.util.validation.ValidationUtils;
import exam.util.xmlParser.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

@Service
public class ShopServiceImpl implements ShopService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/xml/shops.xml");
    private final StringBuilder sb;
    private final ShopRepository shopRepository;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    public ShopServiceImpl(ShopRepository shopRepository, XmlParser xmlParser, ValidationUtils validationUtils, ModelMapper modelMapper, TownRepository townRepository) {
        this.sb = new StringBuilder();
        this.shopRepository = shopRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ShopImportWrapperDTO shopImportWrapperDTO = xmlParser.fromFile(FILE_PATH.toFile(), ShopImportWrapperDTO.class);
        Set<ShopImportDTO> shopImportDTOs = shopImportWrapperDTO.getShops();

        for (ShopImportDTO dto : shopImportDTOs) {
            if(!validationUtils.isValid(dto) ||
                shopRepository.findByName(dto.getName()).isPresent()
            ) {
                sb.append("Invalid Shop").append(System.lineSeparator());
                continue;
            }

            Shop shop = modelMapper.map(dto, Shop.class);
            Optional<Town> townOpt = townRepository.findByName(shop.getTown().getName());
            townOpt.ifPresent(shop::setTown);
            shopRepository.save(shop);

            sb.append(String.format("Successfully imported Shop %s %s", shop.getName(), shop.getIncome()))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
