package com.b2w.sw.teste.services;

import com.b2w.sw.teste.models.Planet;
import com.b2w.sw.teste.repository.PlanetRepository;
import com.b2w.sw.teste.utils.SWApi;
import com.b2w.sw.teste.utils.SWPlanet;
import com.b2w.sw.teste.utils.SearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

@Service
public class PlanetService {
    @Autowired
    private PlanetRepository planetRepository;

    public Planet create(PlanetRepository repository, Planet planet) throws IOException {
        URL url = new URL("https://swapi.dev/api/planets/?search=" + planet.getName().toLowerCase());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        SWApi service = retrofit.create(SWApi.class);

        Call<SearchResponse> call = service.search(planet.getName().toLowerCase());

       SWPlanet foundedPlanet = call.execute().body().getResults().get(0);

        System.out.println("response "+ foundedPlanet.getName());

        planet.setFilmsAppeared(foundedPlanet.getFilms().size());
        planet.setSwApiId(foundedPlanet.getId());

        return repository.save(planet);
    }
}

