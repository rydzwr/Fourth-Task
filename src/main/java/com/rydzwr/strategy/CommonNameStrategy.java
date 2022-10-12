package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;
import com.rydzwr.model.SendMethodStrategy;

public class CommonNameStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) {
        return nameJson;
    }
}
