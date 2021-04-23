package com.example.mobilecw2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecw2.Database.DatabaseHandler;
import com.example.mobilecw2.Database.Movies;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 */
public class EditMovies extends Fragment implements EditAdapter.OnItemClickListener{



    public EditMovies() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseHandler db;
    List<Movies> myDataset;
    FragmentTransaction transaction;
    Fragment editPoductPageFragment;
    Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_edit_movies, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDisplay);

        db = new DatabaseHandler(getActivity());
        myDataset = db.getAllMovies();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new EditAdapter(myDataset,  this);
        editPoductPageFragment = new EditMovie();
        recyclerView.setAdapter(mAdapter);


        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        changeFragment(editPoductPageFragment,position);
    }

    private void changeFragment(Fragment fragment, int id){
        if(!fragment.isAdded()) {
            bundle.putInt("MovieId", id);
            fragment.setArguments(bundle);
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
