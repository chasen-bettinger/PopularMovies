package chasen.popularmovies;

import org.parceler.Parcel;

/**
 * Created by Chasen on 1/20/2017.
 */

@Parcel
public class Movie {

    private int moviePoster;

    public Movie() {

    }

    public Movie(int moviePoster) {
        this.moviePoster = moviePoster;
    }

    public int getMoviePoster() {
        return moviePoster;
    }
}
