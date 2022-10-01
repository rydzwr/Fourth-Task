package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;

public class CommonNameStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) {
        return nameJson;
    }

    @Override
    public boolean applies(NameJson nameJson) {
        return false;
    }
}
