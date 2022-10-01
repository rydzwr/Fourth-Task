package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;

public interface SendMethodStrategy {
    NameJson buildResponse(NameJson nameJson) throws NoSuchFieldException, IllegalAccessException;

    boolean applies(NameJson nameJson);
}
