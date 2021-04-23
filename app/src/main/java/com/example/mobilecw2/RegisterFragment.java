package com.example.mobilecw2;

import android.app.Fragment;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilecw2.Database.DatabaseHandler;
import com.example.mobilecw2.Database.Movies;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }

    EditText nameTxtInput,yearTxtInput,directorTxtInput,actressTxtInput,ratingsTxtInput,descriptionTxtInput;
    Button registerBtn;
    DatabaseHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        db = new DatabaseHandler(getActivity());
        nameTxtInput = (EditText) view.findViewById(R.id.nameTxtInput);
        yearTxtInput = (EditText) view.findViewById(R.id.yearTxtInput);
        directorTxtInput = (EditText) view.findViewById(R.id.directorTxtInput);
        actressTxtInput = (EditText) view.findViewById(R.id.actressTxtInput);
        ratingsTxtInput = (EditText) view.findViewById(R.id.ratingsTxtInput);
        descriptionTxtInput = (EditText) view.findViewById(R.id.descriptionTxtInput);
        registerBtn = (Button) view.findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameTxtInput.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Name Cannot be Empty" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(yearTxtInput.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"year Cannot be Empty" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(directorTxtInput.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"director Cannot be Empty" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(actressTxtInput.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"cast Cannot be Empty" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ratingsTxtInput.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"rating Cannot be Empty" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(descriptionTxtInput.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Description Cannot be Empty" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                int year = Integer.parseInt(yearTxtInput.getText().toString());
                int ratings = Integer.parseInt(ratingsTxtInput.getText().toString());
                if (year <= 1895) {
                    Toast.makeText(getActivity(),"Can't add before 1895 movies" ,Toast.LENGTH_SHORT).show();
                }if (ratings >= 10) {
                    Toast.makeText(getActivity(),"Ratings must be between 10" ,Toast.LENGTH_SHORT).show();
                }else {

                    Movies movie = new Movies();
                    movie.setMovieTitle(nameTxtInput.getText().toString());
                    movie.setYear(Integer.parseInt(yearTxtInput.getText().toString()));
                    movie.setDirector(directorTxtInput.getText().toString());
                    movie.setCast(actressTxtInput.getText().toString());
                    movie.setRating(Integer.parseInt(ratingsTxtInput.getText().toString()));
                    movie.setDescription(descriptionTxtInput.getText().toString());
                    movie.setIsFavourite(0);

                    db.addMovie(movie);
                    Toast.makeText(getActivity(), "Movie Successfully Saved!", Toast.LENGTH_SHORT).show();
                    nameTxtInput.setText("");
                    yearTxtInput.setText("");
                    directorTxtInput.setText("");
                    actressTxtInput.setText("");
                    ratingsTxtInput.setText("");
                    descriptionTxtInput.setText("");
                }

            }
        });

        return view;
    }
}