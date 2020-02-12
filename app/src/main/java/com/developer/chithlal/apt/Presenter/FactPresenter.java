package com.developer.chithlal.apt.Presenter;

import android.util.Log;

import com.developer.chithlal.apt.Adapters.ApiInterface;
import com.developer.chithlal.apt.Models.Fact;
import com.developer.chithlal.apt.Models.JSONResponse;
import com.developer.chithlal.apt.Models.Row;
import com.developer.chithlal.apt.NetworkHandler.HTTPRequestInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FactPresenter implements ApiInterface.LogicController {
    private ApiInterface.ViewController viewController;
    public FactPresenter(ApiInterface.ViewController viewController) {
        this.viewController = viewController;
    }

    @Override
    public void LoadList() {

        String baseUrl = "https://dl.dropboxusercontent.com/";
        Retrofit retrofit = new Retrofit.Builder()          // Initializing retrofit builder for http connection
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJSONData(retrofit);


    }
    private void getJSONData(Retrofit retrofit){
        viewController.showErrorMessage("Method:getJSONData");
        JSONResponse jsonResponse = null;
        HTTPRequestInterface httpRequest = retrofit.create(HTTPRequestInterface.class);
        Call<JSONResponse> call = httpRequest.getJSONData();     //http get request
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                try {
                    JSONResponse jsonResponse = response.body();
                    Log.d("JSON BODY:\n",response.body().getTitle());
                    List<Fact> factList = parseJson(jsonResponse);
                    if(factList.size()!=0) viewController.updateList(factList);
                }catch (NullPointerException e){

                }


            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                viewController.showErrorMessage("Something went wrong! Unable to load data");
            }
        });
    }

    private List<Fact> parseJson(JSONResponse jsonResponse) {

        List<Fact> factList = new ArrayList<Fact>();    // Parsing response object to get actual data
        String title = jsonResponse.getTitle();
        if(!title.isEmpty()) viewController.setTitle(title);
        List<Row> rowList = jsonResponse.getRows();
        for(Row row:rowList){
            factList.add(new Fact(row.getTitle(),row.getDescription(),parseURL(row.getImageHref())));
        }
        return factList;
    }
    private String parseURL(String s){
        String url="";

        if(s!=null)
            url = s.replace("http://", "https://");

        return url;
    }
}
