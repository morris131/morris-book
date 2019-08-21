package com.morris.rocketmq.consumer;

public class BreakTest {

    public static void main(String[] args) {
        first:for(int j=0; j<5; j++){
            second:for(int i=0; i<5; i++){
                if(i == 0){
                    System.out.println(i);
                    break first;
                }
            }
            System.out.println("跳出1层for循环到这啦");
            if(j == 0){
                System.out.println("终结者");
                break;
            }
        }
    }
}
