package softuni.exam.models.dto.SelllerImportDTO.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceImportDTO {

    @Size(min = 2, max = 20)
    @NotNull
    @XmlElement
    private String brand;

    @XmlElement(name = "device_type")
    private String deviceType;

    @Size(min = 1, max = 20)
    @NotNull
    @XmlElement
    private String model;

    @Positive
    @XmlElement
    private Double price;

    @Positive
    @XmlElement
    private Integer storage;

    @XmlElement(name = "sale_id")
    private Long saleId;
}
