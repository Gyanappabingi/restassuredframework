package app.petstore.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility {

	public static String path="C:\\Users\\VGSL-SW10\\eclipse-workspace\\app.petstore\\src\\test\\java\\app\\petstore\\testdata\\testdata.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;
	
	public static Object[][] getExceldata(String sheetname) throws Exception {
	
	FileInputStream file=new FileInputStream(path);
	 book=WorkbookFactory.create(file);
	 sheet=book.getSheet(sheetname);
	Object data[][]=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	for(int i=0;i<sheet.getLastRowNum();i++) {
		for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
			 data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			
		}
	}
	return data;

	
	}
	//get by username
	
	public String[] getcelldata(String sheetname) throws IOException {
		FileInputStream file=new FileInputStream(path);
		book=WorkbookFactory.create(file);
		sheet=book.getSheet(sheetname);
		int rowcount=sheet.getLastRowNum();
		String[] data= new String[rowcount];
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			data[i]= sheet.getRow(i+1).getCell(1).getStringCellValue();
		}
		return data;
		
		
	}
 
}
