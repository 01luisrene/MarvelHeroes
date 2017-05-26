package com.cerezalab.marvelheroes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cerezalab.marvelheroes.Models.SuperHero;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by LUIS on 25/05/2017.
 */

public class HeroGridAdapter extends RecyclerView.Adapter<HeroGridAdapter.MyViewHolder>{

    ArrayList<SuperHero> superHeroArrayList;
    Context context;
    HeroGridFragment.HeroClickListener heroClickListener;

    public HeroGridAdapter(ArrayList superHeroArrayList, Context context, HeroGridFragment.HeroClickListener heroClickListener){

        this.superHeroArrayList = superHeroArrayList;
        this.context = context;
        this.heroClickListener = heroClickListener;

    }

    @Override
    public HeroGridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.hero_grid_item, parent, false);

        return new HeroGridAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeroGridAdapter.MyViewHolder holder, int position) {

        SuperHero superHero = superHeroArrayList.get(position);

        holder.bind(context, superHero, heroClickListener);

    }

    @Override
    public int getItemCount() {
        return superHeroArrayList.size();
    }

    static public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView heroPictureImageView;
        public TextView heroDetailNameTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            heroPictureImageView = (ImageView) itemView.findViewById(R.id.heroPictureImageView);
            heroDetailNameTextView = (TextView) itemView.findViewById(R.id.heroDetailNameTextView);

        }

        public void bind(Context context, final SuperHero superHero, final HeroGridFragment.HeroClickListener heroClickListener){

            heroDetailNameTextView.setText(superHero.getName());
            Picasso.with(context).load(superHero.getThumbnail().getFullPath()).into(heroPictureImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    heroClickListener.onHeroClicked(superHero);
                }
            });
        }

    }

}
