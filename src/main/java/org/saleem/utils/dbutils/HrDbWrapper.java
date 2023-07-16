package org.saleem.utils.dbutils;

import org.saleem.utils.common.PropertyReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HrDbWrapper extends BaseDbWrapper {

    private static HrDbWrapper hrDbWrapper;

    HrDbWrapper(String url, String port, String dbName,String username, String password){
        super(url,port,dbName,username,password);
    }

    public static HrDbWrapper getInstance() {
        if(hrDbWrapper==null){
            hrDbWrapper = new HrDbWrapper(
                    PropertyReader.getPropertyForGivenKey("db.url"),
                    PropertyReader.getPropertyForGivenKey("db.port"),
                    PropertyReader.getPropertyForGivenKey("db.name"),
                    PropertyReader.getPropertyForGivenKey("db.username"),
                    PropertyReader.getPropertyForGivenKey("db.password"));
        }
        return hrDbWrapper;
    }

    public List<String> getAllEmployees(){
        ResultSet result = hrDbWrapper
                .executeQuery("Select * from employees");
        List<String> namesOfAllEmployees = new ArrayList<>();
        while (true){
            try {
                if (!result.next())
                    break;
                else{
                    String firstName = result.getString("first_name");
                    namesOfAllEmployees.add(firstName);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return namesOfAllEmployees;
    }

    public void closeConnection(){
        hrDbWrapper.closeConnections();
    }

    public static void main(String[] args) {
        getInstance().getAllEmployees().forEach(System.out::println);
    }
}
