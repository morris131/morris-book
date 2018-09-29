package com.morris.pattern.builder;

public class Robot {

    private String head;

    private String body;

    private String hand;

    private String foot;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public void setHand(String hand) {
        this.hand = hand;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", hand='" + hand + '\'' +
                ", foot='" + foot + '\'' +
                '}';
    }
}
