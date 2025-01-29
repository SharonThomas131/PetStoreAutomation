package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public static FileInputStream fi; 
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style; 

	public static int getRowCount(String xlfilepath, String xlsheet) throws IOException
	{
		fi = new FileInputStream(xlfilepath);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(xlsheet);
		int rowCount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
		
	}
	
	public static int getCellCount(String xlfilepath, String xlsheet,int rownum) throws IOException
	{
		fi = new FileInputStream(xlfilepath);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
		
	}
	
	public static String getCellData(String xlfilepath, String xlsheet,int rownum,int colnum) throws IOException
	{
		fi = new FileInputStream(xlfilepath);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		String cellData; 
		
		try
		{
			//cellData = cell.toString(); we can use this or DataFromatter class
			DataFormatter formatter = new DataFormatter();
			cellData = formatter.formatCellValue(cell); //formats the cell data to a string, just like toString. 
			//in both DataFormatter or .toString() if we are trying to red empty cell it will throw and exception!! so try-catch
						
		}
		catch (Exception e)
		{
			cellData = ""; //trying to say it is an empty cell, so put it an "" dont use null that is different
		}
		wb.close();
		fi.close();
		return cellData;
		
	} //so we want to get the cell data of multiple cells use this utility method in the looping statement for how many ever cells
	
	public static void setCellData(String xlfilepath, String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi = new FileInputStream(xlfilepath);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		row.createCell(colnum).setCellValue(data); //cell=row.createCell(colnum) -> cell object then set that cell value
		fo = new FileOutputStream(xlfilepath);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

		
	} //cases where imagine we have an excel sheet with the test data and we have to update the last column with pass/fail result. 
	//so we read that row, create a cell in the end and add the result. next we can use below methods to color them green/red
	
	public static void fillGreenColor(String xlfilepath, String xlsheet,int rownum,int colnum) throws IOException
	{
		fi = new FileInputStream(xlfilepath);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style); //now attach that style to the cell then write to the file
		fo = new FileOutputStream(xlfilepath);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public static void fillRedColor(String xlfilepath, String xlsheet,int rownum,int colnum) throws IOException
	{
		fi = new FileInputStream(xlfilepath);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style); //now attach that style to the cell then write to the file
		fo = new FileOutputStream(xlfilepath);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
}
