package com.developer.chithlal.apt.Actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ArrayList<Fact> factList = new ArrayList<>();
    private String errorMgsNoConnection = "No internet connection available!";
    private String FACT_LIST = "fact_list";
    private String TITLE = "Title";
    private String toolbarTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_view);
        toolbar =  findViewById(R.id.toolbar );
        String emptyString = "";
        toolbar.setTitle(emptyString);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorActionbarTitile));
        setSupportActionBar(toolbar);                   //setting up toolbar as actionbar

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.rv_facts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));        // Arranging cards in linear way
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        factPresenter = new FactPresenter(this);                    // handle to presnter and logic controller

        if(savedInstanceState == null || !savedInstanceState.containsKey(FACT_LIST) ) {
            swipeRefreshLayout.setRefreshing(true);
            if (isConnectionAvailable())
                factPresenter.LoadList();
            else showErrorMessage(errorMgsNoConnection);
        }
        else {
            this.factList = savedInstanceState.getParcelableArrayList(FACT_LIST);
            factListAdapter = new FactListAdapter(this,this.factList);
            recyclerView.setAdapter(factListAdapter);
            setTitle(savedInstanceState.getString(TITLE));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putParcelableArrayList(FACT_LIST,this.factList);
        if(toolbarTitle.length()!=0)
        outState.putString(TITLE,toolbarTitle);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void updateList(ArrayList<Fact> factList) {
        swipeRefreshLayout.setRefreshing(false);
        this.factList=factList;
        factListAdapter = new FactListAdapter(this,this.factList);
        recyclerView.setAdapter(factListAdapter);               // adding items to recycler view

    }

    @Override
    public void onRefresh(ArrayList<Fact> factList) {
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
        toolbarTitle = title;
        toolbar.setTitle(title);  //setting toolbar title

    }

    @Override
    public void onRefresh() {
        if(isConnectionAvailable()) {
            factPresenter.LoadList();
            swipeRefreshLayout.setRefreshing(false);
        }
        else {
            showErrorMessage(errorMgsNoConnection);
            swipeRefreshLayout.setRefreshing(true);
        }

    }
    private boolean isConnectionAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         if(item.getItemId()== R.id.credit){
             Intent it = new Intent(getApplicationContext(),CreditsActivity.class);
             startActivity(it);
         }
         return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.fact_menu, menu);
        return true;
    }
}
