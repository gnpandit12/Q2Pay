package com.example.q2pay.model.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author Gaurav Naresh Pandit
 * @Since 22/02/24
 **/
public class RetrofitService {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://dummyjson.com";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
