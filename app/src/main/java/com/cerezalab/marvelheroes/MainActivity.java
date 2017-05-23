package com.cerezalab.marvelheroes;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cerezalab.marvelheroes.Api.MarvelService;
import com.cerezalab.marvelheroes.Models.Basic;
import com.cerezalab.marvelheroes.Models.Data;
import com.cerezalab.marvelheroes.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String HERO_LIST_FRAGMENT = "hero_list_fragment";
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int SUCCESS_CODE = 200;
    public static final String HERO_LIST = "hero_list";

    public  FrameLayout frameLayout;
    private ArrayList<SuperHero> superHeroes;

    public static final int AVENGERS_COMIC_ID = 354;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.placeholder);

        Call<Basic<Data<ArrayList<SuperHero>>>> superHeroesCall = MarvelService.getMarvelApi().getHeroes(AVENGERS_COMIC_ID);

        superHeroesCall.enqueue(new Callback<Basic<Data<ArrayList<SuperHero>>>>() {

            @Override
            public void onResponse(Call<Basic<Data<ArrayList<SuperHero>>>> call, Response<Basic<Data<ArrayList<SuperHero>>>> response) {


                if(response.code() == SUCCESS_CODE){

                    superHeroes = response.body().getData().getResults();

                    Toast.makeText(MainActivity.this, "Hero Name: " + superHeroes.get(0).getName(), Toast.LENGTH_SHORT).show();

                    //Bundle
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(HERO_LIST, superHeroes);

                    FragmentManager fragmentManager = getSupportFragmentManager();

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    HeroListFragment heroListFragment = new HeroListFragment();

                    heroListFragment.setArguments(bundle);

                    fragmentTransaction.add(R.id.placeholder, heroListFragment, HERO_LIST_FRAGMENT);

                    fragmentTransaction.commit();

                }else{

                    Log.d(TAG, "Error en la respuesta");

                }

            }

            @Override
            public void onFailure(Call<Basic<Data<ArrayList<SuperHero>>>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error en la llamada", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
