package com.gosemathraj.popularmovies;

/**
 * Created by raj on 10/3/16.
 */
public class UrlConstants {

    private String api_key;
    private String url;
    private String popularity_url;
    private String highestRated_url;
    private String movies_url;

    public UrlConstants(){

        api_key = ""; //Insert API KEY
        url = "https://api.themoviedb.org/3/discover/movie?api_key=";
        movies_url = url + api_key;
        popularity_url = url + api_key + "&sort_by=popularity.desc";
        highestRated_url = url + api_key + "&sort_by=vote_average.desc";
    }

    public String getMovies_url() {
        return movies_url;
    }

    public void setMovies_url(String movies_url) {
        this.movies_url = movies_url;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPopularity_url() {
        return popularity_url;
    }

    public void setPopularity_url(String popularity_url) {
        this.popularity_url = popularity_url;
    }

    public String getHighestRated_url() {
        return highestRated_url;
    }

    public void setHighestRated_url(String highestRated_url) {
        this.highestRated_url = highestRated_url;
    }
}
