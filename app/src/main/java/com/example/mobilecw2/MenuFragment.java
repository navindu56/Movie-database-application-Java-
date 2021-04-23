package com.example.mobilecw2;

import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.

 */
public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    android.app.Fragment registerFragment,displayFragment,favfragment,searchFragment,editMoviesFragment,ratingFragment;
    Button btnRegister,btnDisplay,btnFav,btnEditMovies,btnSearch,btnRating;
    FragmentTransaction transaction;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        btnDisplay = (Button) view.findViewById(R.id.btnDisplay);
        btnFav = (Button) view.findViewById(R.id.btnFav);
        btnEditMovies = (Button) view.findViewById(R.id.btnEditMovies);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        btnRating = (Button) view.findViewById(R.id.btnRating);


        favfragment = new FavouriteFragment();
        displayFragment = new DisplayFragment();
        editMoviesFragment = new EditMovies();
        ratingFragment = new RatingFragment();
        registerFragment = new RegisterFragment();
        searchFragment = new SearchFragment();

//        assaign each button to fragments
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(registerFragment);
            }
        });
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(displayFragment);
            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(favfragment);
            }
        });
        btnEditMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(editMoviesFragment);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(searchFragment);
            }
        });
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(ratingFragment);
            }
        });

        return view;
    }

    private void changeFragment(android.app.Fragment fragment){
        if(!fragment.isAdded()) {
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}