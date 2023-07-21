package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLUtility {
	public FileInputStream Fi;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell Cell;
	public FileOutputStream fo;
	String path;
	
	public XLUtility(String path) {
		this.path=path;
		
	}
	
	public int getRowCount(String sheetName) throws IOException {
		 Fi=new FileInputStream(path);
		 workbook=new XSSFWorkbook(Fi);
		 sheet=workbook.getSheet(sheetName);
		 int rowcount= sheet.getLastRowNum();
		 workbook.close();
		 Fi.close();
		 return rowcount;
		
	}
	
	public int getCellCount(String sheetName,int rownum) throws IOException {
		 Fi=new FileInputStream(path);
		 workbook=new XSSFWorkbook(Fi);
		 sheet=workbook.getSheet(sheetName);
		 row= sheet.getRow(rownum);
	 	 int cellcount= row.getLastCellNum();
		 workbook.close();
		 Fi.close();
		return cellcount;
		 
	}
	
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException {
		Fi=new FileInputStream(path);
		 workbook=new XSSFWorkbook(Fi);
		 sheet=workbook.getSheet(sheetName);
		 row= sheet.getRow(rownum);
		 Cell= row.getCell(colnum);
		
		 DataFormatter formatter=new DataFormatter();
		 String data;
		 try {
			data= formatter.formatCellValue(Cell);
		 }
		 catch(Exception e){
			 
			 data="";
		 }
		 workbook.close();
		 Fi.close();
		return data;
	}
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException {
		File xfile=new File(path);
		
		if(!xfile.exists()) {
			 workbook=new XSSFWorkbook();
			 fo=new FileOutputStream(path);
			 workbook.write(fo);
			 }
		Fi=new FileInputStream(path);
		 workbook=new XSSFWorkbook(Fi);
		 if(workbook.getSheetIndex(sheetName)==-1)
			 workbook.createSheet(sheetName);
       	sheet=workbook.getSheet(sheetName);
       	
       	if(sheet.getRow(rownum)==null)
       		sheet.createRow(rownum);
          row=sheet.getRow(rownum);
        Cell=row.createCell(colnum);
       	
        Cell.setCellValue(data);
        fo=new FileOutputStream(path);
        workbook.write(fo);
        Fi.close();
        workbook.close();
        fo.close();
       } 
	
	

}
