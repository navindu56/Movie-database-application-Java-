package com.example.mobilecw2;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilecw2.Database.DatabaseHandler;
import com.example.mobilecw2.Database.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.

 */
public class RatingMovieFragment extends Fragment {

    TextView txtName,txtRating;
    ImageView imgView;
    DatabaseHandler db;
    int movieId;
    Movies movies;

    public RatingMovieFragment() {
        // Required empty public constructor
    }
    String key = "k_d7z4cybc";
    String searchMovie;
    String url;
    String urlForRating;
    String name;
    private static final String TAG = "MyActivity";
    String id;
    String imgUrl;

    public class DownloadJSON extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;
            InputStreamReader inputStreamReader;
            String result = "";

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = httpURLConnection.getInputStream();


                inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();

                while (data != -1) {
                    result += (char) data;

                    data = inputStreamReader.read();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

        public class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView imgResult) {
            this.imageView = imgResult;

        }

            @Override
            protected Bitmap doInBackground(String... strings) {
                String url = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating_movie, container, false);
        db = new DatabaseHandler(getActivity());
        movieId = getArguments().getInt("MovieId");
        movies = db.getMovie(movieId);

        txtName = (TextView) view.findViewById(R.id.txtName);
        txtRating = (TextView) view.findViewById(R.id.txtRating);
        name = (String) movies.getMovieTitle();
        imgView = (ImageView)view.findViewById(R.id.imageRatingMovie);

        if(movies!=null) {
            txtName.setText(name);
            getMovieData();
            getImdbRating();
            LoadImage loadImage = new LoadImage(imgView);
            loadImage.execute(imgUrl);
        }

        return view;
    }


    public void getMovieData() {
        Log.d(TAG, "---------------------inside------------------------" + name);

        searchMovie = name.trim();
        url = "https://imdb-api.com/en/API/SearchTitle/" + key + "/" + searchMovie;
        Log.d(TAG, "----------------------movie id-----------------------" + url);


        DownloadJSON downloadJSON = new DownloadJSON();

        try {
            String result = downloadJSON.execute(url).get();

            JSONObject jsonObject = new JSONObject(result);
            JSONArray movies = jsonObject.getJSONArray("results");

            id = movies.getJSONObject(0).getString("id");
            imgUrl = movies.getJSONObject(0).getString("image");
            Log.d(TAG, "----------------------image url---------------------" + imgUrl);

//                URL imageUrl = new URL(imgUrl);
//                InputStream inputStream = imageUrl.openConnection().getInputStream();
//                Bitmap imgBitMap = BitmapFactory.decodeStream(inputStream);
//                movieImg.setImageBitmap(imgBitMap);

//                movieImg.setImageResource(LoadImageFromWebOperations(imgUrl));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
        }
    }

    public void getImdbRating() {

        DownloadJSON downloadJSON = new DownloadJSON();
        try {
            urlForRating = "https://imdb-api.com/en/API/Ratings/k_d7z4cybc/" + id;

            String res = downloadJSON.execute(urlForRating).get();
            JSONObject jsonObject2 = new JSONObject(res);
            String rating = jsonObject2.getString("imDb");

            Log.d(TAG, "----------------------movie id-----------------------" + rating);

            txtRating.setText(rating);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}