package org.lididimi.bookshopsystem._02UserSystem.services.impl;

import org.lididimi.bookshopsystem._02UserSystem.entities.Town;
import org.lididimi.bookshopsystem._02UserSystem.repositories.TownRepository;
import org.lididimi.bookshopsystem._02UserSystem.services.TownService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return this.townRepository.count() > 0;
    }

    @Override
    public void seedTowns(List<Town> towns) {
        this.townRepository.saveAll(towns);
    }

    @Override
    public Town getRandomTown() {

        final long count = this.townRepository.count();

        if (count != 0) {

            final long randomId = new Random().nextLong(1L, count) + 1;

            return this.townRepository.findById(randomId).orElseThrow(NoSuchElementException::new);
        }

        throw new RuntimeException("Towns table is empty!");
    }
}
