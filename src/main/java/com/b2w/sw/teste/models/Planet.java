package com.b2w.sw.teste.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "planet")
public class Planet {
    @Id
    public String id;
    public String name;
    public String climate;
    public String terrain;
    public int exhibitionCount;
    public int swApiId;

    public Planet() {}

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public void setSwApiId(int swApiId) {
        this.swApiId = swApiId;
    }

    public void setExhibitionCount(int exhibitionCount) {
        this.exhibitionCount = exhibitionCount;
    }
}
