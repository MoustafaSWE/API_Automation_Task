package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;

public class ListenerTest implements ITestListener {
    List<ITestNGMethod> passedTests = new ArrayList<ITestNGMethod>();
    List<ITestNGMethod> failedTests = new ArrayList<ITestNGMethod>();
    List<ITestNGMethod> skippedTests = new ArrayList<ITestNGMethod>();

    @Override
    public void onFinish(ITestContext Result) {

    }

    @Override
    public void onStart(ITestContext Result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

    }

    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult Result) {
        failedTests.add(Result.getMethod());
        ReportUtilities.failedExecutionCount = failedTests.size();
    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult Result) {
        skippedTests.add(Result.getMethod());
    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult Result) {

    }

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult Result) {
        passedTests.add(Result.getMethod());
        ReportUtilities.passedExecutionCount = passedTests.size();
    }

}