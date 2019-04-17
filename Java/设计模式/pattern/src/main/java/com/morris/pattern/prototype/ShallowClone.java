package com.morris.pattern.prototype;

import java.util.ArrayList;

public class ShallowClone implements  Cloneable {

    private ArrayList<String> list = new ArrayList<>();

    @Override
    public ShallowClone clone() {

        ShallowClone shallowClone = null;
        try {
            shallowClone = (ShallowClone) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return shallowClone;

    }

    public ArrayList<String> getList() {
        return list;
    }

}
