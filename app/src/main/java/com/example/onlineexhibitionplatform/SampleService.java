package com.example.onlineexhibitionplatform;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SampleService {
    // 모든 게시글 보기
    @GET("posting/read")
    Call<ResponseReadList> getPosts();

    // 게시글 보기
    @GET("posting/read/{id}")
    Call<ResponseReadList> getPost(@Path("id") String id);

    // 게시글 작성
    @Headers("Content-Type:application/json")
    @POST("posting/create")
    Call<ResponseReadOneData> createPost(@Body RequestCreateData body);
}
