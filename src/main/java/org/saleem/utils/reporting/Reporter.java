package org.saleem.utils.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Reporter implements ITestListener {

    private static ExtentReports extentReport;
    private static ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        String dateTime = dateTimeFormat.format(localDateTime);
        String filePath = System.getProperty("user.dir")+"\\reports\\" + dateTime;
        extentReport = ExtentReportManager
                .createExtentReportInstance(filePath, "Reqres API report", "Regression Suite RUN");
    }

    @Override
    public void onFinish(ITestContext context) {
        if(extentReport!=null){
            extentReport.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport
                .createTest("Test Name " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.info(result.getMethod().getMethodName()+"has started");
    }

    @Override
    public void onTestFailure(ITestResult result) {
            extentTest.fail(MarkupHelper.createLabel(result.getMethod().getMethodName() +" has failed", ExtentColor.RED));
    }

}
