package com.rydzwr.controller;

import com.rydzwr.dto.NameJson;
import com.rydzwr.service.SendStrategyFactory;
import com.rydzwr.model.SendMethodStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/api/v1")
public class NameController {

    private final SendStrategyFactory factory;

    public NameController(SendStrategyFactory factory) {
        this.factory = factory;
    }

    @PostMapping(value = "/validator")
    public ResponseEntity<NameJson> validateName(@RequestBody NameJson nameJson) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        SendMethodStrategy service = factory.getStrategy(nameJson.getValue());
        NameJson response = service.buildResponse(nameJson);
        return ResponseEntity.ok(response);
    }
}
