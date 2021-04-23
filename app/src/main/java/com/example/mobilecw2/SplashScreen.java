package com.example.mobilecw2;

import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class SplashScreen extends Fragment {
    FragmentTransaction transaction;
    android.app.Fragment menuFragment;


    public SplashScreen() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        menuFragment = new MenuFragment();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Second fragment after 5 seconds appears
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,menuFragment);
                transaction.commit();
            }
        };

        handler.postDelayed(runnable, 5000);


        return view;
    }

}