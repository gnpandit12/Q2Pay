package com.example.q2pay.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.q2pay.model.retrofit.ApiInterface;
import com.example.q2pay.model.dataClass.Product;
import com.example.q2pay.model.dataClass.ProductDataClass;
import com.example.q2pay.model.retrofit.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author Gaurav Naresh Pandit
 * @Since 22/02/24
 **/
public class ProductViewModel extends ViewModel {

    public static final String TAG = "ProductViewModel";
    private MutableLiveData<ArrayList<Product>> mutableLiveData;


    public MutableLiveData<ArrayList<Product>> getAllProducts() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            loadProducts();
        }
        return mutableLiveData;
    }


    public void loadProducts() {
        ApiInterface apiInterface = RetrofitService.getRetrofitInstance().create(ApiInterface.class);
        Call<ProductDataClass> call = apiInterface.getAllProducts();

        call.enqueue(new Callback<ProductDataClass>() {
            @Override
            public void onResponse(Call<ProductDataClass> call, Response<ProductDataClass> response) {
                ProductDataClass productDataClass = response.body();

                Log.d(TAG, "onResponse: "+response);
                Log.d(TAG, "onResponse body: "+productDataClass);
                Log.d(TAG, "onResponse: ");

                mutableLiveData.setValue(productDataClass.getProducts());
            }

            @Override
            public void onFailure(Call<ProductDataClass> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });

    }


}
