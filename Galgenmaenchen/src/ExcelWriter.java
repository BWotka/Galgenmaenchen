import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


public class ExcelWriter extends Writer {

	@Override
	public boolean write(char[] guessedLetters, char newLetter, int leftFails) {
		Timestamp tstamp = new Timestamp(System.currentTimeMillis());
		String guessedLettersString = null;
		for (char letter : guessedLetters) {
			guessedLettersString = guessedLettersString+letter+",";
		}
		File file = new File(subject + ".xls");
		//create a new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		//create a new sheet
		Sheet sheet = workbook.createSheet();
		
		//make header of .xls
		if (!file.exists())
		{			
			//format header
			Font fontHeader = workbook.createFont();
			fontHeader.setFontHeightInPoints((short) 11);
			fontHeader.setBold(true);
			CellStyle cellstyleHeader = workbook.createCellStyle();
			cellstyleHeader.setFont(fontHeader);
			cellstyleHeader.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
			
			
			Row header = sheet.createRow(0);
			Cell header0 = header.createCell(0);
			Cell header1 = header.createCell(1);
			Cell header2 = header.createCell(2);
			Cell header3 = header.createCell(3);
			header0.setCellValue("Zeitstempel");
			header1.setCellValue("geratene Buchstaben");
			header2.setCellValue("eingegebener Buchstabe");
			header3.setCellValue("verbleibende Fehlversuche");
			header0.setCellStyle(cellstyleHeader);
			header1.setCellStyle(cellstyleHeader);
			header2.setCellStyle(cellstyleHeader);
			header3.setCellStyle(cellstyleHeader);
		}
		
		//format of .xls
		Font font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 11);
				
		CellStyle cellstyle1 = workbook.createCellStyle();
		CellStyle cellstyle2 = workbook.createCellStyle();
		cellstyle1.setFont(font1);
		cellstyle2.setFont(font1);
		cellstyle1.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
		
		//add row to .xls
		Row row = sheet.createRow(sheet.getLastRowNum()+1);
		Cell cell0 = row.createCell(0);
		Cell cell1 = row.createCell(1);
		Cell cell2 = row.createCell(2);
		Cell cell3 = row.createCell(3);
		cell0.setCellValue(tstamp.getTime());
		cell1.setCellValue(guessedLettersString);
		cell2.setCellValue(newLetter);
		cell3.setCellValue(leftFails);
		
		if(sheet.getLastRowNum()%2 == 1) {
			cell0.setCellStyle(cellstyle1);
			cell1.setCellStyle(cellstyle1);
			cell2.setCellStyle(cellstyle1);
			cell3.setCellStyle(cellstyle1);
		}
		else
		{
			cell0.setCellStyle(cellstyle2);
			cell1.setCellStyle(cellstyle2);
			cell2.setCellStyle(cellstyle2);
			cell3.setCellStyle(cellstyle2);
		}
		
		try {
			FileOutputStream output = new FileOutputStream(subject + ".xls");
			workbook.write(output);
			output.close();
		} catch (Exception e) {
			System.out.println("Error");
			return false;
		}
		
		try {
			workbook.close();
		} catch (Exception e) {
			System.out.println("Fehler");
			return false;
		}
		
		return true;
	}

}

