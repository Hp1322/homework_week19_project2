package com.restapiexample.dummy.cucumber.steps;

import com.restapiexample.dummy.dummyapiinfo.EmployeeSteps;
import com.restapiexample.dummy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasKey;

public class EmployeeStepdefs {

    static String name = "test";
    static int salary = 1234;
    static int age = 22;
    static int id = 25;
    static String employee_name = "abc" + TestUtils.getRandomValue();
    static int employee_salary = 12005;
    static int employee_age = 50;
    static String profile_image = "pqr";
    static int employeeID;
    static String status;
    static ValidatableResponse response;

    @Steps
    EmployeeSteps employeeSteps;

    @Given("^I am on homepage of application of employee$")
    public void iAmOnHomepageOfApplicationOfEmployee() {
    }

    @When("^I send Get request to list endpoint of employee$")
    public void iSendGetRequestToListEndpointOfEmployee() {
        response = employeeSteps.getAllEmployee();
    }

    @Then("^I must get back a valid status code (\\d+) of employee$")
    public void iMustGetBackAValidStatusCodeOfEmployee(int code) {
        response.statusCode(200);
        response.assertThat().statusCode(200);
    }

    @When("^I send Post request to list endpoint of employee$")
    public void iSendPostRequestToListEndpointOfEmployee() {
        response = employeeSteps.createEmployee(name, salary, age, id);
        response.log().all().statusCode(200);
        employeeID = response.log().all().extract().path("data.id");
        System.out.println(employeeID);
    }

    @When("^I send Put request to list endpoint of employee$")
    public void iSendPutRequestToListEndpointOfEmployee() {
        employee_name = employee_name + "_updated";
        response =  employeeSteps.updateEmployee(employeeID, employee_name, employee_salary, employee_age, profile_image).log().all().statusCode(200);
        HashMap<String, Object> productMap = employeeSteps.getEmployeeMapInfoByID(employeeID);
        Assert.assertThat(productMap, hasKey("message"));
    }

    @When("^I send Delete request to list endpoint of employee$")
    public void iSendDeleteRequestToListEndpointOfEmployee() {
        response = employeeSteps.deleteEmployee(employeeID);
        response.assertThat().statusCode(200);
    }

    @And("^I validate if employee is deleted$")
    public void iValidateIfEmployeeIsDeleted() {
        response = employeeSteps.getEmployeeById(employeeID);
        response.assertThat().statusCode(200);
    }

    @Then("^I validate total records 24$")
    public void iValidateTotalRecords() {
        response = employeeSteps.getAllEmployee();
        List<?> totalRecord = response.log().all().extract().path("data");
        Assert.assertEquals(24, totalRecord.size());
    }

    @And("^I validate id \"([^\"]*)\"$")
    public void iValidateId(int expected) {
        int dataID = response.log().all().extract().path("data[23].id");
        Assert.assertEquals(expected, dataID);
    }

    @And("^I validate first employee_name \"([^\"]*)\"$")
    public void iValidateFirstEmployee_name(String expected) {
        String dataName = response.log().all().extract().path("data[23].employee_name");
        Assert.assertEquals(expected,dataName);
    }

    @And("^I validate message \"([^\"]*)\"$")
    public void iValidateMessage(String expected) {
        String message = response.log().all().extract().path("message");
        Assert.assertEquals(expected,message);
    }

    @And("^I validate status \"([^\"]*)\"$")
    public void iValidateStatus(String expected) {
        String status = response.log().all().extract().path("status");
        Assert.assertEquals(expected,status);
    }

    @And("^I validate employee_salary \"([^\"]*)\"$")
    public void iValidateEmployee_salary(int expected) {
        int employee_salary  = response.log().all().extract().path("data[2].employee_salary");
        Assert.assertEquals(expected,employee_salary);
    }

    @And("^I validate employee_age \"([^\"]*)\"$")
    public void iValidateEmployee_age(int expected) {
        int employee_age = response.log().all().extract().path("data[5].employee_age");
        Assert.assertEquals(expected,employee_age);
    }

    @And("^I validate employee_name \"([^\"]*)\"$")
    public void iValidateEmployee_name(String expected) {
        String employee_name = response.log().all().extract().path("data[10].employee_name");
        Assert.assertEquals(expected,employee_name);
    }
}
