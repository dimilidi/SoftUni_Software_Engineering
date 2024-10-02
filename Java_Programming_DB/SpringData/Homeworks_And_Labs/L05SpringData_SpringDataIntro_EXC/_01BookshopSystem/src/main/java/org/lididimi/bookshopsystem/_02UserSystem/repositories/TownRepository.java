package org.lididimi.bookshopsystem._02UserSystem.repositories;

import org.lididimi.bookshopsystem._02UserSystem.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
}
