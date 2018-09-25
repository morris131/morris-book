package com.morris.pattern.prototype;

public class DeepCloneClient {

    public static void main(String[] args) {
        DeepClone deepClone = new DeepClone();
        DeepClone newDeepClone = deepClone.clone();

        System.out.println(deepClone.getList() == newDeepClone.getList());
    }

}
