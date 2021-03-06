package com.cerezalab.marvelheroes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cerezalab.marvelheroes.Models.SuperHero;

import java.util.ArrayList;


public class HeroListFragment extends Fragment {

    public static final String HERO_DETAIL_FRAGMENT = "HERO_DETAIL_FRAGMENT";
    public static final String SUPER_HERO = "SUPER_HERO";
    private RecyclerView recyclerView;

    public static final String TAG = HeroListFragment.class.getSimpleName();

    ArrayList<SuperHero> superHeroes;

    public HeroListFragment() {
        // Required empty public constructor
    }

    public interface HeroClickListener{

        void onHeroClicked(SuperHero superHero);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){

            superHeroes = getArguments().getParcelableArrayList(MainActivity.HERO_LIST);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hero_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.superHeroesRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        HeroListAdapter heroListAdapter = new HeroListAdapter(superHeroes, getContext(), new HeroClickListener() {
            @Override
            public void onHeroClicked(SuperHero superHero) {

                //Cambiar de fragment al HeroDatailFragment
                goToHeroDetailsFragment(superHero);

            }
        });

        recyclerView.setAdapter(heroListAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    private void goToHeroDetailsFragment(SuperHero superHero) {

        HeroDetailFragment heroDetailFragment = new HeroDetailFragment();

        Bundle bundle = new Bundle();

        bundle.putParcelable(SUPER_HERO, superHero);

        heroDetailFragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.placeholder,heroDetailFragment, HERO_DETAIL_FRAGMENT);

        fragmentTransaction.addToBackStack(HERO_DETAIL_FRAGMENT);

        fragmentTransaction.commit();
        
    }

}
