package com.example.demo.spi.impl;

import com.example.demo.spi.DataSourceProvider;

public class OracleDataSourceProvider implements DataSourceProvider {
    @Override
    public void dataBaseSpi() {
        System.out.println("Oracle DataSource...");
    }
}
