package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(length = 100)
    private String username;

    @Column(length = 100)
    private String password;

    @Column
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(columnDefinition = "DECIMAL (19, 2)")
    private BigDecimal balance;

    @OneToMany(mappedBy = "user")
    private Set<Bet> bet;

    public User() {}
}