package softuni.exam.models.dto.SelllerImportDTO.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceExportDTO {
    public static final String XML_EXPORT_TEMPLATE = "Device brand: %s%n   *Model: %s%n   **Storage: %d%n   ***Price: %.2f%n";

    private String brand;
    private String model;
    private int storage;
    private double price;

    @Override
    public String toString() {
        return String.format(Locale.US, XML_EXPORT_TEMPLATE,
                brand,
                model,
                storage,
                price
        );
    }
}

