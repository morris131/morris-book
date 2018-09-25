package com.morris.pattern.prototype;

import java.io.*;
import java.util.ArrayList;

public class DeepClone implements  Cloneable, Serializable {

    private ArrayList<String> list = new ArrayList<>();

    @Override
    public DeepClone clone() {
        DeepClone deepClone = null;
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            deepClone = (DeepClone) ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deepClone;
    }

    public ArrayList<String> getList() {
        return list;
    }

}
