package com.rydzwr.service;


import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.model.SupportedNames;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnnotationValueExtractor {
    private Map<SendMethodStrategy, List<String>> strategyMap = new HashMap<>();

    //TODO
    // add all strategies as a key to map using ....idk
    // then set their supported names as list as value
    // then get them in service factory using stream map and flatmap to choose proper strategy for given value

    public void buildMap() throws IOException {
        List<Class> strategies = findAllClassesUsingClassLoader("strategy");
        for (int i = 0; i < strategies.size(); i++) {
            if (strategies.get(i).isAnnotationPresent(SupportedNames.class)) {
                SupportedNames annotation = strategies.get(i).getClass().getAnnotation(SupportedNames.class);
                List<String> savedViews = Arrays.stream(annotation.value()).collect(Collectors.toList());
                strategyMap.put((SendMethodStrategy) strategies.get(i).cast(SendMethodStrategy.class), savedViews);
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
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
