package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;
import com.rydzwr.exception.EasterEggException;
import com.rydzwr.model.SendMethodStrategy;

public class SendEasterEggStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) {
        throw new EasterEggException("I am a Teapot. You tried to use a teapot to brew coffee.");
    }

    @Override
    public boolean applies(NameJson nameJson) {
        return nameJson.getValue().equals("Johny");
    }
}
