package org.lididimi.bookshopsystem._02UserSystem.repositories;

import org.lididimi.bookshopsystem._02UserSystem.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository  extends JpaRepository<Album, Long> {

}