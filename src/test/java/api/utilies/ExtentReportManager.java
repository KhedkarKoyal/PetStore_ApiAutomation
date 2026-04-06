package api.utilies;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;

import java.util.*;
import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter spartReports;  //Ui of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test; //crating test case entries in the  report and update status of the test methods
	
	String repName;
	
	public void onStart(ITestContext  testContext)
	{
		
		String timeStemp= new SimpleDateFormat("yyyy.MM.dd.mm.ss").format(new Date());
		repName = "Test_Report-" + timeStemp + ".html";
		spartReports =new ExtentSparkReporter(".\\reports\\" + repName);
		
		spartReports.config().setDocumentTitle("OpenCart  Automation Report");
		spartReports.config().setReportName("opencart functional report");
		spartReports.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(spartReports);
		extent.setSystemInfo("Application","opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub_Module","customer");
		extent.setSystemInfo("User Name",System.getProperty("user.dir"));
		extent.setSystemInfo("Environment","QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String>  includedGruops=testContext.getCurrentXmlTest().getIncludedGroups();
				if(!includedGruops.isEmpty())
					{
					extent.setSystemInfo("Gruop", includedGruops.toString());
					}	
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//display grp in report
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" Test fail");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP, result.getName()+" Test skiped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	  @Override
	    public void onFinish(ITestContext context) {

	        extent.flush();

	        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	        File extentReport = new File(pathOfExtentReport);

	        try {
	            Desktop.getDesktop().browse(extentReport.toURI());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
