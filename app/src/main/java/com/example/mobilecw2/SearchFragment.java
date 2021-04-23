package com.example.mobilecw2;

import android.app.Fragment;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobilecw2.Database.DatabaseHandler;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {



    public SearchFragment() {
        // Required empty public constructor
    }

    private Button btnSearchItem;
    private EditText editSearch;
    private ListView listItems;
    private DatabaseHandler db;
    List<String> myDataset;
    ArrayAdapter<String> arrayAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        btnSearchItem = (Button) view.findViewById(R.id.btnSearchItem);
        editSearch = (EditText) view.findViewById(R.id.editSearch);
        listItems = (ListView) view.findViewById(R.id.listItems);
        db = new DatabaseHandler(getActivity());

        btnSearchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editSearch.getText()!=null) {
                    if(!editSearch.getText().toString().isEmpty()) {
                        myDataset = db.getSearchMovies(editSearch.getText().toString());
                        if(myDataset !=null) {
                            if(myDataset.size()>0) {
                                arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myDataset);
                                listItems.setAdapter(arrayAdapter);
                                return;
                            }
                        }
                        listItems.setAdapter(null);
                        Toast.makeText(getActivity(),"Movie Not Found" ,Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                listItems.setAdapter(null);
                Toast.makeText(getActivity(),"Please enter any text to search" ,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}