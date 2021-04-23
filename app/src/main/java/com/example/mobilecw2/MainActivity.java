package com.example.mobilecw2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Fragment menuFragment,splashFragment;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashFragment = new SplashScreen();
        menuFragment = new MenuFragment();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,splashFragment);
        transaction.commit();
    }
}