package com.rit.profileapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rit.profileapplication.R;
import com.rit.profileapplication.main_activity.RecyclerItemClickListener;
import com.rit.profileapplication.model.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private RecyclerItemClickListener recyclerItemClickListener;
    private ArrayList<SearchResult> resultList;


    public ProfileAdapter(ArrayList<SearchResult> resultList, RecyclerItemClickListener recyclerItemClickListener) {
        this.resultList = resultList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.profile_item_show, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, @SuppressLint("RecyclerView") final int i) {
        holder.nameTV.setText(resultList.get(i).getName());
        holder.whoTv.setText(resultList.get(i).getWho());
        holder.userTv.setText(resultList.get(i).getUser());

        Picasso.get().load(resultList.get(i).getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(resultList.get(i));

            }
        });

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, whoTv, userTv;
        ImageView imageView;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameTV);
            whoTv = itemView.findViewById(R.id.whoTV);
            userTv = itemView.findViewById(R.id.userTV);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
