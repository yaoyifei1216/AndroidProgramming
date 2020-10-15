package com.example.hookdemo;

public class SuperPerson extends Person implements Smoke.Smoking {
    private boolean isMan;

    public void fly() {
        System.out.println("走你~~");
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean iaMan) {
        isMan = iaMan;
    }

    @Override
    public void smoke(int count) {
        System.out.println("吸了" + count + "根烟");
    }
}