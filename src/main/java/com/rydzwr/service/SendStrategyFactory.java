package com.rydzwr.service;


import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.model.SupportedNames;
import com.rydzwr.strategy.CommonNameStrategy;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;

public class SendStrategyFactory {
    private final Map<String, Class<SendMethodStrategy>> strategyMap;

    public SendStrategyFactory() throws ClassNotFoundException {
        strategyMap = buildMap();
    }

    public Map<String, Class<SendMethodStrategy>> getMap() {
        return new HashMap<>(strategyMap);
    }

    public Map<String, Class<SendMethodStrategy>> buildMap() throws ClassNotFoundException {
        Map<String, Class<SendMethodStrategy>> strategyMap = new HashMap<>();
        List<Class> strategies = getClasses();
        for (Class strategy : strategies) {
            if (strategy.isAnnotationPresent(SupportedNames.class)) {
                SupportedNames annotation = (SupportedNames) strategy.getAnnotation(SupportedNames.class);
                Arrays.stream(annotation.value()).forEach(name -> {
                    if (strategyMap.containsKey(name))
                        throw new IllegalArgumentException("Duplicate name in annotation!");
                    strategyMap.put(name, strategy);
                });
            }
        }
        return strategyMap;
    }

    public List<Class> getClasses() throws ClassNotFoundException {
        List<Class> out = new ArrayList<>();
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        String packageName = "com.rydzwr.strategy";
        Set<BeanDefinition> classes = provider.findCandidateComponents(packageName);
        for (BeanDefinition bean : classes) {
            Class<?> clazz = Class.forName(bean.getBeanClassName());
            out.add(clazz);
        }
        return out;
    }
}
