package com.developer.chithlal.apt.factslist.contract;

import com.developer.chithlal.apt.factslist.data.Fact;

import java.util.ArrayList;

@SuppressWarnings("unused")
public interface FactContractMVP {

     interface View {     // To update views
         void updateList(ArrayList<Fact> factList);
         void showErrorMessage(String message);
         void setTitle(String title);
    }

     interface Presenter {    // app logic controller
        void loadList();
        void setView(FactContractMVP.View view);
        void onListUpdated(ArrayList<Fact> facts);
        void onTitleUpdate(String title);
        void onErrorMessage(String message);
    }

    interface Model{
         void setPresenter(FactContractMVP.Presenter presenter);
         void getFacts();
         void onListUpdated(ArrayList<Fact> facts);
         void onTitleUpdated(String title);
         void onErrorMessage(String message);

    }
}
