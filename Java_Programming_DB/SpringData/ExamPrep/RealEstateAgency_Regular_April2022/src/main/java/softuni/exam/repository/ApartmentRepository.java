package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.models.entity.enums.ApartmentTypeEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository  extends JpaRepository<Apartment, Long> {

    Optional<Apartment> findFirstByTown(Town town);

    Optional<Apartment>  findFirstByArea(Double area);

    Optional<Apartment> findFirstByTownAndArea(Town town, Double area);
}
