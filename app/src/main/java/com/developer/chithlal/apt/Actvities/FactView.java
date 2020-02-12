package com.developer.chithlal.apt.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.widget.Toast;

import com.developer.chithlal.apt.Adapters.ApiInterface;
import com.developer.chithlal.apt.Adapters.FactListAdapter;
import com.developer.chithlal.apt.Models.Fact;
import com.developer.chithlal.apt.Presenter.FactPresenter;
import com.developer.chithlal.apt.R;

import java.util.ArrayList;
import java.util.List;

public class FactView extends AppCompatActivity implements ApiInterface.ViewController,SwipeRefreshLayout.OnRefreshListener {
    FactPresenter factPresenter;
    private RecyclerView recyclerView;
    private FactListAdapter factListAdapter;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Fact> factList = new ArrayList<>();
    private String emptyString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_view);
        toolbar =  findViewById(R.id.toolbar );
        toolbar.setTitle(emptyString);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorActionbarTitile));
        setSupportActionBar(toolbar);                   //setting up toolbar as actionbar

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.rv_facts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));        // Arranging cards in linear way
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        factPresenter = new FactPresenter(this);                    // handle to presnter and logic controller
        factPresenter.LoadList();
        swipeRefreshLayout.setRefreshing(true);
    }


    @Override
    public void updateList(List<Fact> factList) {
        swipeRefreshLayout.setRefreshing(false);
        this.factList=factList;
        factListAdapter = new FactListAdapter(this,this.factList);
        recyclerView.setAdapter(factListAdapter);               // adding items to recycler view

    }

    @Override
    public void onRefresh(List<Fact> factList) {
        this.factList.clear();
        this.factList=factList;
        factListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);  //setting toolbar title

    }

    @Override
    public void onRefresh() {
        factPresenter.LoadList();
        swipeRefreshLayout.setRefreshing(false);
    }
}
