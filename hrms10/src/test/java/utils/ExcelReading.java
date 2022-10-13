package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReading {

	static Workbook book;
	static Sheet sheet;

	// OPEN EXCLE BOOK
	public static void openExcel(String filePath) {

		try {
			FileInputStream fis = new FileInputStream(filePath);
			book = new XSSFWorkbook(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// OPEN SHEET
	public static void getSheet(String sheetName) {
		sheet = book.getSheet(sheetName);
	}

//GET TOTAL ROWS
	public static int getRowCount() {
		return sheet.getPhysicalNumberOfRows(); // return rows with data
	}

//Get Total columns		
	public static int getColsCount(int rowIndex) {
		return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
	}

//To collect the data from every cell in the form of string, we use this function
	public static String getCellData(int rowIndex, int colIndex) {
		return sheet.getRow(rowIndex).getCell(colIndex).toString();
	}

	public static List<Map<String, String>> excelIntoListMap(String filePath, String sheetName) {
		openExcel(filePath);
		getSheet(sheetName);

		List<Map<String, String>> ListData = new ArrayList<>();
		// outer loop
		for (int row = 1; row < getRowCount(); row++) {
			// creating a map for every row
			Map<String, String> map = new LinkedHashMap<>();
			// Loop through the values of the cell
			for (int col = 0; col < getColsCount(row); col++) {
				map.put(getCellData(0, col), getCellData(row, col));
			}
			// to add the map to List
			ListData.add(map);
		}
		return ListData;

	}
}
