package com.rydzwr.service;

import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.model.SupportedNames;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.*;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class SendStrategyService {
    private final Map<String, SendMethodStrategy> strategyMap;

    public SendStrategyService() {
        strategyMap = buildMap();
    }

    public Map<String, SendMethodStrategy> getMap() {
        return new HashMap<>(strategyMap);
    }

    private Map<String, SendMethodStrategy> buildMap() {
        Map<String, SendMethodStrategy> strategyMap = new HashMap<>();
        List<SendMethodStrategy> strategies = createInstances();
        for (SendMethodStrategy strategy : strategies) {
            if (strategy.getClass().isAnnotationPresent(SupportedNames.class)) {
                SupportedNames annotation = strategy.getClass().getAnnotation(SupportedNames.class);
                Arrays.stream(annotation.value()).forEach(name -> {
                    if (strategyMap.containsKey(name)) {
                        throw new IllegalArgumentException("Duplicate name in annotation!");
                    }
                    strategyMap.put(name, strategy);
                });
            }
        }
        return strategyMap;
    }

    @SneakyThrows
    private List<Class<?>> getClasses() {
        List<Class<?>> out = new ArrayList<>();
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        @SuppressWarnings("SpellCheckingInspection")
        String packageName = "com.rydzwr.strategy";
        Set<BeanDefinition> classes = provider.findCandidateComponents(packageName);
        for (BeanDefinition bean : classes) {
            Class<?> clazz = Class.forName(bean.getBeanClassName());
            out.add(clazz);
        }
        return out;
    }

    private List<SendMethodStrategy> createInstances() {
        return getClasses()
                .stream()
                .filter(SendMethodStrategy.class::isAssignableFrom)
                .map(s -> {
                    try {
                        return (SendMethodStrategy) Class.forName(s.getName()).getConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(toList());
    }
}
