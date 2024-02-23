package com.example.q2pay.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.q2pay.R;

import java.util.ArrayList;

/**
 * @Author Gaurav Naresh Pandit
 * @Since 23/02/24
 **/
public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {

    private Context mContext;
    private ArrayList<String> mProductImages;

    public ViewPagerAdapter(Context context, ArrayList<String> productImages) {
        this.mContext = context;
        this.mProductImages = productImages;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_pager_item, parent, false);
        return new ViewPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {

        Glide.with(mContext)
                .load(mProductImages.get(position))
                .into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        return mProductImages.size();
    }

    static class ViewPagerViewHolder extends RecyclerView.ViewHolder {

        ImageView productImageView;
        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);

            productImageView = itemView.findViewById(R.id.view_pager_product_image_view);

        }
    }

}
