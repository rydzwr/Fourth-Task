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
            Map<String, Class<SendMethodStrategy>> strategyMap = factory.getMap();
            if (strategyMap.containsKey(name)) {
                return (SendMethodStrategy) Class.forName(strategyMap.get(name).getName())
                        .getConstructor()
                        .newInstance();
            }
        }catch (Exception e) {
            throw new ServiceFactoryException("Unable to choose proper strategy");
        }
        return new CommonNameStrategy();
    }
}
