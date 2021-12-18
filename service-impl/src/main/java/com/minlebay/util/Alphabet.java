package com.minlebay.util;

import com.minlebay.models.CarNumber;

import java.util.Map;
import java.util.TreeMap;

public class Alphabet {

    public static TreeMap<Integer, Character> getLiterals() {
        TreeMap<Integer,Character> literals = new TreeMap<>();
        literals.put(0,'А');
        literals.put(1,'В');
        literals.put(2,'Е');
        literals.put(3,'К');
        literals.put(4,'М');
        literals.put(5,'Н');
        literals.put(6,'О');
        literals.put(7,'Р');
        literals.put(8,'С');
        literals.put(9,'Т');
        literals.put(10,'У');
        literals.put(11,'Х');
        return literals;
    }
}
