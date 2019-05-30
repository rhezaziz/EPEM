package com.example.rheza.epem;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServer {
    private static final String BASE_URL ="http://192.168.42.199/";
            //"http://10.10.11.234/";
            //"http://192.168.42.199/";
    private static RetrofitServer mInstance;
    private Retrofit retrofit;

    private RetrofitServer(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
