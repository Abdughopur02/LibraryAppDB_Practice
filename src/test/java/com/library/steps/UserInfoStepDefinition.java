package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class UserInfoStepDefinition {
    String actualUserCount;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        //connection
        DB_Util.createConnection();
        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualUserCount = " + actualUserCount);


    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String query="select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualUserCount = " + actualUserCount);

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
    String query="select count(distinct id) from users";
    DB_Util.runQuery(query);
        String expectedUserCount = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedUserCount,actualUserCount);
    DB_Util.destroy();
    }
}
