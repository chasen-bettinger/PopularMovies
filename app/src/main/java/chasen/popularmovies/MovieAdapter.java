package chasen.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chasen on 1/20/2017.
 */

public class MovieAdapter extends BaseAdapter {

    private final Context mContext;
    private ArrayList<Movie> mMovies;


    public MovieAdapter(Context context, ArrayList<Movie> movies) {
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


    public void updateAdapter(ArrayList<Movie> movies) {
        mMovies = movies;
        //and call notifyDataSetChanged
        notifyDataSetChanged();
    }


    static class ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_image)
        ImageView moviePicture;

        @Override
        public void onClick(View v) {

        }

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        final String baseURL = "http://image.tmdb.org/t/p/w500/";

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.movie_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Picasso.with(mContext).load(baseURL + mMovies.get(i).getMoviePoster()).into(viewHolder.moviePicture);

        return view;

    }
}
