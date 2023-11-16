package com.example.demo.pool;


import java.sql.Connection;
import java.sql.Statement;

public class Tests {

    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mall";
        String username = "root";
        String password = "Root1234";

        DbConfig dbConnectionConfig = new DbConfig(
                driver, url, username, password);
        DbConnectionFactory dbConnectionFactory = new DbConnectionFactory(dbConnectionConfig);

        DbPoolConfig dbPoolConfig = new DbPoolConfig();
        dbPoolConfig.setMaxTotal(10);
        dbPoolConfig.setMaxIdle(5);
        dbPoolConfig.setMinIdle(2);
        dbPoolConfig.setMaxWaitMillis(200);
        dbPoolConfig.setTestOnBorrow(false);
        dbPoolConfig.setTestOnReturn(false);

        DbPool dbPool = new DbPool(dbPoolConfig, dbConnectionFactory);

        for(int i = 0 ; i < 20 ; i++){
            Connection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO ums_menu (parent_id, create_time, title, `level`, sort, name, icon, hidden) VALUES( 0, '2020-02-02 14:50:36', '商品111111', 0, 0, 'pms', 'product', 0);\n");
            dbPool.returnConnection(connection);
        }
    }

}
