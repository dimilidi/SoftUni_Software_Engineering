package entities._01Gringotts;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposit extends BaseEntity {

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(name = "notes", columnDefinition = "TEXT", length = 1000)
    private String notes;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private Short magicWandSize;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date", columnDefinition = "DATETIME")
    private String depositStartDate;

    @Column(name = "deposit_amount", columnDefinition = "DECIMAL(19, 2)")
    private BigDecimal depositAmount;

    @Column(name = "deposit_interest", columnDefinition = "DECIMAL(19, 2)")
    private BigDecimal depositInterest;

    @Column(name = "deposit_charge", columnDefinition = "DECIMAL(19, 2)")
    private BigDecimal depositCharge;

    @Column(name = "deposit_expiration_date", columnDefinition = "DATETIME")
    private String depositExpirationDate;

    @Column(name = "is_deposit_expired", columnDefinition = "TINYINT")
    private Boolean isDepositExpired;

    protected WizardDeposit() {}

    public WizardDeposit(String lastName, int age) {
        this();
        this.lastName = lastName;
        this.age = age;
    }
}
