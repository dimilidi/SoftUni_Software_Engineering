package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.awt.*;
import java.math.BigDecimal;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    @Column
    private String name;

    @Column(columnDefinition = "BLOB")
    private String logo;

    @Column(length = 3)
    private String initials;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color", referencedColumnName = "id")
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color", referencedColumnName = "id")
    private Color secondaryKitColor;

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    @Column
    private BigDecimal budget;

    public Team() {}
}