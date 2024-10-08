package org.lididimi.jsonparsing._02CarDealer.servicies.impl;

import org.lididimi.jsonparsing._02CarDealer.entities.Part;
import org.lididimi.jsonparsing._02CarDealer.repositories.PartRepository;
import org.lididimi.jsonparsing._02CarDealer.servicies.PartService;
import org.lididimi.jsonparsing._02CarDealer.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final Random random;

    public PartServiceImpl(PartRepository partRepository, Random random) {
        this.partRepository = partRepository;
        this.random = random;
    }

    @Override
    public Part getPartById(long id) {
        return partRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public long getRandomPartId() {
        return Utils.getRandomEntityId(partRepository, random);
    }

    @Override
    public boolean isPartRepositoryNotEmpty() {
        return partRepository.count() > 0;
    }

    @Override
    public void saveAll(List<Part> parts) {
        partRepository.saveAllAndFlush(parts);
    }
}
