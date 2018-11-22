package com.yy.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import  org.testng.TestListenerAdapter;

public class TestResultListener extends TestListenerAdapter {
	@Override
	public void onFinish(ITestContext testContext){
		for(Iterator<ITestResult> iterator=testContext.getFailedTests().getAllResults().iterator();iterator.hasNext();){
			ITestResult testResult=iterator.next();
			ITestNGMethod method=testResult.getMethod();
			if(testContext.getFailedTests().getResults(method).size() > 1){
				iterator.remove();
			}else if(testContext.getPassedTests().getResults(method).size()>0){
				iterator.remove();
				
			}
		}
	}
	
//	@Override
//	public void onFinish(ITestContext testContext)
//	{
//		super.onFinish(testContext);
//		//存放要被删除的测试结果
//		ArrayList<ITestResult> testsToBeRemoved=new ArrayList<ITestResult>();
//		//收集所有的pass test的id
//		Set<Integer> passedTestIds=new HashSet<Integer>();
//		for(ITestResult passedTest:testContext.getPassedTests().getAllResults())
//		{
//			System.out.println("执行成功的用例="+passedTest.getName());
//			passedTestIds.add(getId(passedTest));
//		}
//		Set<Integer> failedTestIds=new HashSet<Integer>();
//		for(ITestResult failedTest:testContext.getFailedTests().getAllResults())
//		{
//			System.out.println("执行失败的测试用例="+failedTest.getName());
//			int failedTestId=getId(failedTest);
//			if(failedTestIds.contains(failedTestId)||passedTestIds.contains(failedTestId))
//			{
//				testsToBeRemoved.add(failedTest);
//			}
//			else
//			{
//				failedTestIds.add(failedTestId);
//			}
//			
//		}
//		for(Iterator<ITestResult> iterator=testContext.getFailedTests().getAllResults().iterator();iterator.hasNext();)
//		{
//			ITestResult testResult=iterator.next();
//			if(testsToBeRemoved.contains(testResult))
//			{
//				iterator.remove();
//			}
//		}	
//			
//		
//	}
	private int getId(ITestResult result) {
		int id = result.getTestClass().getName().hashCode();
		id = id + result.getMethod().getMethodName().hashCode();
		id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
		return id;
	}

}
