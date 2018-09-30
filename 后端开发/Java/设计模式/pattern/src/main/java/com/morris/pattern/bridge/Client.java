package com.morris.pattern.bridge;

public class Client {

    public static void main(String[] args) {
        Image image = new JpgImage();
        image.setImageImp(new WindowsImageImp());
        image.parseFile("morris.jpg");
    }
}
