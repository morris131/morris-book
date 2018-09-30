package com.morris.pattern.bridge;

public class UnixImageImp extends  ImageImp {
    @Override
    protected void doPaint(Martix m) {
        System.out.println("在Unix操作系统中显示图像");
    }
}
