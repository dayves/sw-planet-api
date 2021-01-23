package com.b2w.sw.teste.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planet")
public class Planet {
    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private int filmsAppeared;
    private int swApiId;

    public Planet() {}

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public int getFilmsAppeared() {
        return filmsAppeared;
    }

    public int getSwApiId() {
        return swApiId;
    }

    public void setSwApiId(int swApiId) {
        this.swApiId = swApiId;
    }

    public void setFilmsAppeared(int filmsAppeared) {
        this.filmsAppeared = filmsAppeared;
    }
}
