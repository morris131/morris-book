package com.morris.pattern.factory.demo.v2;

public class JpgPictureReader implements PictureReader {
    @Override
    public void read() {
        System.out.println("GIF读取器");
    }
}
