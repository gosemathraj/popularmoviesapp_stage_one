package com.gosemathraj.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by raj on 8/3/16.
 */
public class MovieDescription extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Movies movieIntent = (Movies) intent.getSerializableExtra("Movie");

        setContentView(R.layout.movie_details);

        ImageView image = (ImageView)findViewById(R.id.detail_image);
        TextView title = (TextView)findViewById(R.id.detail_title);
        TextView rate = (TextView)findViewById(R.id.detail_popularity);
        TextView release = (TextView)findViewById(R.id.detail_release_date);
        TextView overview = (TextView)findViewById(R.id.detail_overview);

        Picasso.with(this).load("https://image.tmdb.org/t/p/w185" + movieIntent.getPoster_path())
                .into(image);

        title.setText(movieIntent.getOriginal_title());
        rate.setText(movieIntent.getVote_average());
        release.setText(movieIntent.getRelease_date());
        overview.setText(movieIntent.getOverview());



    }
}
