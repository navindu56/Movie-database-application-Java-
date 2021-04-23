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
public class FavouriteFragment extends Fragment {



    public FavouriteFragment() {
        // Required empty public constructor
    }

    private Button btnSave;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseHandler db;
    List<Movies> myDataset;
    public static List<Movies> checkedMovies;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDisplay);
        btnSave = (Button) view.findViewById(R.id.btnSave);

        db = new DatabaseHandler(getActivity());
        myDataset = db.getFavMovies();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new FavouriteAdapter(myDataset);
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedMovies!=null){
                    for(int i=0;i<checkedMovies.size();i++)
                    {
                        db.updateMovies(checkedMovies.get(i));
                    }
                    myDataset = db.getFavMovies();
                    Toast.makeText(getActivity(),"Successfully Saved" ,Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getActivity(),"You Haven't Deselected Any Items " ,Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}