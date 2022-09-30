package com.rydzwr.controller;

import com.rydzwr.dto.NameJson;
import com.rydzwr.service.ServiceFactory;
import com.rydzwr.strategy.SendMethodStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/validator")
public class NameController {

    private final ServiceFactory factory;

    public NameController(ServiceFactory factory) {
        this.factory = factory;
    }

    @PostMapping
    public ResponseEntity<NameJson> validateName(@RequestBody NameJson nameJson) {
        SendMethodStrategy service = factory.chooseStrategy(nameJson.getValue());
        NameJson response = service.buildResponse(nameJson);
        return ResponseEntity.ok(response);
    }
}
