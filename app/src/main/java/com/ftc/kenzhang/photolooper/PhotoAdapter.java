package com.ftc.kenzhang.photolooper;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kenzhang.photorecycler.R;

import java.util.ArrayList;

/**
 * Created by kenzhang on 2016/12/24.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ItemViewHolder> {

    private ArrayList<String> data;
    private OnItemClickListener l;

    public PhotoAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public PhotoAdapter.ItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo_pager, viewGroup, false);
        if (l != null) {
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(v, (Integer) v.getTag());
                }
            });
        }
        ItemViewHolder itemViewHolder = new ItemViewHolder(inflate);

        return itemViewHolder;


    }

    @Override
    public void onBindViewHolder(final PhotoAdapter.ItemViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(i + 1);
        int rgb = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        viewHolder.img.setColorFilter(rgb);
        viewHolder.text.setText(i + "");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public ArrayList<String> getData() {
        return data;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;
        ImageView img;

        public ItemViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        this.l = l;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

}
