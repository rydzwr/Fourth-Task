package com.rydzwr.model;

import com.rydzwr.dto.NameJson;

public interface SendMethodStrategy {
    NameJson buildResponse(NameJson nameJson);
}
