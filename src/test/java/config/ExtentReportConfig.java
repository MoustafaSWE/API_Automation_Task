package config;

import config.generateUnifiedReport.unifiedReportSetup.UnifiedReportSetupTest;
import utilities.FileUtilities;
import utilities.ReportUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;


public class ExtentReportConfig {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;

    //Setting Configurations Of The Report
    public void reportSetup(String reportName, String testCaseName) throws IOException {

        FileUtilities.deleteFile("testResults/extent.json");
        sparkReporter = new ExtentSparkReporter("testResults/".concat(reportName));
        List<ViewName> DEFAULT_ORDER = Arrays.asList(ViewName.DASHBOARD, ViewName.CATEGORY, ViewName.TEST);
        sparkReporter.viewConfigurer().viewOrder().as(DEFAULT_ORDER);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle(testCaseName);
        sparkReporter.config().setReportName(testCaseName);
        String css = ".r-img {width: 100%;}";
        sparkReporter.config().setCss(css);
        JsonFormatter json = new JsonFormatter("testResults/extent.json");
        extent = new ExtentReports();
        extent.createDomainFromJsonArchive("testResults/extent.json");
        extent.attachReporter(json, sparkReporter);

    }

    //Flushing The Report
    public void reportTearDown(String reportName) throws IOException {
        int reportFailedTestCases = 0;
        for (int i = 0; i < extent.getReport().getTestList().size(); i++) {
            if (extent.getReport().getTestList().get(i).getStatus() == Status.FAIL) {
                reportFailedTestCases = reportFailedTestCases + 1;
            }
        }

        ReportUtilities.failuresCount = ReportUtilities.failuresCount + ReportUtilities.failedExecutionCount + reportFailedTestCases;
        if (ReportUtilities.failuresCount > 0) {
            System.out.println(" There're ".concat(String.valueOf(ReportUtilities.failuresCount)).concat(" failed test case(s) or exception(s) occurred "));
            PrintWriter writer = new PrintWriter("testResults/testResultLog.txt", "UTF-8");
            writer.println(" There're ".concat(String.valueOf(ReportUtilities.failuresCount)).concat(" failed test case(s) or exception(s) occurred "));
            writer.close();
        } else {
            System.out.println("All test cases are succeeded");
            PrintWriter writer = new PrintWriter("testResults/testResultLog.txt", "UTF-8");
            writer.println("All Test cases are succeeded");
            writer.close();
        }
        extent.flush();
        File htmlFile = new File("testResults/".concat(reportName));
        try {
            //Desktop.getDesktop().open(htmlFile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (UnifiedReportSetupTest.isTestPlanRun) {
            JsonFormatter json = new JsonFormatter("testResults/extent.json");
            UnifiedReportSetupTest.unifiedExtent.createDomainFromJsonArchive("testResults/extent.json");
            UnifiedReportSetupTest.unifiedExtent.attachReporter(json, UnifiedReportSetupTest.unifiedSparkReporter);
        }

    }
}