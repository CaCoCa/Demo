package com.example.demo.spi.impl;

import com.example.demo.spi.DataSourceProvider;

public class MysqlDataSourceProvider implements DataSourceProvider {
    @Override
    public void dataBaseSpi() {
        System.out.println("Mysql DataSource.....");
    }
}
