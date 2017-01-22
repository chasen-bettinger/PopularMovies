package chasen.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {

    private Context mContext;
    private ArrayList<Movie> mMovies = new ArrayList<Movie>();
    private MovieAdapter movieAdapter;

    @BindView(R.id.movie_gridview)
    GridView movieGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            ,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_movie_fragment, container, false);

        ButterKnife.bind(this, rootView);

        mContext = rootView.getContext();

        movieAdapter = new MovieAdapter(mContext, mMovies);

        FetchMovieData fetchMovieData = new FetchMovieData();
        fetchMovieData.parseJSONData();

        movieGridView.setAdapter(movieAdapter);

        return rootView;
    }

    public class FetchMovieData {

        public final String LOG_TAG = FetchMovieData.class.getSimpleName();

        private final String baseURL = "https://api.themoviedb.org/3/movie/popular?";
        private final String apiKey = "&api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY;

        private String[] posterPaths;

        public FetchMovieData() {}

        public void parseJSONData() {
            Ion.with(mContext)
                    .load(buildURL(baseURL))
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {
                                posterPaths = formatJSONData(result);
                            }
                            catch (JSONException j){
                                Log.wtf(LOG_TAG, j);
                            }

                            for (String m : posterPaths) {
                                mMovies.add(new Movie(m));
                            }

                            movieAdapter.updateAdapter(mMovies);

                        }
                    });
        }

        private String buildURL(String baseURL) {
            String apiData = baseURL + apiKey;
            Log.v(LOG_TAG, apiData);
            return apiData;
        }

        private String[] formatJSONData(String jsonData) throws JSONException{

            final String TMDB_RESULTS = "results";
            final String TMDB_POSTER_PATH = "poster_path";



            JSONObject movieData = new JSONObject(jsonData);
            JSONArray resultsArray = movieData.getJSONArray(TMDB_RESULTS);

            String[] formattedJSONData = new String[resultsArray.length()];

            for(int i = 0; i < resultsArray.length(); i++) {
                JSONObject currentObject = resultsArray.getJSONObject(i);
                String posterPath = currentObject.getString(TMDB_POSTER_PATH);

                formattedJSONData[i] = posterPath;
            }

            for (String s : formattedJSONData) {
                Log.v(LOG_TAG, s);
            }

            return formattedJSONData;
        }

    }
}
