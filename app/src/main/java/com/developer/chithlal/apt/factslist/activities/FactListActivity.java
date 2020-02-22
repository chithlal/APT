package com.developer.chithlal.apt.factslist.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.developer.chithlal.apt.factslist.contract.FactContractMVP;
import com.developer.chithlal.apt.factslist.adapter.FactListAdapter;
import com.developer.chithlal.apt.factslist.data.Fact;
import com.developer.chithlal.apt.R;
import com.developer.chithlal.apt.root.ObjectFactory;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class FactListActivity extends AppCompatActivity implements FactContractMVP.View,SwipeRefreshLayout.OnRefreshListener {

    private FactContractMVP.Presenter factPresenter;

    private RecyclerView recyclerView;
    private FactListAdapter factListAdapter;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Fact> factList = new ArrayList<>();

    private final String errorMgsNoConnection = "No internet connection available!";
    private final String FACT_LIST = "fact_list";
    private final String TITLE = "Title";
    private String toolbarTitle = "";

    private Bundle savedState;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setupState(savedState);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_view);
        savedState = savedInstanceState;

        setupWidgets();
        ObjectFactory objectFactory = new ObjectFactory();
        factPresenter = objectFactory.getPresenter();                   // handle to presenter and logic controller
        factPresenter.setView(this);



    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setupState(savedState);         // Updates the state according to the saved data and delegating to presenter

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
            factPresenter.loadList();
            swipeRefreshLayout.setRefreshing(false);
        }
        else {
            showErrorMessage(errorMgsNoConnection);
            swipeRefreshLayout.setRefreshing(true);
        }

    }
    private boolean isConnectionAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
    private void setupWidgets(){
        toolbar =  findViewById(R.id.toolbar );
        String emptyString = "";
        toolbar.setTitle(emptyString);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorActionbarTitle));
        setSupportActionBar(toolbar);                   //setting up toolbar as actionbar

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.rv_facts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));        // Arranging cards in linear way
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    private void setupState(Bundle savedInstanceState){


        if(savedInstanceState == null || !savedInstanceState.containsKey(FACT_LIST) ) {
            swipeRefreshLayout.setRefreshing(true);
            if (isConnectionAvailable())
                factPresenter.loadList();
            else showErrorMessage(errorMgsNoConnection);
        }
        else {
            this.factList = savedInstanceState.getParcelableArrayList(FACT_LIST);
            factListAdapter = new FactListAdapter(this,this.factList);
            recyclerView.setAdapter(factListAdapter);
            setTitle(savedInstanceState.getString(TITLE));
        }
    }
}
