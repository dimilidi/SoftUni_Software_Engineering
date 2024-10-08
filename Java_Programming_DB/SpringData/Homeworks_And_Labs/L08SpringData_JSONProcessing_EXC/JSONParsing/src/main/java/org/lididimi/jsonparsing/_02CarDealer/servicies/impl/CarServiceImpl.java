package org.lididimi.jsonparsing._02CarDealer.servicies.impl;

import aj.org.objectweb.asm.commons.Remapper;
import org.lididimi.jsonparsing._02CarDealer.entities.Car;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.car.CarDetailedInfoDTO;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.car.CarMakeDTO;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.car.CarWrapperDTO;
import org.lididimi.jsonparsing._02CarDealer.repositories.CarRepository;
import org.lididimi.jsonparsing._02CarDealer.servicies.CarService;
import org.lididimi.jsonparsing._02CarDealer.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.lididimi.jsonparsing._01ProductShop.utils.Utils.writeJsonIntoFile;
import static org.lididimi.jsonparsing._02CarDealer.constants.Constants.*;
import static org.lididimi.jsonparsing._02CarDealer.constants.Paths.ALL_TOYOTA_CARS_FILE_PATH;
import static org.lididimi.jsonparsing._02CarDealer.constants.Paths.CARS_AND_PARTS_FILE_PATH;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final Random random;
    private final ModelMapper mapper;

    public CarServiceImpl(CarRepository carRepository, Random random, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.random = random;
        this.mapper = mapper;
    }

    @Override
    public Car getCarById(long id) {
        return this.carRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public boolean isCarRepositoryNotEmpty() {
        return this.carRepository.count() != 0;
    }

    @Override
    public boolean isCarRepositoryEmpty() {
        return this.carRepository.count() == 0;
    }

    @Override
    public void saveCars(List<Car> cars) {
        this.carRepository.saveAllAndFlush(cars);
    }

    @Override
    public long getRandomCarId() {
        return Utils.getRandomEntityId(carRepository, random);
    }

    @Override
    public String findAllCarsFromMakeToyota() throws IOException {
        final List<CarMakeDTO> carsToyotaDTO =
                this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(TOYOTA)
                        .orElseThrow(NoSuchElementException::new)
                        .stream()
                        .map(car -> this.mapper.map(car, CarMakeDTO.class))
                        .toList();

        writeJsonIntoFile(carsToyotaDTO, ALL_TOYOTA_CARS_FILE_PATH);

        return ALL_TOYOTA_CARS_SAVED_SUCCESSFULLY;
    }

    @Override
    public String findAllCarsAndTheirParts() throws IOException {
        final List<CarWrapperDTO> carDetailedInfoDTOS =
                this.carRepository.findAll()
                        .stream()
                        .map(car -> this.mapper.map(car, CarWrapperDTO.class))
                        .toList();

        writeJsonIntoFile(carDetailedInfoDTOS, CARS_AND_PARTS_FILE_PATH);

        return CARS_AND_PARTS_SAVED_SUCCESSFULLY;
    }

}
