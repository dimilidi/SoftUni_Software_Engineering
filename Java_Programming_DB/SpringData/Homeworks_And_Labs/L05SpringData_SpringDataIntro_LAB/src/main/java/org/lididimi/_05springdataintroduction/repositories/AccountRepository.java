package org.lididimi._05springdataintroduction.repositories;

import org.lididimi._05springdataintroduction.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}