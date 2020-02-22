package com.developer.chithlal.apt.factslist.network;

import com.developer.chithlal.apt.factslist.data.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HTTPRequestInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<JSONResponse> getJSONData();
}
