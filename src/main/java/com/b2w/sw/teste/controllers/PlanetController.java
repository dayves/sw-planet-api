package com.b2w.sw.teste.controllers;

import com.b2w.sw.teste.models.Planet;
import com.b2w.sw.teste.repository.PlanetRepository;
import com.b2w.sw.teste.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;
    @Autowired
    private PlanetRepository planetRepository;

    @GetMapping("")
    public List<Planet> index() {
        return planetRepository.findAll();
    }

    @PostMapping("")
    public Planet create(@RequestBody Planet planet) {
        return planetService.create(planetRepository, planet);
    }
}
