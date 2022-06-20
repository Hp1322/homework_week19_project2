package com.restapiexample.dummy.cucumber;



import com.restapiexample.dummy.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by Harsh
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature/"
//, tags = "@Test"
)
public class CucumberRunner extends TestBase {



}
