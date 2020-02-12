package com.developer.chithlal.apt.Adapters;

import com.developer.chithlal.apt.Models.Fact;

import java.util.List;

public interface ApiInterface {

     interface ViewController{     // To update views
         void updateList(List<Fact> factList);
         void onRefresh(List<Fact> factList);
         void showErrorMessage(String message);
         void setTitle(String title);
    }

     interface LogicController{    // app logic controller
        void LoadList();
    }
}
