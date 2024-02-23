package com.example.q2pay.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.q2pay.R;
import com.example.q2pay.model.dataClass.Product;
import com.example.q2pay.model.listeners.ProductClickListener;

import java.util.ArrayList;

/**
 * @Author Gaurav Naresh Pandit
 * @Since 22/02/24
 **/
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> mProductDataClassArrayList;
    private Context mContext;
    private ProductClickListener mProductClickListener;

    public ProductAdapter(Context context, ArrayList<Product> productDataClassArrayList, ProductClickListener productClickListener) {
        this.mContext = context;
        this.mProductDataClassArrayList = productDataClassArrayList;
        this.mProductClickListener = productClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_card_view, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = mProductDataClassArrayList.get(position);

        String title = product.getTitle();
        String description = product.getDescription();

        holder.productTitle.setText(product.getTitle());
        holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText("Price - " + product.getPrice());
        holder.productDiscountPercentage.setText("Discount Percentage - " + product.getDiscountPercentage());

        Glide.with(mContext)
                .load(product.getThumbnail())
                .into(holder.productImageView);

        holder.itemView.setOnClickListener(view -> mProductClickListener.onProductClick(product.getId().toString()));

    }

    @Override
    public int getItemCount() {
        return mProductDataClassArrayList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productImageView;
        TextView productTitle;
        TextView productDescription;
        TextView productPrice;
        TextView productDiscountPercentage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImageView = itemView.findViewById(R.id.product_image_view);
            productTitle = itemView.findViewById(R.id.product_title_tv);
            productDescription = itemView.findViewById(R.id.product_description_tv);
            productPrice = itemView.findViewById(R.id.product_price_tv);
            productDiscountPercentage = itemView.findViewById(R.id.discount_percentage_tv);


        }

    }

}
