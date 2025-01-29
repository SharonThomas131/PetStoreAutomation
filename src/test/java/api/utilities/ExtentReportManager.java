package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extentReport;		//common details to be added to the report
	public ExtentTest extentTest;  //create TC entries in the report and update the results of the test methods
	
	String reportName;
	
	public void onStart(ITestContext context)
	{
		//sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/extent-report.html"); //specific location of where you want the report to be generated
		//Instead of overwriting reports each time - create history of reports by appending a time stamp. 
		
		//Generate a time stamp
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/extent-report_" + timestamp + ".html");
        
		//reportName = "Test-Report-"+timestamp+".html"; //I ADDED THIS and below line NEWLY
        //sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName); //specify location of report
        
        
		sparkReporter.config().setDocumentTitle("Rest Assured Automation Project Report"); //title of the report //-->CHANGED THIS
		sparkReporter.config().setReportName("Pet Store Users API");  //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimelineEnabled(true);  //generates the pie charts

		
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Application", "Pet Store Users API");
		
		/*extentReport.setSystemInfo("Computer Name", "localhost");
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("Tester Name", "Sharon Thomas");
		extentReport.setSystemInfo("OS", "MAC");
		extentReport.setSystemInfo("Browser Name", "Chrome");*/ //instead of hard coding lets get them at runtime
		
		setDynamicSystemInfo();
    }

    private void setDynamicSystemInfo() {
        extentReport.setSystemInfo("Computer Name", System.getenv("COMPUTERNAME")); 
        extentReport.setSystemInfo("Environment", System.getProperty("env", "QA")); //the second param is default, if no env fetched set default to QA
        extentReport.setSystemInfo("Tester Name", System.getProperty("user.name")); 
        extentReport.setSystemInfo("OS", System.getProperty("os.name") + " " + System.getProperty("os.version"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("Browser Name", System.getProperty("browser", "Chrome"));
    }
		
		
	
	public void onTestStart(ITestResult result)
	{
		extentTest = extentReport.createTest(result.getName()); // Create a new test entry in the report
	    extentTest.log(Status.INFO, "Test started: " + result.getName()); // Log that the test has started
	
	}
	
	public void onTestSucess(ITestResult result)
	{ 
		//using ExtentReports we will create a new entry - NO NEED, ONTESTSTART WILL DO IT FOR EACH TEST
		//extentTest = extentReport.createTest(result.getName()); //next update the status using ExtentTest
		extentTest.log(Status.PASS, "Test case passed: "+result.getName());
		
	}
	
	
	public void onTestFailure(ITestResult result)
	{
		//extentTest = extentReport.createTest(result.getName()); //create a TC entry
		extentTest.log(Status.FAIL, "Test cased failed: " +result.getName()); //update the result 
		extentTest.log(Status.FAIL, "Reason for failure: "+result.getThrowable()); //reason for failure
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		//extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.SKIP, "Test Skipped: "+result.getName());
		
	}
	
	public void onFinish(ITestContext context)
	{
		extentReport.flush();  //WITHOUT THIS REPORT WONT BE GENERATED
	}
	

}

