package com.rydzwr.factory;

import com.rydzwr.exception.ServiceFactoryException;
import com.rydzwr.model.SendMethodStrategy;
import com.rydzwr.service.SendStrategyService;
import com.rydzwr.strategy.CommonNameStrategy;
import com.rydzwr.strategy.SendErrorStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ServiceFactory {

    public SendMethodStrategy getStrategy(String name) {
        if (name == null) return new SendErrorStrategy();
        try {
            SendStrategyService service = new SendStrategyService();
            Map<String, SendMethodStrategy> strategyMap = service.getMap();
            if (strategyMap.containsKey(name)) {
                return strategyMap.get(name);
            }
        } catch (Exception e) {
            throw new ServiceFactoryException("Unable to choose proper strategy");
        }
        return new CommonNameStrategy();
    }
}
