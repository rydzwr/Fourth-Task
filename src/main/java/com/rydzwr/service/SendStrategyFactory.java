package com.rydzwr.service;


import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.model.SupportedNames;
import com.rydzwr.strategy.CommonNameStrategy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SendStrategyFactory {
    private Map<String, Class<SendMethodStrategy>> strategyMap = new HashMap<>();

    //TODO
    // add all strategies as a key to map using ....idk
    // then set their supported names as list as value
    // then get them in service factory using stream map and flatmap to choose proper strategy for given value

    public SendStrategyFactory() throws IOException {
        buildMap();
    }

    public SendMethodStrategy getStrategy(String name) {
        try {
            if (strategyMap.containsKey(name)) {
                Constructor<SendMethodStrategy> ctor = strategyMap.get(name).getConstructor(String.class);
                return ctor.newInstance(new Object[] {});
            }
        } catch (Exception e) { }

        return new CommonNameStrategy();
    }

    public void buildMap() throws IOException {
        List<Class> strategies = findAllClassesUsingClassLoader("strategy");
        for (Class strategy: strategies) {
            if (strategy.isAnnotationPresent(SupportedNames.class)) {
                SupportedNames annotation = strategy.getClass().getAnnotation(SupportedNames.class);
                Arrays.stream(annotation.value()).forEach(name -> {
                    if (strategyMap.containsKey(name))
                        throw new IllegalArgumentException("Duplicate name in annotation!");
                    strategyMap.put(name, strategy);
                });
            }
        }
    }

    public List<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .filter(Objects::nonNull)
                .filter(aClass -> aClass.isInstance(SendMethodStrategy.class))
                .collect(Collectors.toList());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
