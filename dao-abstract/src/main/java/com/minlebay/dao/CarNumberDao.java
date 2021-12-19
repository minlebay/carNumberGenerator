package com.minlebay.dao;

import com.minlebay.models.CarNumber;

public interface CarNumberDao {

    CarNumber getLastCarNumber();

    CarNumber getCarNumberByRepresentation(String representation);

    void add(CarNumber newCarNumber);
}
