package stepDefinitions.jdbc;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class EmployeeDataTesting {

    @Given("I query all employee names from the {string} table")
    public void i_query_all_employee_names_from_the_table(String columnName) {


    }
    @Then("I should get the following names:")
    public void i_should_get_the_following_names(DataTable dataTable) {

    }

}
