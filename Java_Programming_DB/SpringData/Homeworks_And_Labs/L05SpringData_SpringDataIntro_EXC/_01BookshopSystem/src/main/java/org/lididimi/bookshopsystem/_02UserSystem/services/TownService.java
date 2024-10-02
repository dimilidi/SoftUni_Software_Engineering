package org.lididimi.bookshopsystem._02UserSystem.services;

import org.lididimi.bookshopsystem._02UserSystem.entities.Town;

import java.util.List;

public interface TownService {
    boolean isDataSeeded();

    void seedTowns(List<Town> towns);

    Town getRandomTown();
}
