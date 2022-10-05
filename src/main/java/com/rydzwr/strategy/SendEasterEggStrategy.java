package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;
import com.rydzwr.exception.EasterEggException;
import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.model.SupportedNames;

@SupportedNames(value = "Johny")
public class SendEasterEggStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) {
        throw new EasterEggException("I am a Teapot. You tried to use a teapot to brew coffee.");
    }
}
