package com.example.dickiez.cluetest;

import com.example.dickiez.cluetest.Model.Data;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;

public interface ApiInterface {

    @GET("top_report")
    Call<List<Data>> getData(@HeaderMap Map<String, String> header);
}
