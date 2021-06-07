package edu.neu.madcourse.numad21su_shailigandhi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {
    public ImageView imageIcon;
    public TextView name;
    public TextView url;

    public RviewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
        imageIcon = itemView.findViewById(R.id.item_icon);
        name = itemView.findViewById(R.id.item_name);
        url = itemView.findViewById(R.id.item_link);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}