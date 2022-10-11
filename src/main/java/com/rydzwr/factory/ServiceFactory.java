package com.rydzwr.factory;

import com.rydzwr.exception.ServiceFactoryException;
import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.service.SendStrategyFactory;
import com.rydzwr.strategy.CommonNameStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ServiceFactory {

    public SendMethodStrategy getStrategy(String name) {
        try {
            SendStrategyFactory factory = new SendStrategyFactory();
            Map<String, SendMethodStrategy> strategyMap = factory.getMap();
            if (strategyMap.containsKey(name)) {
                return strategyMap.get(name);
            }
        } catch (Exception e) {
            throw new ServiceFactoryException("Unable to choose proper strategy");
        }
        return new CommonNameStrategy();
    }
}
