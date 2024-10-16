package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SelllerImportDTO.seller.SellerImportDTO;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerServiceImpl implements SellerService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/json/sellers.json");
    private final String INVALID_TEMPLATE = "Invalid %s";
    private final String SUCCESSFUL_IMPORT_TEMPLATE = "Successfully imported %s %s %s";

    private final StringBuilder sb;
    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, @Qualifier("gson") Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
        this.sb = new StringBuilder();
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importSellers() throws IOException {
        SellerImportDTO[] sellerImportDTOS = gson.fromJson(readSellersFromFile(), SellerImportDTO[].class);

        for (SellerImportDTO s : sellerImportDTOS) {
            // Validate the seller DTO and check if the last name already exists in the database
            if (!validationUtil.isValid(s) || sellerRepository.findByLastNameIgnoreCase(s.getLastName().trim()).isPresent()) {
                sb.append(String.format(INVALID_TEMPLATE, "seller")).append(System.lineSeparator());
                continue;  // Skip to the next seller if invalid or duplicate
            }

            // Map valid DTO to the Seller entity
            Seller seller = modelMapper.map(s, Seller.class);

            // Save the valid seller to the database
            sellerRepository.saveAndFlush(seller);

            // Append success message
            sb.append(String.format(SUCCESSFUL_IMPORT_TEMPLATE,
                            seller.getClass().getSimpleName().toLowerCase(),
                            seller.getFirstName(),
                            seller.getLastName()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }


}
