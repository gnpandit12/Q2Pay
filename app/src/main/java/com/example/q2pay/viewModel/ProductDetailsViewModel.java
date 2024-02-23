package com.example.q2pay.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.q2pay.model.retrofit.ApiInterface;
import com.example.q2pay.model.dataClass.Product;
import com.example.q2pay.model.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author Gaurav Naresh Pandit
 * @Since 23/02/24
 **/
public class ProductDetailsViewModel extends ViewModel {

    public static final String TAG = "ProductDetailsViewModel";
    private MutableLiveData<Product> ProductDetailsMutableData;


    public MutableLiveData<Product> getProductDetails(String productID) {
        if (ProductDetailsMutableData == null) {
            ProductDetailsMutableData = new MutableLiveData<>();
            loadProductDetails(productID);
        }
        return ProductDetailsMutableData;
    }


    public void loadProductDetails(String productID) {
        ApiInterface apiInterface = RetrofitService.getRetrofitInstance().create(ApiInterface.class);
        Call<Product> call = apiInterface.getProductDetails(productID);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();

                Log.d(TAG, "onResponse: "+response);
                Log.d(TAG, "onResponse body: "+product);
                Log.d(TAG, "onResponse: ");

                ProductDetailsMutableData.setValue(product);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                ProductDetailsMutableData.setValue(null);
            }
        });

    }

}

