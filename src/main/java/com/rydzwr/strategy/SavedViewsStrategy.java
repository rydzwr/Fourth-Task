package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;
import com.rydzwr.model.Names;
import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.model.SupportedNames;
import com.rydzwr.model.Target;
import com.rydzwr.service.TargetValueResolver;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SupportedNames(value = {"david", "hal"})
public class SavedViewsStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) {
        return TargetValueResolver.getInstance().resolve(nameJson);
    }
}
