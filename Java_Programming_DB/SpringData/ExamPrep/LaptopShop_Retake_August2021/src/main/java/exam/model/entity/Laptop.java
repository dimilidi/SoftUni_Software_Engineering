package exam.model.entity;

import exam.model.base.BaseEntity;
import exam.model.entity.enums.WarrantyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "laptops")
public class Laptop extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String macAddress;

    @Column(name = "cpu_speed", nullable = false)
    private Float cpuSpeed;

    @Column(nullable = false)
    private Integer ram;

    @Column(nullable = false)
    private Integer storage;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "warranty_type", nullable = false)
    private WarrantyTypeEnum warrantyType;

    @ManyToOne(optional = false)
    private Shop shop;

    @Override
    public String toString() {
        return "Laptop - " + macAddress + "\n" +
                "*Cpu speed - " + cpuSpeed + "\n" +
                "**Ram - " + ram + "\n" +
                "***Storage - " + storage + "\n" +
                "****Price - " + price + "\n" +
                "#Shop name - " + shop.getName() + "\n" +
                "##Town - " + shop.getTown().getName() + "\n";
    }
}
