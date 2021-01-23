package com.b2w.sw.teste.repository;

import com.b2w.sw.teste.models.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PlanetRepository  extends MongoRepository<Planet, String> {
    public Planet findOneByName(String name);
    public Optional<List<Planet>> findAllByName(String name);
}
