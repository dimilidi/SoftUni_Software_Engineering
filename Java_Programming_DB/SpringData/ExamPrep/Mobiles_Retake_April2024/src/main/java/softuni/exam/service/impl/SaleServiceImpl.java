package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SelllerImportDTO.sale.SaleImportDTO;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.SaleRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SaleServiceImpl implements SaleService {

    private static final Path FILE_PATH = Path.of("src/main/resources/files/json/sales.json");
    private final String INVALID_TEMPLATE = "Invalid %s";
    private final String SUCCESSFUL_IMPORT_TEMPLATE = "Successfully imported %s with number %s";
    private final StringBuilder sb;

    private final SaleRepository saleRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final SellerRepository sellerRepository;

    public SaleServiceImpl(SaleRepository saleRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, SellerRepository sellerRepository) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.sb = new StringBuilder();
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/sales.json"));
    }

    @Override
    public String importSales() throws IOException {

        SaleImportDTO[] saleImportDTOS = gson.fromJson(readSalesFileContent(), SaleImportDTO[].class);

        for (SaleImportDTO saleImportDTO : saleImportDTOS) {
            if(!validationUtil.isValid(saleImportDTO) || saleRepository.findByNumber(saleImportDTO.getNumber()).isPresent()) {
                sb.append(String.format(INVALID_TEMPLATE, "sale")).append(System.lineSeparator());
                continue;
            }

            Sale sale = modelMapper.map(saleImportDTO, Sale.class);
            saleRepository.saveAndFlush(sale);
            sb.append(String.format(SUCCESSFUL_IMPORT_TEMPLATE,sale.getClass().getSimpleName().toLowerCase(), sale.getNumber())).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
