package com.example.demo.spi.impl;

import com.example.demo.spi.DataSourceProvider;

import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) {
        System.out.println(121212);
        ServiceLoader<DataSourceProvider> sourceProviders = ServiceLoader.load(DataSourceProvider.class);

        for (DataSourceProvider next : sourceProviders) {
            next.dataBaseSpi();
        }
    }
}
