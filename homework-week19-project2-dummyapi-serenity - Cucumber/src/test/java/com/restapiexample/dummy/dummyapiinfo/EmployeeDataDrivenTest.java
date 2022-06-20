package com.restapiexample.dummy.dummyapiinfo;

import com.restapiexample.dummy.testbase.TestBase;
import com.restapiexample.dummy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class EmployeeDataDrivenTest extends TestBase {
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

    @Steps
    EmployeeSteps employeeSteps;

    @Title("This will create a new employee")
    @Test
    public void createMultipleEmployee() {

        ValidatableResponse response = employeeSteps.createEmployee(name, salary, age, id);
        response.log().all().statusCode(200);
        employeeID = response.log().all().extract().path("data.id");
        System.out.println(employeeID);
    }

    @Title("Verify if the employee was added to the list")
    @Test
    public void verifyEmployeeAddedList() {
        HashMap<String, Object> employeeMap = employeeSteps.getEmployeeMapInfoByID(employeeID);
        Assert.assertThat(employeeMap, hasValue(status));
        System.out.println("employee map : " + employeeMap);

    }

    @Title("Update the employee information and verify updated information")
    @Test
    public void upadteEmployee() {
        employee_name = employee_name + "_updated";
        employeeSteps.updateEmployee(employeeID, employee_name, employee_salary, employee_age, profile_image).log().all().statusCode(200);
        HashMap<String, Object> productMap = employeeSteps.getEmployeeMapInfoByID(employeeID);
        Assert.assertThat(productMap, hasKey("message"));
    }

    @Title("Delete the employee and verify if the employee is deleted!")
    @Test
    public void deleteEmployeeAndVerify() {
        employeeSteps.deleteEmployee(employeeID).statusCode(200);
        employeeSteps.getEmployeeById(employeeID).statusCode(404);
    }


}