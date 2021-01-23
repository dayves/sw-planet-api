package com.b2w.sw.teste.repository;

import com.b2w.sw.teste.models.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository  extends MongoRepository<Planet, String> {
    public Planet findOneByName(String name);
}
