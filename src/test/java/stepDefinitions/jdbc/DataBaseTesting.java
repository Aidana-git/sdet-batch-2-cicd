package stepDefinitions.jdbc;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseTesting {
    @Given("Establish new connection")
    public void establish_new_connection() {
        String url = "jdbc:mariadb://54.210.179.193/my_jdbc_db";
        String user = "admin";
        String password = "admin";

        try(Connection connection = DriverManager.getConnection(url, user, password)){
            System.out.println("Connected to the database!");

            Statement statement = connection.createStatement(); //Object is used to execute SQL query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees"); //fetch the all rows and columns

            System.out.println("Employee Data:");

            while(resultSet.next()){
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               String department = resultSet.getString("department");
               double salary = resultSet.getDouble("salary");

                System.out.println("ID: " + id);
                System.out.println("name: " + name);
                System.out.println("department: " + department);
                System.out.println("salary: " + salary);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Given("I query all employee names from the {string} table")
    public void i_query_all_employee_names_from_the_table(String columnName) {
        String url = "jdbc:mariadb://34.228.58.0/my_jdbc_db";
        String user = "admin";
        String password = "admin";

        try(Connection connection = DriverManager.getConnection(url, user, password)){
            System.out.println("Connected to the database!");

            Statement statement = connection.createStatement(); //Object is used to execute SQL query
            ResultSet resultSet = statement.executeQuery("SELECT name FROM employees"); //fetch the all rows and columns

            List<String> actualNames = new ArrayList<>();

            while(resultSet.next()){
               actualNames.add(resultSet.getString("name"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Then("I should get the following names:")
    public void i_should_get_the_following_names(DataTable dataTable) {
        List<String> expectedNames = dataTable.asList(String.class);
        List<String> actualNames = new ArrayList<>();
        String url = "jdbc:mariadb://34.228.58.0/my_jdbc_db";
        String user = "admin";
        String password = "admin";
        String query = "SELECT name FROM employees";

        try(Connection connection = DriverManager.getConnection(url, user, password)){

            Statement statement = connection.createStatement(); //Object is used to execute SQL query
            ResultSet resultSet = statement.executeQuery(query); //fetch the all rows and columns

            while(resultSet.next()){
                actualNames.add(resultSet.getString("name"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        if(actualNames.equals(expectedNames)){
            System.out.println("All expected names are present.");
        }
        else{
            System.out.println("Name mismatch found!");
            System.out.println("expected: " + expectedNames);
            System.out.println("Actual: "+ actualNames);
        }
        
    }
}
