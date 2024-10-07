package org.lididimi.jsonparsing._01ProductShop.repositories;

import org.lididimi.jsonparsing._01ProductShop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM product_shop.users ORDER BY RAND () LIMIT 1")
    Optional<User> getRandomEntity();

    Optional<List<User>> findAllBySellingProductsBuyerIsNotNullOrderByLastNameAscFirstNameAsc();
}
