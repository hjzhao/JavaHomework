package com.zhj.starter.service;

public class ZhjService {
    private String name;
    private int age;

    public ZhjService(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String info() {
        return name + "," + age;
    }
}
