package com.orangehr.stepdefenitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features={"src/test/resources/features"},
        glue = {"com.orangehr.stepdefenitions"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
