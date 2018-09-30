package com.morris.pattern.bridge;

public abstract class Image {

    protected ImageImp imageImp;

    public void setImageImp(ImageImp imageImp) {
        this.imageImp = imageImp;
    }

    protected abstract void parseFile(String fileName);

}
