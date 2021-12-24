package com.minlebay.util;

import com.minlebay.models.CarNumber;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

@Service
public class CarNumberGenerator {

    public CarNumber getNextCarNumber(CarNumber lastCarNumber) {

        CarNumber newCarNumber = new CarNumber();
        TreeMap<Integer, Character> alphabet = Alphabet.getLiterals();
        Integer prefixKey = 0;
        Integer suffixKey = 0;
        Integer postfixKey = 0;

        for (Map.Entry<Integer, Character> pair : alphabet.entrySet()) {
            if (lastCarNumber.getPrefix().equals(pair.getValue()))
                prefixKey = pair.getKey();
            if (lastCarNumber.getSuffix().equals(pair.getValue()))
                suffixKey = pair.getKey();
            if (lastCarNumber.getPostfix().equals(pair.getValue()))
                postfixKey = pair.getKey();
        }

        if (lastCarNumber.getNum() == 999) {
            newCarNumber.setNum(0);
            if (lastCarNumber.getPostfix().equals(alphabet.get(alphabet.lastKey()))) {
                newCarNumber.setPostfix(alphabet.get(alphabet.firstKey()));
                if (lastCarNumber.getSuffix().equals(alphabet.get(alphabet.lastKey()))) {
                    newCarNumber.setSuffix(alphabet.get(alphabet.firstKey()));
                    if (lastCarNumber.getPrefix().equals(alphabet.get(alphabet.lastKey()))) {
                        newCarNumber.setPrefix(alphabet.get(alphabet.firstKey()));
                    } else {
                        newCarNumber.setPrefix(alphabet.get(prefixKey + 1));
                    }
                } else {
                    newCarNumber.setPrefix(lastCarNumber.getPrefix());
                    newCarNumber.setSuffix(alphabet.get(suffixKey + 1));
                }
            } else {
                newCarNumber.setPrefix(lastCarNumber.getPrefix());
                newCarNumber.setSuffix(lastCarNumber.getSuffix());
                newCarNumber.setPostfix(alphabet.get(postfixKey + 1));
            }
        } else {
            newCarNumber.setNum(lastCarNumber.getNum() + 1);
            newCarNumber.setPrefix(lastCarNumber.getPrefix());
            newCarNumber.setSuffix(lastCarNumber.getSuffix());
            newCarNumber.setPostfix(lastCarNumber.getPostfix());
        }

        String stringBuilder = newCarNumber.getPrefix() +
                new DecimalFormat("000").format(newCarNumber.getNum()) +
                newCarNumber.getSuffix() +
                newCarNumber.getPostfix();

        newCarNumber.setRepresentation(stringBuilder);

        return newCarNumber;
    }

    public CarNumber getRandomCarNumber(Random random) {

        TreeMap<Integer, Character> alphabet = Alphabet.getLiterals();
        CarNumber newCarNumber = new CarNumber();

        newCarNumber.setPrefix(alphabet.get(random.nextInt(11)));
        newCarNumber.setNum(random.nextInt(999));
        newCarNumber.setSuffix(alphabet.get(random.nextInt(11)));
        newCarNumber.setPostfix(alphabet.get(random.nextInt(11)));

        String stringBuilder = newCarNumber.getPrefix() +
                new DecimalFormat("000").format(newCarNumber.getNum()) +
                newCarNumber.getSuffix() +
                newCarNumber.getPostfix();

        newCarNumber.setRepresentation(stringBuilder);

        return newCarNumber;
    }
}