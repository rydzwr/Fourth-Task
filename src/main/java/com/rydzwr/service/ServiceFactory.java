package com.rydzwr.service;

import com.rydzwr.strategy.CommonNameStrategy;
import com.rydzwr.strategy.SendErrorStrategy;
import com.rydzwr.strategy.SendMethodStrategy;
import org.springframework.stereotype.Service;

@Service
public class ServiceFactory {
    public SendMethodStrategy chooseStrategy(String name) {
        return new CommonNameStrategy();
    }
}
