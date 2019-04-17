package com.morris.pattern.composite;

import java.util.ArrayList;
import java.util.List;

public class ConcreteCompany extends Company {

    private List<Company> children = new ArrayList<>();

    public ConcreteCompany(String name) {
        this.name = name;
    }

    @Override
    void add(Company company) {
        children.add(company);
    }

    @Override
    void getInfo() {
        System.out.println("部门：" + name + "---------");
        for (Company company : children
                ) {
            company.getInfo();
        }
    }
}
