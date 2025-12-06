package com.example.proj4_recyclerview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.WishViewHolder> {

    interface OnCheckedChanged {
        void onChanged();
    }

    List<WishItem> items;
    OnCheckedChanged listener;

    public WishAdapter(List<WishItem> items, OnCheckedChanged listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wish, parent, false);
        return new WishViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WishViewHolder h, int pos) {
        WishItem i = items.get(pos);

        h.img.setImageResource(i.image);
        h.name.setText(i.name);
        h.price.setText("$" + i.price);
        h.check.setChecked(i.checked);

        h.check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            i.checked = isChecked;
            listener.onChanged();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class WishViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price;
        CheckBox check;

        public WishViewHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.imgWish);
            name = v.findViewById(R.id.txtName);
            price = v.findViewById(R.id.txtPrice);
            check = v.findViewById(R.id.checkBuy);
        }
    }
}
