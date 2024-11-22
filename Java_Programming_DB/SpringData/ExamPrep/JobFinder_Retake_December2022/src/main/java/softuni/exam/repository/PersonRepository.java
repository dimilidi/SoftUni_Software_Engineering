package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findFirstByFirstName(String firstName);

    Optional<Person> findFirstByEmail(String email);

    Optional<Person> findFirstByPhone(String phone);
}
