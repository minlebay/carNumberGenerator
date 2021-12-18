package com.minlebay.dao;

import com.minlebay.models.CarNumber;

import java.util.List;

public interface CarNumberDao {
    List<CarNumber> getAllCarNumbers();

    CarNumber getLastCarNumber();

    CarNumber getCarNumberByRepresentation(String representation);

    void add(CarNumber newCarNumber);
}
