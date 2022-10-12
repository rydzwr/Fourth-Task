package com.rydzwr.service;

import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.model.SupportedNames;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.*;
import java.util.regex.Pattern;

public class SendStrategyService {
    private final Map<String, SendMethodStrategy> strategyMap;

    public SendStrategyService() throws Exception {
        strategyMap = buildMap();
    }

    public Map<String, SendMethodStrategy> getMap() {
        return new HashMap<>(strategyMap);
    }

    private Map<String, SendMethodStrategy> buildMap() throws Exception {
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

    private List<Class<?>> getClasses() throws ClassNotFoundException {
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

    private List<SendMethodStrategy> createInstances() throws Exception {
        List<Class<?>> strategies = getClasses();
        List<SendMethodStrategy> out = new ArrayList<>();
        try {
            for (int i = 0; i < strategies.size(); i++) {
                if (!SendMethodStrategy.class.isAssignableFrom(strategies.get(i))) {
                    strategies.remove(i);
                }
            }
            for (Class<?> strategy : strategies) {
                SendMethodStrategy sendMethodStrategy = (SendMethodStrategy) Class.forName(strategy.getName()).getConstructor().newInstance();
                out.add(sendMethodStrategy);
            }
            return out;
        } catch (Exception e) {
            throw new Exception("Internal Server Error -> Couldn't create instances");
        }
    }
}
