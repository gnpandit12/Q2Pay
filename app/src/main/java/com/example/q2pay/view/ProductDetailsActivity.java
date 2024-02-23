package com.example.q2pay.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.q2pay.databinding.ActivityProductDetailsBinding;
import com.example.q2pay.model.adapters.ViewPagerAdapter;
import com.example.q2pay.viewModel.ProductDetailsViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ProductDetailsActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private String productID;
    private TextView productTitle, productDesc, productPrice, productDiscountPercentage,
                    productRating, productStock, productBrand, productCategory;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.q2pay.databinding.ActivityProductDetailsBinding productDetailsBinding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(productDetailsBinding.getRoot());

        progressBar = productDetailsBinding.productDetailsProgressBar;
        productTitle = productDetailsBinding.productDetailsTitleTv;
        productDesc = productDetailsBinding.productDetailsDescriptionTv;
        productPrice = productDetailsBinding.productDetailsPriceTv;
        productRating = productDetailsBinding.productDetailsRatingTv;
        productStock = productDetailsBinding.productDetailsStockTv;
        productBrand = productDetailsBinding.productDetailsBrandTv;
        productCategory = productDetailsBinding.productDetailsCategoryTv;
        productDiscountPercentage = productDetailsBinding.productDetailsDiscountPercentageTv;
        viewPager2 = productDetailsBinding.viewPager;
        tabLayout = productDetailsBinding.viewPagerPageIndicator;
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        productID = intent.getStringExtra("product_id");

        setupViewModel();

    }

    private void setupViewModel() {
        ProductDetailsViewModel viewModel = ViewModelProviders.of(this)
                .get(ProductDetailsViewModel.class);

        viewModel.getProductDetails(productID).observe(this, product -> {
            if (product != null) {
                productTitle.setText(product.getTitle());
                productDesc.setText(product.getDescription());
                productPrice.setText("Price - " +String.valueOf(product.getPrice()));
                productDiscountPercentage.setText("Discount - " + String.valueOf(product.getDiscountPercentage()));
                productRating.setText("Rating - " + String.valueOf(product.getRating()));
                productStock.setText("Stock - " + String.valueOf(product.getStock()));
                productBrand.setText("Brand - " + product.getBrand());
                productCategory.setText("Category - " + product.getCategory());

                viewPagerAdapter = new ViewPagerAdapter(ProductDetailsActivity.this, product.getImages());
                viewPager2.setAdapter(viewPagerAdapter);

                TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, true, (tab, position) -> { });
                tabLayoutMediator.attach();
                progressBar.setVisibility(View.INVISIBLE);
            } else {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

}