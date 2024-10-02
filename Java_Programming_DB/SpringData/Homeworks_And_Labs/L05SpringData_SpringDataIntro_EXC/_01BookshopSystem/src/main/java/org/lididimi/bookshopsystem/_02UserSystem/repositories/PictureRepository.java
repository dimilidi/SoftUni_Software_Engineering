package org.lididimi.bookshopsystem._02UserSystem.repositories;

import org.lididimi.bookshopsystem._02UserSystem.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}