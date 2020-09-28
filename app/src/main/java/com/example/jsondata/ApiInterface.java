package com.example.jsondata;

import com.example.jsondata.Model.DtaClass;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/gethomeData")
    Call<DtaClass> getData();


}
