package com.minlebay.service;

import com.minlebay.models.CarNumber;

import java.util.List;

public interface CarNumberService {
    List<String> getAllCarNumbers();

    String getNext();

    String getRandom();
}
