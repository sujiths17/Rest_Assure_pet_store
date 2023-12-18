package api.utilites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//public class extendReportManager implements ITestListener {
//    public ExtentSparkReporter sparkReporter;
//    public ExtentReports extent;
//    public ExtentTest test;
//    String repName;
//
//    public void onStart(ITestContext testContext)
//    {
//        String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
//        repName="Test-Report-"+timeStamp+".html";
//
//        sparkReporter=new ExtentSparkReporter(".\\"+repName);//specify location of report
//
//        sparkReporter.config().setDocumentTitle("RestAssurePro");//title of report
//        sparkReporter.config().setReportName("Pet Store User Api");//name of report
//        sparkReporter.config().setTheme(Theme.DARK);
//
//        extent = new ExtentReports();
//        extent.attachReporter(sparkReporter);
//        extent.setSystemInfo("Application","pet store users api");
//        extent.setSystemInfo("Operating System ",System.getProperty("os.name"));
//        extent.setSystemInfo("user name",System.getProperty("user.name"));
//        extent.setSystemInfo("Enviornment","QA");
//        extent.setSystemInfo("user","apt");
//
//
//
//    }
//
//    public void onTestSuccess(ITestResult result){
//      test = extent.createTest(result.getName());
//      test.assignCategory(result.getMethod().getGroups());
//      test.createNode(result.getName());
//      test.log(Status.PASS,"Test Passed");
//
//    }
//    public void onTestFailure(ITestResult result){
//        test = extent.createTest(result.getName());
//        test.createNode(result.getName());
//        test.assignCategory(result.getMethod().getGroups());
//        test.log(Status.FAIL,"Test Failed");
//        test.log(Status.FAIL,result.getThrowable().getMessage());
//    }
//
//    public void onTestSkipped(ITestResult result){
//        test = extent.createTest(result.getName());
//        test.createNode(result.getName());
//        test.assignCategory(result.getMethod().getGroups());
//        test.log(Status.SKIP,"Test Skipped");
//        test.log(Status.SKIP,result.getThrowable().getMessage());
//    }
//
//    public void onFinish(ITestResult result){
//        extent.flush();
//    }
//}


public class extendReportManager implements ITestListener {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;
    private String repName;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter("." + File.separator + repName);//specify location of report

        sparkReporter.config().setDocumentTitle("RestAssurePro");//title of the report
        sparkReporter.config().setReportName("Pet Store User Api");//name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "pet store users api");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "apt");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups() != null ? result.getMethod().getGroups() : new String[]{});
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups() != null ? result.getMethod().getGroups() : new String[]{});
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups() != null ? result.getMethod().getGroups() : new String[]{});
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
