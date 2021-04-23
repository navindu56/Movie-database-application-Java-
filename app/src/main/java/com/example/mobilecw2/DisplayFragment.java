package com.example.mobilecw2;

import android.app.Fragment;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecw2.Database.DatabaseHandler;
import com.example.mobilecw2.Database.Movies;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 */
public class DisplayFragment extends Fragment {




    public DisplayFragment() {
        // Required empty public constructor
    }

    private Button btnAddToFav;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseHandler db;
    List<Movies> myDataset;
    public static List<Movies> checkedMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDisplay);
        btnAddToFav = (Button) view.findViewById(R.id.btnAddToFav);

        db = new DatabaseHandler(getActivity());
        myDataset = db.getAllMovies();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new DisplayAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);


        btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedMovies!=null){
                    for(int i=0;i<checkedMovies.size();i++)
                    {
                        db.updateMovies(checkedMovies.get(i));
                    }
                    myDataset = db.getAllMovies();
                    Toast.makeText(getActivity(),"Successfully Added to Favourite" ,Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getActivity(),"Please select Movies you want to Add" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}