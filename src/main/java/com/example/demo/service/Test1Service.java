package com.example.demo.service;

public interface Test1Service {

    String test();

    default String aa() {
        return null;
    }

    default String aaa(String a) {
        System.out.println(a);
         return a;
     }
}
