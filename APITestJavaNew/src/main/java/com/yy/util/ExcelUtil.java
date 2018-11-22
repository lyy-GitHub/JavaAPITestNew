package com.yy.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	 private static XSSFSheet sheet;
	    private static XSSFWorkbook wb;
	    private static XSSFCell Cell;
	    private static XSSFRow Row;
	public static Object[][] getExcelData(String filePath,String sheetName){	
		String[][] caseData=null;
		try {
			FileInputStream is=new FileInputStream(filePath);
			wb=new XSSFWorkbook(is);
			sheet=wb.getSheet(sheetName);
			int rowsCount=sheet.getLastRowNum();
			caseData=new String[rowsCount][5];
			for(int i=0;i<rowsCount;i++){
				 caseData[i][0]=getCellData(i+1,2);
                 caseData[i][1]=getCellData(i+1,3);
                 caseData[i][2]=getCellData(i+1,4);
                 caseData[i][3]=getCellData(i+1,5);
                 caseData[i][4]=getCellData(i+1,6);				
			}		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return caseData;
	}
	public static String getCellData(int RowNum, int ColNum) throws Exception {
        try{
            Cell = sheet.getRow(RowNum).getCell(ColNum);
            int dataType = Cell.getCellType();
            if  (dataType == 3) {
                return "";
            }
            else{
            	Cell.setCellType(CellType.STRING);
                String CellData = Cell.getStringCellValue();
                return CellData;
            }
        }
            catch (Exception e){
            	throw (e);
            }
        }
	public static void main(String[] args) {
		String baseDir=System.getProperty("user.dir");
		String casePath=baseDir+File.separator+"testCaseData\\casedata.xlsx";
		System.out.println(casePath);
		Object[][] data=getExcelData(casePath,"Sheet1");
		System.out.println(data);
	}

}
