package com.rydzwr.service;

import com.rydzwr.dto.NameJson;
import com.rydzwr.exception.TargetValueResolverException;
import com.rydzwr.model.Names;
import com.rydzwr.model.Target;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class TargetValueResolver {
    private static TargetValueResolver instance;

    public static TargetValueResolver getInstance() {
        if (instance == null) {
            return new TargetValueResolver();
        }
        return instance;
    }

    public NameJson resolve(NameJson nameJson) {
        try {
            Field field = Names.class.getDeclaredField(nameJson.getValue());
            Target annotation = field.getAnnotation(Target.class);
            return new NameJson(annotation.value(), (String) field.get(null));
        }catch (Exception e) {
            throw new TargetValueResolverException("Couldn't resolve field or annotation value");
        }
    }
}
