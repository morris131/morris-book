package com.morris.pattern.bridge;

public class WindowsImageImp extends  ImageImp {
    @Override
    protected void doPaint(Martix m) {
        System.out.println("在Windows操作系统中显示图像");
    }
}
