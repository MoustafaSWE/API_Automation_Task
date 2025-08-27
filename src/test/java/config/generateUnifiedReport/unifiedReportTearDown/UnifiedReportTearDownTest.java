package config.generateUnifiedReport.unifiedReportTearDown;

import config.generateUnifiedReport.unifiedReportSetup.UnifiedReportSetupTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UnifiedReportTearDownTest extends UnifiedReportSetupTest {

    @Test
    public static void unifiedReportSetup() throws IOException {
        unifiedExtent.flush();
        File unifiedHtmlFile = new File("UnifiedTestResult.html");
        Desktop.getDesktop().open(unifiedHtmlFile);
    }

}
