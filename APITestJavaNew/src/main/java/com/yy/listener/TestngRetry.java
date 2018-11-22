package com.yy.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;


public class TestngRetry implements IRetryAnalyzer {
	private static int retryCount = 1;
	private static int maxRetryCount = 2;

	public boolean retry(ITestResult result) {
		if(retryCount<=maxRetryCount)
		{
			String message="Running retry for "+result.getName()+" on class "+this.getClass().getName()+"Retrying "+retryCount+" times";
			System.out.println(message);
			Reporter.setCurrentTestResult(result);
			retryCount++;
			return true;
		}
		return false;
	}
}

