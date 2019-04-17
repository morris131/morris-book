package com.morris.pattern.bridge;

public class GifImage extends Image {
    @Override
    protected void parseFile(String fileName) {
        Martix m = new Martix();
        imageImp.doPaint(m);
        System.out.println(fileName + "，格式为GIF。");
    }
}
