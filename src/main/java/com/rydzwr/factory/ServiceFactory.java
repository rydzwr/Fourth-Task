package com.rydzwr.factory;

import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.service.SendStrategyFactory;
import com.rydzwr.strategy.CommonNameStrategy;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Service
public class ServiceFactory {

    public SendMethodStrategy getStrategy(String name) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SendStrategyFactory factory = new SendStrategyFactory();
        Map<String, Class<SendMethodStrategy>> strategyMap = factory.getMap();
        if (strategyMap.containsKey(name)) {
            return (SendMethodStrategy) Class.forName(strategyMap.get(name).getName())
                    .getConstructor()
                    .newInstance();
        }

        return new CommonNameStrategy();
    }
}
