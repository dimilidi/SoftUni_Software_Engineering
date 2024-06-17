package com.dictionaryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.dictionaryapp.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
