package chasen.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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

        MovieAdapter movieAdapter = new MovieAdapter(mContext, mMovies);

        movieGridView.setAdapter(movieAdapter);

        return rootView;
    }

    public class FetchMovieData {
        public FetchMovieData() {}



    }
}
