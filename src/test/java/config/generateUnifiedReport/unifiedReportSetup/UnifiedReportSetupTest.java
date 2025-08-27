package config.generateUnifiedReport.unifiedReportSetup;

import utilities.FileUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UnifiedReportSetupTest {

    public static ExtentSparkReporter unifiedSparkReporter;
    public static ExtentReports unifiedExtent;

    public static boolean isTestPlanRun = false;

    @Test
    public static void unifiedReportSetup() throws IOException {

        isTestPlanRun = true;
        FileUtilities.deleteFile("extent.json");
        unifiedSparkReporter = new ExtentSparkReporter("UnifiedTestResult.html");
        List<ViewName> DEFAULT_ORDER = Arrays.asList(ViewName.DASHBOARD, ViewName.CATEGORY, ViewName.TEST);
        unifiedSparkReporter.viewConfigurer().viewOrder().as(DEFAULT_ORDER);
        unifiedSparkReporter.config().setTheme(Theme.DARK);
        unifiedSparkReporter.config().setDocumentTitle("Unified Test Result");
        unifiedSparkReporter.config().setReportName("Unified Test Result");
        String css = ".r-img {width: 100%;}";
        unifiedSparkReporter.config().setCss(css);
        unifiedExtent = new ExtentReports();

    }
}
