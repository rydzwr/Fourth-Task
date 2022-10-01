package com.rydzwr.service;

import com.rydzwr.dto.NameJson;
import com.rydzwr.strategy.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class ServiceFactory {
    private static List<SendMethodStrategy> strategyList = asList(
            new SendEasterEggStrategy(),
            new SavedViewsStrategy()
    );

    public SendMethodStrategy chooseStrategy(NameJson nameJson) {
        if (nameJson.getValue() == null) {
            return new SendErrorStrategy();
        }
        return strategyList
                .stream()
                .filter(s -> s.applies(nameJson))
                .findFirst()
                .orElse(new CommonNameStrategy());
    }
}
