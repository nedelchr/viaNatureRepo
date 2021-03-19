package com.example.vianature;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ResultAdapter extends FirebaseRecyclerAdapter<Destination, ResultAdapter.PastViewHolder> {


    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;

    public ResultAdapter(@NonNull FirebaseRecyclerOptions<Destination> options, Context context) {
        super(options);
        mContext = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull PastViewHolder holder, int i, @NonNull final Destination data) {
        holder.title.setText(data.getTitle());
        holder.type.setText(data.getType());
        holder.region.setText(data.getRegion());
        holder.description.setText(data.getDescription());

        Picasso.get().load(data.getImageUrl()).into(holder.imgurl);

    }

    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_results, parent, false);
        return new PastViewHolder(view);
    }

    class PastViewHolder extends RecyclerView.ViewHolder{


        TextView title,description, region, type;
       ImageView imgurl;


        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            region = itemView.findViewById(R.id.region);
            type = itemView.findViewById(R.id.type);
           imgurl = itemView.findViewById(R.id.imageShow);





        }
        ;
    }
}