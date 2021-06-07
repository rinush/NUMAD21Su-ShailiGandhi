package edu.neu.madcourse.numad21su_shailigandhi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {

    private final ArrayList<ItemCard> itemCardArrayList;
    private ItemClickListener itemClickListener;

    public RviewAdapter(ArrayList<ItemCard> itemCardArrayList) {
        this.itemCardArrayList = itemCardArrayList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new RviewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        ItemCard currentItem = itemCardArrayList.get(position);

        holder.imageIcon.setImageResource(currentItem.getImageSource());
        holder.name.setText(currentItem.getItemName());
        holder.url.setText(currentItem.getItemLink());
    }

    @Override
    public int getItemCount() {
        return itemCardArrayList.size();
    }
}
