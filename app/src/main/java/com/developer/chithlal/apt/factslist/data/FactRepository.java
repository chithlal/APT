package com.developer.chithlal.apt.factslist.data;

import com.developer.chithlal.apt.factslist.contract.FactContractMVP;

public interface FactRepository {
    void setModel(FactContractMVP.Model model);
    void getList();
}
