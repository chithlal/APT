package com.developer.chithlal.apt.factslist.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.developer.chithlal.apt.factslist.data.Fact;
import com.developer.chithlal.apt.R;

import java.util.ArrayList;

public class FactListAdapter extends RecyclerView.Adapter<FactListAdapter.FactViewHolder> {
    private final Context context;
    private final ArrayList<Fact> factList;

    public FactListAdapter(Context context, ArrayList<Fact> factList) {
        this.context = context;
        this.factList = factList;
    }

    @NonNull
    @Override
    public FactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View parentView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_fact,viewGroup,false);
        return new FactViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(@NonNull FactViewHolder factViewHolder, int i) {
        Fact fact = factList.get(i);
        factViewHolder.cardTitle.setText(fact.getTitle());              // Setting the data to corresponding place
        factViewHolder.cardDesc.setText(fact.getDescription());
        if(!fact.getImgURL().isEmpty()&&fact.getImgURL()!=null)    // Loading image
        Glide.with(context).load(fact.getImgURL()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                factViewHolder.imagePreview.setImageResource(android.R.drawable.ic_menu_gallery);
                return true;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(factViewHolder.imagePreview);
        else factViewHolder.imagePreview.setImageResource(android.R.drawable.ic_menu_gallery);

    }



    @Override
    public int getItemCount() {
        return factList.size();
    }

    public class FactViewHolder extends RecyclerView.ViewHolder {
        final TextView cardTitle;
        final TextView cardDesc;
        final ImageView imagePreview;
        FactViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.tv_card_title);
            cardDesc = itemView.findViewById(R.id.tv_card_desc);
            imagePreview = itemView.findViewById(R.id.iv_cardImage);
        }
    }
}
