package com.cerezalab.marvelheroes.Api;

import com.cerezalab.marvelheroes.Models.Basic;
import com.cerezalab.marvelheroes.Models.Data;
import com.cerezalab.marvelheroes.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by LUIS on 20/05/2017.
 */

public interface Marvel {

    String BASE_URL = "https://gateway.marvel.com/";

    String API_KEY_KEY = "apikey";
    String API_KEY_VALUE = "88f0a81d5fa571bda29d83da1787f3c6";

    String TIME_STAMP_KEY = "ts";
    String TIME_STAMP_VALUE = "1";

    String HASH_KEY = "hash";
    String HASH_VALUE = "d59b5205e68e8b3d5eb0dbe1a5e05667";

    @GET("v1/public/series/{seriesId}/characters")
    Call<Basic<Data<ArrayList<SuperHero>>>> getHeroes(@Path("seriesId") int seriesId);


}
