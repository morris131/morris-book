package com.morris.pattern.bridge;

public class LinuxImageImp extends  ImageImp {
    @Override
    protected void doPaint(Martix m) {
        System.out.println("在Linux操作系统中显示图像");
    }
}
