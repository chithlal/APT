package com.developer.chithlal.apt.factslist.data;

import com.developer.chithlal.apt.factslist.contract.FactContractMVP;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class FactsModel implements FactContractMVP.Model {

    private final FactRepository factRepository;
    private FactContractMVP.Presenter presenter;

    public FactsModel(FactRepository factRepository) {
        this.factRepository = factRepository;
    }

    @Override
    public void setPresenter(FactContractMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getFacts() {
        factRepository.setModel(this);
        factRepository.getList();
    }

    @Override
    public void onListUpdated(ArrayList<Fact> factList) {
        presenter.onListUpdated(factList);
    }

    @Override
    public void onTitleUpdated(String title) {
        presenter.onTitleUpdate(title);
    }

    @Override
    public void onErrorMessage(String message) {
        presenter.onErrorMessage(message);

    }
}
