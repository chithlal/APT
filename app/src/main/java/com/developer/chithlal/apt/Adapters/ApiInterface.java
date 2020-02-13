package com.developer.chithlal.apt.Adapters;

import com.developer.chithlal.apt.Models.Fact;

import java.util.ArrayList;
import java.util.List;

public interface ApiInterface {

     interface ViewController{     // To update views
         void updateList(ArrayList<Fact> factList);
         void onRefresh(ArrayList<Fact> factList);
         void showErrorMessage(String message);
         void setTitle(String title);
    }

     interface LogicController{    // app logic controller
        void LoadList();
    }
}
