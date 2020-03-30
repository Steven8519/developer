package com.devhubsocial.core.developer.controllers;

import com.devhubsocial.core.developer.domain.Developer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/developers")
public class DeveloperController {

    @GetMapping
    public List<Developer> getAllDevelopers() {
        return List.of(
            new Developer("1", "Sam", "Smith", "Python Developer")
        );
    }

}
