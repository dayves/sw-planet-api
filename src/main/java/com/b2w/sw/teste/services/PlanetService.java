package com.b2w.sw.teste.services;

import com.b2w.sw.teste.models.Planet;
import com.b2w.sw.teste.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {
    @Autowired
    private PlanetRepository planetRepository;

    public Planet create(PlanetRepository repository, Planet planet) {
        planet.setExhibitionCount(10);
        planet.setSwApiId(10);
        return repository.save(planet);
    }
}
