package com.b2w.sw.teste.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SWPlanet {
    @JsonProperty
    private String url;
    @JsonProperty
    private String name;
    @JsonProperty
    private ArrayList<String> films;

    SWPlanet() {}

    public int getId() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(0));
        }

        return 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getFilms() {
        return films;
    }
}
