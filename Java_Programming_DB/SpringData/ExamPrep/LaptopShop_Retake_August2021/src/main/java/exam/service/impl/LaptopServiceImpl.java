package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.laptop.LaptopImportDTO;
import exam.model.entity.Laptop;
import exam.model.entity.enums.WarrantyTypeEnum;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import exam.util.validation.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/laptops.json");
    public static final String INVALID_LAPTOP = "Invalid Laptop";
    public static final String SUCCESSFULLY_IMPORTED_LAPTOP = "Successfully imported Laptop %s - %.2f - %d - %d";
    private final Gson gson;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final ShopRepository shopRepository;
    private StringBuilder sb;
    private final LaptopRepository laptopRepository;

    public LaptopServiceImpl(LaptopRepository laptopRepository, @Qualifier("gson") Gson gson, ValidationUtils validationUtils, ModelMapper modelMapper, ShopRepository shopRepository) {
        this.sb = new StringBuilder();
        this.laptopRepository = laptopRepository;
        this.gson = gson;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.shopRepository = shopRepository;
    }

    @Override
    public boolean areImported() {
        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importLaptops() throws IOException {
        LaptopImportDTO[] laptopImportDTOs= gson.fromJson(readLaptopsFileContent(), LaptopImportDTO[].class);

        for (LaptopImportDTO dto : laptopImportDTOs) {
            WarrantyTypeEnum[] typeEnums = WarrantyTypeEnum.values();
            List<String> warrantyTypeNames = Arrays.stream(typeEnums).map(WarrantyTypeEnum::name).toList();
            if(!validationUtils.isValid(dto) ||
                    laptopRepository.findByMacAddress(dto.getMacAddress()).isPresent() ||
                    !warrantyTypeNames.contains(dto.getWarrantyType())
            ) {
                appendInvalidMessage();
                continue;
            }

            Laptop laptop = mapToLaptop(dto);
            laptopRepository.save(laptop);

            appendSuccessfullyImportedMessage(dto);

        }
        return sb.toString().trim();
    }

    @Override
    public String exportBestLaptops() {
        return laptopRepository.findAllByOrderByCpuSpeedDescRamDescStorageDescMacAddressAsc()
                .stream()
                .map(Laptop::toString)
                .collect(Collectors.joining("\n"));

    }


    private Laptop mapToLaptop(LaptopImportDTO dto) {
        Laptop laptop = modelMapper.map(dto, Laptop.class);
        shopRepository.findByName(dto.getShop().getName()).ifPresent(laptop::setShop);
        WarrantyTypeEnum warrantyTypeEnum = WarrantyTypeEnum.valueOf(dto.getWarrantyType());
        laptop.setWarrantyType(warrantyTypeEnum);

        return laptop;
    }

    private void appendInvalidMessage() {
        sb.append(INVALID_LAPTOP).append(System.lineSeparator());
    }

    private void appendSuccessfullyImportedMessage(LaptopImportDTO dto) {
        sb.append(String.format(SUCCESSFULLY_IMPORTED_LAPTOP,
                dto.getMacAddress(), dto.getCpuSpeed(), dto.getRam(), dto.getStorage()))
                .append(System.lineSeparator());
    }

}
