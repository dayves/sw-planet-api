package com.b2w.sw.teste.utils;

import com.b2w.sw.teste.models.Planet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {
    @JsonProperty
    private ArrayList<SWPlanet> results;

    SearchResponse() {}

    public ArrayList<SWPlanet> getResults() {
        return results;
    }
}
