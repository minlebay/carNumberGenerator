package com.minlebay.service;

import com.minlebay.dao.CarNumberDao;
import com.minlebay.models.CarNumber;
import com.minlebay.util.CarNumberGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class CarNumberServiceImpl implements CarNumberService {

    private static final String REGION = " 116 RUS";
    private final Random random = new Random();
    private final CarNumberDao carNumberDao;
    private final CarNumberGenerator carNumberGenerator;

    public CarNumberServiceImpl(CarNumberDao carNumberDao, CarNumberGenerator carNumberGenerator) {
        this.carNumberDao = carNumberDao;
        this.carNumberGenerator = carNumberGenerator;
    }

    @Override
    @Transactional
    public String getNext() {

        CarNumber lastCarNumber = carNumberDao.getLastCarNumber();
        if (lastCarNumber == null) {
            CarNumber newCarNumber = new CarNumber();
            newCarNumber.setPrefix('А');
            newCarNumber.setNum(0);
            newCarNumber.setSuffix('А');
            newCarNumber.setPostfix('А');
            newCarNumber.setRepresentation("А000АА");
            carNumberDao.add(newCarNumber);
            return "A000AA" + REGION;
        }

        CarNumber newCarNumber = null;
        while (newCarNumber == null
                || carNumberDao.getCarNumberByRepresentation(newCarNumber.getRepresentation()) != null) {
            newCarNumber = carNumberGenerator.getNextCarNumber(lastCarNumber);
        }
        carNumberDao.add(newCarNumber);
        return newCarNumber.getRepresentation() + REGION;
    }

    @Override
    @Transactional
    public String getRandom() {
        CarNumber newCarNumber = null;
        while (newCarNumber == null
                || carNumberDao.getCarNumberByRepresentation(newCarNumber.getRepresentation()) != null) {
            newCarNumber = carNumberGenerator.getRandomCarNumber(random);
        }
        carNumberDao.add(newCarNumber);
        return newCarNumber.getRepresentation() + REGION;
    }
}
