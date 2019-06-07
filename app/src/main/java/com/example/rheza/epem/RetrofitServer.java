package com.example.rheza.epem;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServer {
    private static final String BASE_URL ="http://192.168.43.217/";
            //"http://10.10.11.234/";
            //"http://192.168.42.199/";
    private static RetrofitServer mInstance;
    private Retrofit retrofit;

    private RetrofitServer(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitServer getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitServer();
        }
        return mInstance;
    }

    public RegisterAPI RegisterApi(){
        return retrofit.create(RegisterAPI.class);
    }
}
