package org.saleem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDbWrapper {

    private String url;
    private String port;
    private String dbName;
    private String username;
    private String password;

    public BaseDbWrapper(String url, String port, String dbName) {
        this.url=url;
        this.port=port;
        this.dbName=dbName;
    }

    public BaseDbWrapper(String url, String port, String dbName,String username, String password) {
        this.url=url;
        this.port=port;
        this.dbName=dbName;
        this.username=username;
        this.password=password;
    }

    public Connection getConnection(){
        Connection con;
        try{
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection(
                    String.format("%s:%s/%s"+"?sslmode=disable",
                            url,port,dbName),username,password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public ResultSet executeQuery(String query){
        try {
            return getConnection()
                    .createStatement()
                    .executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnections(){
        try {
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
