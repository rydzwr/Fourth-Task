package com.rydzwr.strategy;

import com.rydzwr.dto.NameJson;
import com.rydzwr.model.Names;
import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.model.SupportedNames;
import com.rydzwr.model.Target;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SupportedNames(value = {"david", "hal"})
public class SavedViewsStrategy implements SendMethodStrategy {

    @Override
    public NameJson buildResponse(NameJson nameJson) throws NoSuchFieldException, IllegalAccessException {
        Field field = Names.class.getDeclaredField(nameJson.getValue());
        Target annotation = field.getAnnotation(Target.class);
        return new NameJson(annotation.value(), (String) field.get(null));
    }

    @Override
    public boolean applies(NameJson nameJson) {
        SupportedNames annotation = this.getClass().getAnnotation(SupportedNames.class);
        List<String> savedViews = Arrays.stream(annotation.value()).collect(Collectors.toList());
        return savedViews.contains(nameJson.getValue());
    }
}
