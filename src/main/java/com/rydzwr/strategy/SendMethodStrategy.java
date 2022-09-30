package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;

public interface SendMethodStrategy {
    NameJson buildResponse(NameJson nameJson);

    boolean applies(String name);
}
