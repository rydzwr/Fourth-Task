package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;
import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.model.SupportedNames;
import com.rydzwr.service.TargetValueResolver;

@SupportedNames(value = {"david", "hal"})
public class SavedViewsStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) {
        return TargetValueResolver.getInstance().resolve(nameJson);
    }
}
