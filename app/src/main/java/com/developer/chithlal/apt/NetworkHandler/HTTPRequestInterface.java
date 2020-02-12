package com.developer.chithlal.apt.NetworkHandler;

import com.developer.chithlal.apt.Models.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HTTPRequestInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<JSONResponse> getJSONData();
}
