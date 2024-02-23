package com.example.q2pay.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.q2pay.databinding.ActivityProductBinding;
import com.example.q2pay.model.adapters.ProductAdapter;
import com.example.q2pay.model.listeners.ProductClickListener;
import com.example.q2pay.viewModel.ProductViewModel;

public class ProductsActivity extends AppCompatActivity implements ProductClickListener {

    private ActivityProductBinding activityProductBinding;
    private RecyclerView productRecyclerView;
    private ProgressBar progressBar;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityProductBinding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(activityProductBinding.getRoot());

        productRecyclerView = activityProductBinding.productRecyclerView;
        progressBar = activityProductBinding.progressBar;
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerView.setHasFixedSize(true);

        progressBar.setVisibility(View.VISIBLE);

        setupViewModel();

    }

    private void setupViewModel() {
        ProductViewModel viewModel = ViewModelProviders.of(this)
                .get(ProductViewModel.class);
        viewModel.getAllProducts().observe(this, productDataClassArrayList -> {
            if(productDataClassArrayList.isEmpty()){
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                productAdapter = new ProductAdapter(ProductsActivity.this, productDataClassArrayList, ProductsActivity.this);
                productRecyclerView.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onProductClick(String productID) {
        Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
        intent.putExtra("product_id", productID);
        startActivity(intent);
    }
}
