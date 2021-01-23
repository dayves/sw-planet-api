package com.b2w.sw.teste.services;

import com.b2w.sw.teste.models.Planet;
import com.b2w.sw.teste.repository.PlanetRepository;
import com.b2w.sw.teste.utils.SWApi;
import com.b2w.sw.teste.utils.SWPlanet;
import com.b2w.sw.teste.utils.SearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public Planet create(PlanetRepository repository, Planet planet) {
        Planet repositoryPlanet = repository.findOneByName(planet.getName());
        System.out.println(repositoryPlanet);
        System.out.println(repositoryPlanet != null);

        if (repositoryPlanet != null && repositoryPlanet.getName().equals(planet.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already exists");
        }

        planet.setSwStatus(Planet.SWStatus.WAITING_SYNC);
        syncSWApi(repository, planet);

        return repository.save(planet);
    }

    private void syncSWApi(PlanetRepository repository, Planet planet) {
        try {
            URL url = new URL("https://swapi.dev/api/planets/?search=" + planet.getName().toLowerCase());

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://swapi.dev/api/")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

            SWApi service = retrofit.create(SWApi.class);

            Call<SearchResponse> call = service.search(planet.getName().toLowerCase());

            call.enqueue(new Callback<SearchResponse>() {
                @Override
                public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                    try {
                        SWPlanet foundedPlanet = response.body().getResults().get(0);

                        planet.setFilmsAppeared(foundedPlanet.getFilms().size());
                        planet.setSwApiId(foundedPlanet.getId());
                        planet.setSwStatus(Planet.SWStatus.SYNCED);

                        repository.save(planet);
                    } catch (Exception e) {
                        planet.setSwStatus(Planet.SWStatus.NOT_FOUND);
                        repository.save(planet);
                    }
                }

                @Override
                public void onFailure(Call<SearchResponse> call, Throwable throwable) {
                    syncSWApi(repository, planet);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

