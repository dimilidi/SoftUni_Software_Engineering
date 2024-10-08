package org.lididimi.jsonparsing._02CarDealer.servicies;

import org.lididimi.jsonparsing._02CarDealer.entities.Part;

import java.util.List;

public interface PartService {
    Part getPartById(long id);

    long getRandomPartId();

    boolean isPartRepositoryNotEmpty();

    void saveAll(List<Part> parts);
}
