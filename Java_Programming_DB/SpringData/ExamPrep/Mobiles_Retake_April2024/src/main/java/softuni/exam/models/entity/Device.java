package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.base.BaseEntity;
import softuni.exam.models.entity.enums.DeviceTypeEnum;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devices")
public class Device extends BaseEntity {

    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    private DeviceTypeEnum deviceType;

    @Column(nullable = false, unique = true)
    private String model;

    @Column
    private Double price;

    @Column
    private Integer storage;

    @ManyToOne
    private Sale sale;
}
