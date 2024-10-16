package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SelllerImportDTO.device.DeviceExportDTO;
import softuni.exam.models.dto.SelllerImportDTO.device.DeviceImportDTO;
import softuni.exam.models.dto.SelllerImportDTO.device.DeviceImportWrapperDTO;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.enums.DeviceTypeEnum;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class DeviceServiceImpl implements DeviceService {
    public static final String URL = "src/main/resources/files/xml/devices.xml";
    public static final Path FILE_PATH = Path.of(URL);
    public static final String XML_EXPORT_TEMPLATE = "Device brand: %s%n   *Model: %s%n   **Storage: %d%n   ***Price: %.2f%n";
    private final String INVALID_TEMPLATE = "Invalid %s";
    private final String SUCCESSFUL_IMPORT_TEMPLATE = "Successfully imported %s of type %s with brand %s";

    private final StringBuilder sb;
    private final DeviceRepository deviceRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;

    public DeviceServiceImpl(
            DeviceRepository deviceRepository,
            XmlParser xmlParser,
            ValidationUtil validationUtil,
            SaleRepository saleRepository,
            ModelMapper modelMapper
    ) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.sb = new StringBuilder();
        this.deviceRepository = deviceRepository;
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String importDevices() throws IOException, JAXBException {
        DeviceImportWrapperDTO deviceImportWrapperDTO = xmlParser.fromFile(URL, DeviceImportWrapperDTO.class);

        List<DeviceImportDTO> deviceDTOS = deviceImportWrapperDTO.getDevices();

        for (DeviceImportDTO deviceDTO : deviceDTOS) {
            if (!validationUtil.isValid(deviceDTO)
                    || deviceRepository.findByBrandAndModel(deviceDTO.getBrand(), deviceDTO.getModel()).isPresent()
                    || saleRepository.findById(deviceDTO.getSaleId()).isEmpty()) {
                sb.append(String.format(INVALID_TEMPLATE, "device")).append(System.lineSeparator());
                continue;
            }

            Device device = modelMapper.map(deviceDTO, Device.class);
            device.setSale(saleRepository.findById(deviceDTO.getSaleId()).orElse(null));

            deviceRepository.saveAndFlush(device);
            sb.append(String.format(SUCCESSFUL_IMPORT_TEMPLATE,
                            device.getClass().getSimpleName().toLowerCase(),
                            device.getDeviceType().toString(),
                            device.getBrand()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String exportDevices() {
        List<Device> devices = deviceRepository.findByDeviceTypeAndPriceLessThanAndStorageGreaterThanEqual(
                DeviceTypeEnum.SMART_PHONE,
                1000,
                128).orElseThrow(() -> new NoSuchElementException("No device found"));

        devices
                .stream()
                .map(d -> modelMapper.map(d, DeviceExportDTO.class))
                .sorted(Comparator.comparing(d -> d.getBrand().toLowerCase()))
                .forEach(sb::append);

       // Use entity By Limited time (but not recommended):

    /* devices.sort(Comparator.comparing(d -> d.getBrand().toLowerCase()));

        devices.forEach(d -> sb.append(String.format(Locale.US, XML_EXPORT_TEMPLATE,
                        d.getBrand(),
                        d.getModel(),
                        d.getStorage(),
                        d.getPrice()
                )));*/
        return sb.toString().trim();
    }
}
