package com.example.onlineexhibitionplatform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {
    // 메인 서버 도메인
    private static final String BASE_URL = "https://asia-northeast3-bluegreen-oe.cloudfunctions.net/api/";

    // 인터페이스 객체를 create에 넘겨 실제 구현체 생성
    public static SampleService getApiService(){return getInstance().create(SampleService.class);}

    // Retrofit 객체 생성
    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
//    private Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();

//    // 인터페이스 객체를 create에 넘겨 실제 구현체 생성
//    SampleService sampleService = retrofit.create(SampleService.class);
}
