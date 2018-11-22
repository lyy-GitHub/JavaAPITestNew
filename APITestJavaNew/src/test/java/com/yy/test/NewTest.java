package com.yy.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.yy.listener.RetryListener;
import com.yy.listener.TestResultListener;
import com.yy.util.ExcelUtil;
import com.yy.util.HttpClientUtil;

public class NewTest {
	
	String baseDir=System.getProperty("user.dir");
	String casePath=baseDir+File.separator+"testCaseData\\casedata.xlsx";
  @Test(dataProvider = "dp")
  public void f(String url,String path,String meth,String map,String qiwang) {
	  String result=HttpClientUtil.GenerateRequest(url,path,meth,map);
	  System.out.println(result);
  }
  @Test
  public void test1(){
	  assertEquals("1", "1");
  }
  @Test
  public void test2(){
	  assertEquals("1", "1");
  }
  @Test
  public void test3(){
	  assertEquals("2", "1");
  }

  @DataProvider
  public Object[][] dp() {
    Object[][] object=ExcelUtil.getExcelData(casePath,"Sheet1");
    return object;
  }
}
