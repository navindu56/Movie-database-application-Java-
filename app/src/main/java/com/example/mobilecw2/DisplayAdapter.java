package com.example.mobilecw2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilecw2.Database.Movies;
import java.util.List;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.MyViewHolder>{

    private List<Movies> dataSet;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtMoviename;
        CheckBox chkmovie;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtMoviename = (TextView) itemView.findViewById(R.id.txtMoviename);
            this.chkmovie = (CheckBox) itemView.findViewById(R.id.chkmovie);
        }
    }


    public DisplayAdapter(List<Movies> data) {
        this.dataSet = data;
    }

    @Override
    public DisplayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAdapter.MyViewHolder holder, int position) {
        holder.txtMoviename.setText(dataSet.get(position).getMovieTitle());
        holder.chkmovie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dataSet.get(position).setIsFavourite(1);
                }
                if(!isChecked){
                    dataSet.get(position).setIsFavourite(0);
                }
                DisplayFragment.checkedMovies = dataSet;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
