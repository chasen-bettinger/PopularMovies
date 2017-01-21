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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {

    private Context mContext;
    private List<Movie> mMovies;

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

        mMovies = new ArrayList<Movie>();
        mMovies.add(new Movie(R.drawable.pets_poster));

        MovieAdapter movieAdapter = new MovieAdapter(mContext, mMovies);

        movieGridView.setAdapter(movieAdapter);

        return rootView;
    }

    public class FetchMovieData {

        public final String LOG_TAG = FetchMovieData.class.getSimpleName();

        private final String baseURL = "https://api.themoviedb.org/3/movie/popular?";
        private final String api_key = "&api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY;

        public FetchMovieData() {}

        public void parseJSONData() {
            Ion.with(mContext)
                    .load(buildURL(baseURL))
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {
                                formatJSONData(result);
                            }
                            catch (JSONException j){
                                Log.wtf(LOG_TAG, j);
                            }

                        }
                    });
        }

        private String buildURL(String baseURL) {
            return baseURL + api_key;
        }

        private void formatJSONData(String jsonData) throws JSONException{
            JSONObject movieData = new JSONObject(jsonData);
            Log.v(LOG_TAG, movieData.toString());
        }

    }
}
