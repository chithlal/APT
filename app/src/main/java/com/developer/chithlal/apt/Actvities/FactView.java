package com.developer.chithlal.apt.Actvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developer.chithlal.apt.Adapters.ApiInterface;
import com.developer.chithlal.apt.Models.Fact;
import com.developer.chithlal.apt.R;

import java.util.List;

public class FactView extends AppCompatActivity implements ApiInterface.ViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_view);
    }

    @Override
    public void updateList(List<Fact> factList) {

    }

    @Override
    public void onRefresh(List<Fact> factList) {

    }
}
