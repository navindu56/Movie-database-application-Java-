package com.example.mobilecw2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecw2.Database.Movies;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {

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
    public FavouriteAdapter(List<Movies> data) {
        this.dataSet = data;
    }


    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        FavouriteAdapter.MyViewHolder myViewHolder = new FavouriteAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(FavouriteAdapter.MyViewHolder holder, int position) {
        holder.txtMoviename.setText(dataSet.get(position).getMovieTitle());
        if(dataSet.get(position).getIsFavourite()==1) {
            holder.chkmovie.setChecked(true);
        }
        holder.chkmovie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dataSet.get(position).setIsFavourite(1);
                }
                if(!isChecked){
                    dataSet.get(position).setIsFavourite(0);
                }
                FavouriteFragment.checkedMovies = dataSet;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
