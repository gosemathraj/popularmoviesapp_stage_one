package com.gosemathraj.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    public Movies movies[];
    public GridView gridView;

    public UrlConstants urlConstants = new UrlConstants();
    public String apiUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        apiUrl = urlConstants.getMovies_url();
        gridView = (GridView)findViewById(R.id.grid_view);

        FetchImages fetchImages = new FetchImages();
        fetchImages.execute();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), MovieDescription.class);
                Movies movie = (Movies) parent.getAdapter().getItem(position);
                // passing array index
                i.putExtra("Movie", movie);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        switch (item.getItemId()) {
            case R.id.popularity:
                //Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                callPopualarSort();
            case R.id.highest_rating:
                //Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                callHighestRatedSort();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void callPopualarSort(){

        apiUrl = urlConstants.getPopularity_url();
        FetchImages fetchImages = new FetchImages();
        fetchImages.execute();
    }

    public void callHighestRatedSort(){

        apiUrl = urlConstants.getHighestRated_url();
        FetchImages fetchImages = new FetchImages();
        fetchImages.execute();
    }

    public class FetchImages extends AsyncTask<String, Void, Movies[]>{

        @Override
        protected Movies[] doInBackground(String... params) {

                try {
                    movies = getmoviesArray(apiUrl);
                    return movies;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            return null;
        }


        @Override
        protected void onPostExecute(Movies[] movies) {

            super.onPostExecute(movies);
            gridView.setAdapter(new ImageAdapter(MainActivity.this, movies));
        }

        public Movies[] getmoviesArray(String url) throws IOException, JSONException {

            InputStream inputstream = new URL(url).openStream();
            BufferedReader input  = new BufferedReader(new InputStreamReader(inputstream));

            String mJson = readAll(input);
            JSONObject jsonObject = new JSONObject(mJson);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            int movies_length = jsonArray.length();

            Movies tempmovies[] = new Movies[movies_length];
            for(int i = 0;i < movies_length; i++){
                Movies m = new Movies();
                m.setOriginal_title(jsonArray.getJSONObject(i).getString("original_title"));
                m.setOverview(jsonArray.getJSONObject(i).getString("overview"));
                m.setPoster_path(jsonArray.getJSONObject(i).getString("poster_path"));
                m.setRelease_date(jsonArray.getJSONObject(i).getString("release_date"));
                m.setVote_average(jsonArray.getJSONObject(i).getString("vote_average"));
                tempmovies[i] = m;
            }
            return tempmovies;
        }

        public String readAll(BufferedReader rd) throws IOException{

            StringBuilder s = new StringBuilder();
            int count;

            while((count = (rd.read()))!= -1){
                s.append((char) count);
            }
            return s.toString();
        }


    }
}
