package api.tests.authors;

import api.tests.authors.steps.*;
import com.aventstack.extentreports.ExtentTest;
import config.ExtentReportConfig;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AuthorsTestCase {
    ExtentReportConfig extentReportConfig = new ExtentReportConfig();

    @BeforeTest
    public void setup() throws IOException {
        extentReportConfig.reportSetup("AuthorsAPI.html","Authors API");
    }

    @AfterTest
    public void tearDown() throws IOException {
        extentReportConfig.reportTearDown("AuthorsAPI.html");
    }

    @Test(priority = 1)
    public void getAuthor (){
        ExtentTest test = extentReportConfig.extent.createTest("Get Authors");
        new GetAuthor().getAuthor(test);
    }

    @Test(priority = 2)
    public void getAuthorById (){
        ExtentTest test = extentReportConfig.extent.createTest("Get Authors By Id");
        new GetAuthorById().getAuthorById(test);
    }

    @Test(priority = 3)
    public void createAuthor (){
        ExtentTest test = extentReportConfig.extent.createTest("Create Author");
        new CreateAuthor().createAuthor(test);
    }

    @Test(priority = 4)
    public void updateAuthor (){
        ExtentTest test = extentReportConfig.extent.createTest("Update Author");
        new UpdateAuthor().updateAuthor(test);
    }

    @Test(priority = 5)
    public void deleteAuthor (){
        ExtentTest test = extentReportConfig.extent.createTest("Delete Author");
        new DeleteAuthor().deleteAuthor(test);
    }
}
