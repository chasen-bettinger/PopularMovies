package chasen.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chasen on 1/20/2017.
 */

public class MovieAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Movie> mMovies;

    @BindView(R.id.movie_image)
    ImageView moviePicture;

    public MovieAdapter(Context context, List<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }
    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            layoutInflater.inflate(R.layout.movie_item, null);
        }

        ButterKnife.bind(view);

        moviePicture.setImageResource(mMovies.get(i).getMoviePoster());

        return view;

    }
}
