package com.morris.pattern.prototype;

public class ShallowCloneClient {

    public static void main(String[] args) {
        ShallowClone shallowClone = new ShallowClone();
        ShallowClone newShallowClone = shallowClone.clone();

        System.out.println(shallowClone.getList() == newShallowClone.getList());
    }

}
