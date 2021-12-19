package com.minlebay.api.config;

import com.github.database.rider.spring.DBRiderTestExecutionListener;
import org.springframework.core.Ordered;

public class CarNumberDBRiderTestExecutionListener extends DBRiderTestExecutionListener {

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
