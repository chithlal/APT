package com.developer.chithlal.apt.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.chithlal.apt.Models.Fact;
import com.developer.chithlal.apt.R;

import java.util.List;

public class FactListAdapter extends RecyclerView.Adapter<FactListAdapter.FactViewHolder> {
    private final Context context;
    private final List<Fact> factList;

    public FactListAdapter(Context context, List<Fact> factList) {
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
        factViewHolder.cardTitle.setText(fact.getTitle());
        factViewHolder.cardDesc.setText(fact.getDescription());

    }

    @Override
    public int getItemCount() {
        return factList.size();
    }

    public class FactViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle,cardDesc;
        ImageView imagePreview;
        public FactViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.tv_card_title);
            cardDesc = itemView.findViewById(R.id.tv_card_desc);
            imagePreview = itemView.findViewById(R.id.iv_cardImage);
        }
    }
}
