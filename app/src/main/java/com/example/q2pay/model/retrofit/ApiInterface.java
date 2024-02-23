package com.example.q2pay.model.retrofit;

import com.example.q2pay.model.dataClass.Product;
import com.example.q2pay.model.dataClass.ProductDataClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Author Gaurav Naresh Pandit
 * @Since 22/02/24
 **/
public interface ApiInterface {

    @GET("/products")
    Call<ProductDataClass> getAllProducts();

    @GET("/products/{id}")
    Call<Product> getProductDetails(@Path("id") String productID);

}
