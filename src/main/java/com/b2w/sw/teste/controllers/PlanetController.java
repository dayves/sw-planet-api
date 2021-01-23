package com.b2w.sw.teste.controllers;

import com.b2w.sw.teste.models.Planet;
import com.b2w.sw.teste.repository.PlanetRepository;
import com.b2w.sw.teste.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.springframework.util.StringUtils.capitalize;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;
    @Autowired
    private PlanetRepository planetRepository;

    @GetMapping("")
    public Optional<List<Planet>> index(@RequestParam(required = false) String name) {
        if (name != null) {
            return planetRepository.findAllByName(capitalize(name));
        } else {
            return Optional.of(planetRepository.findAll());
        }
    }

    @PostMapping("")
    public Planet create(@RequestBody Planet planet) throws IOException {
        return planetService.create(planetRepository, planet);
    }

    @GetMapping("/{id}")
    public Optional<Planet> show(@PathVariable String id) {
        return planetRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<Planet> destroy(@PathVariable String id) {
        Optional<Planet> planet = planetRepository.findById(id);
        planetRepository.deleteById(id);
        return planet;
    }
}
