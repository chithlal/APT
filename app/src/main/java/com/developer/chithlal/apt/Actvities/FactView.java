package com.developer.chithlal.apt.Actvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.developer.chithlal.apt.Adapters.ApiInterface;
import com.developer.chithlal.apt.Adapters.FactListAdapter;
import com.developer.chithlal.apt.Models.Fact;
import com.developer.chithlal.apt.Presenter.FactPresenter;
import com.developer.chithlal.apt.R;

import java.util.List;

public class FactView extends AppCompatActivity implements ApiInterface.ViewController {
    FactPresenter factPresenter;
    private RecyclerView recyclerView;
    private FactListAdapter factListAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar );
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rv_facts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        factPresenter = new FactPresenter(this);
        factPresenter.LoadList();
    }

    @Override
    public void updateList(List<Fact> factList) {
        factListAdapter = new FactListAdapter(this,factList);
        recyclerView.setAdapter(factListAdapter);

    }

    @Override
    public void onRefresh(List<Fact> factList) {

    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }
}
