package com.developer.chithlal.apt.factslist.presenter;

import android.util.Log;

import com.developer.chithlal.apt.factslist.contract.FactContractMVP;
import com.developer.chithlal.apt.factslist.data.Fact;

import java.util.ArrayList;


public class FactPresenter implements FactContractMVP.Presenter {
    private FactContractMVP.View view;
    private final FactContractMVP.Model model;
    public FactPresenter(FactContractMVP.Model model) {
        this.model = model;
        model.setPresenter(this);
    }

    @SuppressWarnings("unused")
    @Override
    public void loadList() {
        Log.d("DEBUG", "loadList: ");
       model.getFacts();
    }

    @SuppressWarnings("unused")
    @Override
    public void setView(FactContractMVP.View view) {
        this.view = view;
    }

    @SuppressWarnings("unused")
    @Override
    public void onListUpdated(ArrayList<Fact> facts) {
        Log.d("DEBUG", "onListUpdated: ");
        view.updateList(facts);
    }

    @SuppressWarnings("unused")
    @Override
    public void onTitleUpdate(String title) {
        view.setTitle(title);
    }

    @SuppressWarnings("unused")
    @Override
    public void onErrorMessage(String message) {
        view.showErrorMessage(message);

    }


}
