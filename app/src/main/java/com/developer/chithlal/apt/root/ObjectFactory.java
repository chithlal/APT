package com.developer.chithlal.apt.root;

import com.developer.chithlal.apt.factslist.contract.FactContractMVP;
import com.developer.chithlal.apt.factslist.data.DataRepository;
import com.developer.chithlal.apt.factslist.data.FactRepository;
import com.developer.chithlal.apt.factslist.data.FactsModel;
import com.developer.chithlal.apt.factslist.presenter.FactPresenter;

public class ObjectFactory {
    private FactContractMVP.Model getModel(){
        return new FactsModel(getDataRepository());
    }
    public FactContractMVP.Presenter getPresenter(){
        return new FactPresenter(getModel());

    }
    private FactRepository getDataRepository(){
        return new DataRepository();

    }
}
