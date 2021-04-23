package com.example.mobilecw2;

import android.app.Fragment;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.mobilecw2.Database.DatabaseHandler;
import com.example.mobilecw2.Database.Movies;

/**
 * A simple {@link Fragment} subclass.

 */
public class EditMovie extends Fragment {



    public EditMovie() {
        // Required empty public constructor
    }

    EditText txtName;
    EditText txtYear;
    EditText txtDirector;
    EditText txtDescription;
    EditText txtCast;
    RatingBar textRating;

    Button btnUpdate;
    CheckBox chkAvailable;
    DatabaseHandler db;
    int movieId;
    Movies movies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_movie, container, false);
        db = new DatabaseHandler(getActivity());
        movieId = getArguments().getInt("MovieId");
        movies = db.getMovie(movieId);


        txtName = (EditText) view.findViewById(R.id.txtName);
        txtYear = (EditText) view.findViewById(R.id.txtYear);
        txtDirector = (EditText) view.findViewById(R.id.txtDirector);
        txtCast = (EditText) view.findViewById(R.id.txtcast);
        textRating = (RatingBar) view.findViewById(R.id.RatingBar);
        txtDescription = (EditText) view.findViewById(R.id.txtDescription);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        chkAvailable = (CheckBox) view.findViewById(R.id.chkAvailable);


        String name = (String) movies.getMovieTitle();
        String mdirector = (String) movies.getDirector();
        String mYear = String.valueOf(movies.getYear());
        String mRatings = String.valueOf(movies.getRating());
        String fav = String.valueOf(movies.getIsFavourite());
        String cast = (String) movies.getCast();
        String mdis = (String) movies.getDescription();
        int noofstars = movies.getRating();


        if(movies!=null) {
            txtName.setText(name);
            txtYear.setText(mYear);
            txtDirector.setText(mdirector);
            txtCast.setText(cast);
            textRating.setRating(Float.parseFloat(mRatings));;
            txtDescription.setText(mdis);

            if(movies.getIsFavourite()==1){
                chkAvailable.setChecked(true);
            }else
            {
                chkAvailable.setChecked(false);
            }
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtName.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Name Cannot be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtYear.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Year Cannot be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtDirector.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Director Cannot be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtCast.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Cast Cannot be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if(textRating.get().toString().isEmpty()){
//                    Toast.makeText(getActivity(),"Rating Cannot be Empty" ,Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if (txtDescription.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Description Cannot be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                int year = Integer.parseInt(txtYear.getText().toString());
//                int ratings = Integer.parseInt(textRating.getText().toString());
                if (year <= 1895) {
                    Toast.makeText(getActivity(),"Can't add before 1895 movies" ,Toast.LENGTH_SHORT).show();
//                }if (ratings >= 10) {
//                    Toast.makeText(getActivity(),"Ratings must be between 10" ,Toast.LENGTH_SHORT).show();
                }else {
                    movies.setMovieTitle(txtName.getText().toString());
                    movies.setYear(Integer.parseInt(txtYear.getText().toString()));
                    movies.setDirector(txtDirector.getText().toString());
                    movies.setDescription(txtDescription.getText().toString());
                    movies.setCast(txtCast.getText().toString());
                    movies.setRating((int) textRating.getRating());
//                    movies.setRating(Integer.parseInt(textRating.getText().toString()));


                    if (chkAvailable.isChecked()) {
                        movies.setIsFavourite(1);
                    } else if (!chkAvailable.isChecked()) {
                        movies.setIsFavourite(0);
                    }

                    db.updateMovies(movies);
                    Toast.makeText(getActivity(), "Movie Successfully Updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}