package scbseleniumframeworkdesign.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg 
{
   public static ExtentReports getReportObject()
   {
	   String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter report=new ExtentSparkReporter(path);
		report.config().setReportName("Automation Results");
		report.config().setDocumentTitle("Test Results");
		 ExtentReports extent=new ExtentReports();
		 extent.attachReporter(report);
		 extent.setSystemInfo("Tester", "Akshay Tondase");   
	    return extent;
   } 
 }

