package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;
import com.rydzwr.exception.MissingParameterException;
import com.rydzwr.model.SendMethodStrategy;

public class SendErrorStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) {
        throw new MissingParameterException("Missing name parameter");
    }

    @Override
    public boolean applies(NameJson nameJson) {
        return nameJson.getValue() == null;
    }
}
