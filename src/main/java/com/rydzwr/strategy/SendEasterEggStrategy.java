package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;

public class SendEasterEggStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) {
        return null;
    }

    @Override
    public boolean applies(String name) {
        return false;
    }
}
