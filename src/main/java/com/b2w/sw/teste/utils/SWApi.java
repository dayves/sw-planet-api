package com.b2w.sw.teste.utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SWApi {
    @GET("planets")
    Call<SearchResponse> search(@Query("search") String planetName);
}
