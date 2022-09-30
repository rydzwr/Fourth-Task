package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;

public class SavedViewsStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) {
        return null;
    }

    @Override
    public boolean applies(String name) {
        return false;
    }
}
