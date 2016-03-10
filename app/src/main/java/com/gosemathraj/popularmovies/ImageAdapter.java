package com.gosemathraj.popularmovies;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by raj on 7/3/16.
 */
public class ImageAdapter extends BaseAdapter {

    Movies[] movies;
    private Context context;

    public ImageAdapter(Context context,Movies movies[]){
        this.context=context;
        this.movies=movies;
    }



    @Override
    public int getCount() {
        return movies.length;
    }

    @Override
    public Object getItem(int position) {
        return movies[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(context);
        imageView.setAdjustViewBounds(true);

        Picasso.with(context).load("https://image.tmdb.org/t/p/w185"+movies[position].getPoster_path()).resize(185,278).into(imageView);
        return imageView;

        //ImageView imageView;

        // Will be null if it's not recycled. Will initialize ImageView if new.
        /*if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w185"+movies[position].getPoster_path())
                .resize(185, 278)
                .into(imageView);

        return imageView;*/

    }
}
