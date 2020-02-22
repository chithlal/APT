package com.developer.chithlal.apt.factslist.data;

import com.developer.chithlal.apt.factslist.contract.FactContractMVP;
import com.developer.chithlal.apt.factslist.network.HTTPRequestInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/* Provide data from remote repository */

public class DataRepository implements FactRepository {

    private FactContractMVP.Model model;

    @Override
    public void setModel(FactContractMVP.Model model) {
        this.model = model;
    }

    @Override
    public void getList() {
        Retrofit retrofit;
        retrofit = setupRetrofit();
        getJSONData(retrofit);

    }

    private Retrofit setupRetrofit() {
        String baseUrl = "https://dl.dropboxusercontent.com/";

        return new Retrofit.Builder()          // Initializing retrofit builder for http connection
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getJSONData(Retrofit retrofit) {

        HTTPRequestInterface httpRequest = retrofit.create(HTTPRequestInterface.class);
        Call<JSONResponse> call = httpRequest.getJSONData();     //http get request
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(@NonNull Call<JSONResponse> call, @NonNull Response<JSONResponse> response) {
                try {

                    JSONResponse jsonResponse = response.body();
                    ArrayList<Fact> factList = null;
                    if (jsonResponse != null)
                        factList = parseJson(jsonResponse);
                    if (factList != null)
                        if (factList.size() != 0) model.onListUpdated(factList);
                } catch (NullPointerException e) {

                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(@NonNull Call<JSONResponse> call, @NonNull Throwable t) {
                if (model != null)
                    model.onErrorMessage("Something went wrong! Unable to load data");
            }
        });
    }

    private ArrayList<Fact> parseJson(JSONResponse jsonResponse) {

        ArrayList<Fact> factList = new ArrayList<>();    // Parsing response object to get actual data
        if (jsonResponse != null) {
            String title = jsonResponse.getTitle();
            if (!title.isEmpty()) model.onTitleUpdated(title);
            List<Row> rowList = jsonResponse.getRows();
            for (Row row : rowList) {
                if ((row.getTitle() != null && row.getTitle().length() != 0) && (row.getDescription() != null && row.getDescription().length() != 0)
                        && (row.getImageHref() != null && row.getImageHref().length() != 0))
                    factList.add(new Fact(row.getTitle(), row.getDescription(), parseURL(row.getImageHref())));

            }
        }

        return factList;
    }

    private String parseURL(String s) {
        String url = "";

        if (s != null)
            url = s.replace("http://", "https://");

        return url;
    }
}
