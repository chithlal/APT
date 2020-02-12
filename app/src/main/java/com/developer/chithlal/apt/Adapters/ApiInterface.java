package com.developer.chithlal.apt.Adapters;

import com.developer.chithlal.apt.Models.Fact;

import java.util.List;

public interface ApiInterface {

     interface ViewController{
         void updateList(List<Fact> factList);
         void onRefresh(List<Fact> factList);
    }

     interface LogicController{
        void LoadList();
    }
}
