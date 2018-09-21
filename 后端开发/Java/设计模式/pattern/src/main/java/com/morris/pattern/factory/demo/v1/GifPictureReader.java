package com.morris.pattern.factory.demo.v1;

public class GifPictureReader implements  PictureReader {
    @Override
    public void read() {
        System.out.println("GIF读取器");
    }
}
