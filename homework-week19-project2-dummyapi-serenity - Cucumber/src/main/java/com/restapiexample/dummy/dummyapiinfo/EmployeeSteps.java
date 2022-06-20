package com.restapiexample.dummy.dummyapiinfo;

import com.restapiexample.dummy.constants.EndPoints;
import com.restapiexample.dummy.model.EmployeePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class EmployeeSteps {
    @Step("Creating employee with name : {0}, age : {1} and ID : {2}")
    public ValidatableResponse createEmployee(String name, int salary, int age, int id){
        EmployeePojo employeePojo = new EmployeePojo();
        employeePojo.setName(name);
        employeePojo.setSalary(salary);
        employeePojo.setAge(age);
        employeePojo.setId(id);

        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .body(employeePojo)
                .when()
                .post(EndPoints.CREATE_EMPLOYEE_BY_ID)
                .then();

    }
    @Step("Getting the employee information with employeeID : {0}")
    public HashMap<String, Object> getEmployeeMapInfoByID(int employeeID){
        HashMap<String, Object> employeeMap = SerenityRest.given().log().all().
                when()
                .pathParam("employeeID", employeeID)
                .get(EndPoints.GET_SINGLE_EMPLOYEE_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return employeeMap;
    }
    @Step("Creating employee with employeeID : {0}, employee_name : {1}, employee_salary : {2}, employee_age : {3} and profile_image {4}")
    public ValidatableResponse updateEmployee(int employeeID,String employee_name,
                                              int employee_salary,
                                              int employee_age,
                                              String profile_image){
        EmployeePojo employeePojo = new EmployeePojo();
        employeePojo.setEmployee_name(employee_name);
        employeePojo.setEmployee_salary(employee_salary);
        employeePojo.setEmployee_age(employee_age);
        employeePojo.setProfile_image(profile_image);

        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .pathParam("employeeID",employeeID)
                .body(employeePojo)
                .when()
                .put(EndPoints.UPDATE_EMPLOYEE_BY_ID)
                .then();

    }

    @Step("Deleting employee information with userID : {0}")
    public ValidatableResponse deleteEmployee(int employeeID){
        return SerenityRest.given().log().all()
                .pathParam("employeeID", employeeID)
                .when()
                .delete(EndPoints.DELETE_EMPLOYEE_BY_ID)
                .then();
    }
    @Step("Getting employee information with userID: {0}")
    public ValidatableResponse getEmployeeById(int employeeID){
        return SerenityRest.given().log().all()
                .pathParam("employeeID", employeeID)
                .when()
                .get(EndPoints.GET_SINGLE_EMPLOYEE_BY_ID)
                .then();
    }
    @Step("Getting all employees from application")
    public ValidatableResponse getAllEmployee() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_EMPLOYEES)
                .then();

    }
}
