package api.utilies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtility {

 public static	FileInputStream fi;
 public static FileOutputStream fo;
 public static XSSFWorkbook wb;
 public static XSSFSheet ws;
 public static XSSFRow row;
 public static XSSFCell cell;
 String path;
 
 
 public DataUtility(String path) {
	this.path=path;
}

 public int getRowCount(String xlsheet) throws IOException
 {
     fi = new FileInputStream(path);
     wb = new XSSFWorkbook(fi);

     ws = wb.getSheet(xlsheet);

     if (ws == null) {
         wb.close();
         fi.close();
         throw new RuntimeException("❌ Sheet '" + xlsheet + "' NOT FOUND");
     }

     int rowCount = ws.getLastRowNum();

     wb.close();
     fi.close();

     return rowCount;
 }
	
 public  int getCellCount(String xlsheet,int rownum) throws IOException
 {
	 fi=new FileInputStream(path);
	 wb=new XSSFWorkbook(fi);
	 ws=wb.getSheet(xlsheet);
	  row=ws.getRow(rownum);
	  if (ws == null) {
		    throw new RuntimeException("❌ Sheet '" + xlsheet + "' NOT FOUND");
		}
	int cellCount=row.getLastCellNum();
	wb.close();
	fi.close();
	return cellCount;
 }
 
 public  String getCellData(String xlsheet,int rownum,int colnum) throws IOException
 {
	 fi=new FileInputStream(path);
	 wb=new XSSFWorkbook(fi);
	 ws=wb.getSheet(xlsheet);
	  row=ws.getRow(rownum);
	cell=row.getCell(colnum);
	DataFormatter formatter=new DataFormatter();
	String data;
	
	try {
	data=formatter.formatCellValue(cell);
	}
	catch(Exception e)
	{
		data=" ";
	}
	wb.close();
	fi.close();
	return data;
 }
 
 public  void setCellData( String xlsheet, int rownum, int colnum, String data) throws IOException
 {
     File file = new File(path);

     // If file does not exist → create
     if (!file.exists())
     {
         wb = new XSSFWorkbook();
         fo = new FileOutputStream(path);
         wb.write(fo);
         wb.close();
         fo.close();
     }

     // Open file
     fi = new FileInputStream(path);
     wb = new XSSFWorkbook(fi);

     // Get or create sheet
     if (wb.getSheet(xlsheet) == null)
         ws = wb.createSheet(xlsheet);
     else
         ws = wb.getSheet(xlsheet);

     // Get or create row
     if (ws.getRow(rownum) == null)
         row = ws.createRow(rownum);
     else
         row = ws.getRow(rownum);

     // Get or create cell
     if (row.getCell(colnum) == null)
         cell = row.createCell(colnum);
     else
         cell = row.getCell(colnum);

     // Set value
     cell.setCellValue(data);

     // Write back
     fo = new FileOutputStream(path);
     wb.write(fo);

     // Close
     wb.close();
     fi.close();
     fo.close();
 }
 
 public  void fillRedColor(String xlsheet, int rownum, int colnum) throws IOException
 {
     fi = new FileInputStream(path);
     wb = new XSSFWorkbook(fi);

     ws = wb.getSheet(xlsheet);

     // Handle row
     if (ws.getRow(rownum) == null)
         row = ws.createRow(rownum);
     else
         row = ws.getRow(rownum);

     // Handle cell
     if (row.getCell(colnum) == null)
         cell = row.createCell(colnum);
     else
         cell = row.getCell(colnum);

     // Create style
     XSSFCellStyle style = wb.createCellStyle();

     // Set RED color
     style.setFillForegroundColor(IndexedColors.RED.getIndex());
     style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

     cell.setCellStyle(style);

     // Write file
     fo = new FileOutputStream(path);
     wb.write(fo);

     wb.close();
     fi.close();
     fo.close();
 }
 public  void fillGreenColor(String xlsheet, int rownum, int colnum) throws IOException
 {
     fi = new FileInputStream(path);
     wb = new XSSFWorkbook(fi);

     ws = wb.getSheet(xlsheet);

     // Handle row
     if (ws.getRow(rownum) == null)
         row = ws.createRow(rownum);
     else
         row = ws.getRow(rownum);

     // Handle cell
     if (row.getCell(colnum) == null)
         cell = row.createCell(colnum);
     else
         cell = row.getCell(colnum);

     // Create style
     XSSFCellStyle style = wb.createCellStyle();

     // Set GREEN color
     style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
     style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

     cell.setCellStyle(style);

     // Write file
     fo = new FileOutputStream(path);
     wb.write(fo);

     wb.close();
     fi.close();
     fo.close();
 }
}
