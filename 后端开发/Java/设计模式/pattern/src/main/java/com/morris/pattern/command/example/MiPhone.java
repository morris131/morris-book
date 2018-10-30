package com.morris.pattern.command.example;

import java.util.ArrayList;

public class MiPhone {

    private ArrayList commands;

    public MiPhone() {
        commands = new ArrayList();
    }

    public void setCommand(Command command) {
        commands.add(command);
    }

    public void onButtonWasPushed(int slot) {
        ((Command)commands.get(slot-1)).execute();
    }

    public static void main(String[] args) {
        MiPhone miPhone = new MiPhone();
        //创建电器
        Light light = new Light();
        TV tv = new TV();
        //创建命令
        LightCommand lightCommand = new LightCommand(light);
        TVCommand tvCommand = new TVCommand(tv);
        //给小米手机设置命令
        //设置第一个按钮为开灯
        miPhone.setCommand(lightCommand);
        //设置第二个按钮为开电视
        miPhone.setCommand(tvCommand);

        //开灯
        miPhone.onButtonWasPushed(1);
        //开电视
        miPhone.onButtonWasPushed(2);
    }
}

