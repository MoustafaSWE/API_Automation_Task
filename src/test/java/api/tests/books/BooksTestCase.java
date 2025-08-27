package api.tests.books;

import api.tests.books.steps.*;
import com.aventstack.extentreports.ExtentTest;
import config.ExtentReportConfig;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class BooksTestCase {
    ExtentReportConfig extentReportConfig = new ExtentReportConfig();

    @BeforeTest
    public void setup() throws IOException {
        extentReportConfig.reportSetup("BooksAPI.html","Books API");
    }

    @AfterTest
    public void tearDown() throws IOException {
        extentReportConfig.reportTearDown("BooksAPI.html");
    }

    @Test(priority = 1)
    public void getBooks (){
        ExtentTest test = extentReportConfig.extent.createTest("Get Books");
        new GetBooks().getBooks(test);
    }

    @Test(priority = 2)
    public void getBooksById (){
        ExtentTest test = extentReportConfig.extent.createTest("Get Books By Id");
        new GetBooksById().getBookingById(test);
    }

    @Test(priority = 3)
    public void createBook (){
        ExtentTest test = extentReportConfig.extent.createTest("Create Book");
        new CreateBook().createBook(test);
    }

    @Test(priority = 4)
    public void updateBook (){
        ExtentTest test = extentReportConfig.extent.createTest("Update Book");
        new UpdateBook().updateBook(test);
    }

    @Test(priority = 5)
    public void deleteBook (){
        ExtentTest test = extentReportConfig.extent.createTest("Delete Book");
        new DeleteBook().deleteBook(test);
    }
}
