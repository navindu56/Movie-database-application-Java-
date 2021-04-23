package com.example.mobilecw2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecw2.Database.Movies;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<EditAdapter.MyViewHolder>  {

    private List<Movies> dataSet;
    private OnItemClickListener onItemClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtMoviename;
        CheckBox chkmovie;

        public MyViewHolder(View itemView ) {
            super(itemView);
            this.txtMoviename = (TextView) itemView.findViewById(R.id.txtMoviename);
            this.chkmovie = (CheckBox) itemView.findViewById(R.id.chkmovie);

        }
    }

    public RatingAdapter(List<Movies> data, RatingFragment clickListener) {

        this.dataSet = data;
        this.onItemClickListener = (OnItemClickListener) clickListener;
    }

    @NonNull
    @Override
    public EditAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        EditAdapter.MyViewHolder myViewHolder = new EditAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder( EditAdapter.MyViewHolder holder, int position) {
        holder.txtMoviename.setText(dataSet.get(position).getMovieTitle());
        holder.chkmovie.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, dataSet.get(position).getMovieId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface OnItemClickListener{

        public void onItemClick(View view, int id);

    }
}
