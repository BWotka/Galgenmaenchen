import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelWriter extends Writer {

  @Override
  public boolean write(char[] guessedLetters, char newLetter, int leftFails) {
    File file = new File(subject + ".xls");
    HSSFWorkbook workbook = new HSSFWorkbook();
    Sheet sheet;


    // make header of .xls
    if (!file.exists()) {
      // create a new sheet
      sheet = workbook.createSheet();

      // format header
      Font fontHeader = workbook.createFont();
      fontHeader.setFontHeightInPoints((short) 11);
      fontHeader.setBold(true);
      CellStyle cellstyleHeader = workbook.createCellStyle();
      cellstyleHeader.setFont(fontHeader);
      cellstyleHeader.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
      cellstyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

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
    } else {
      try {
        workbook = new HSSFWorkbook(new FileInputStream(file));
      } catch (Exception e) {
        System.out.println("Error");
      }
      sheet = workbook.getSheetAt(0);
    }

    // format of .xls
    Font font1 = workbook.createFont();
    font1.setFontHeightInPoints((short) 11);

    CellStyle cellstyle1 = workbook.createCellStyle();
    CellStyle cellstyle2 = workbook.createCellStyle();
    cellstyle1.setFont(font1);
    cellstyle2.setFont(font1);
    cellstyle1.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
    cellstyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // add row to .xls
    Row row = sheet.createRow(sheet.getLastRowNum() + 1);
    Cell cell0 = row.createCell(0);
    Cell cell1 = row.createCell(1);
    Cell cell2 = row.createCell(2);
    Cell cell3 = row.createCell(3);

    Timestamp tstamp = new Timestamp(System.currentTimeMillis());
    cell0.setCellValue(tstamp.toString());
    String guessedLettersString = new String();
    for (char letter : guessedLetters) {
      guessedLettersString = guessedLettersString + letter;
    }
    cell1.setCellValue(guessedLettersString);
    cell2.setCellValue(Character.toString(newLetter));
    cell3.setCellValue(Integer.toString(leftFails));

    if (sheet.getLastRowNum() % 2 == 1) {
      cell0.setCellStyle(cellstyle1);
      cell1.setCellStyle(cellstyle1);
      cell2.setCellStyle(cellstyle1);
      cell3.setCellStyle(cellstyle1);
    } else {
      cell0.setCellStyle(cellstyle2);
      cell1.setCellStyle(cellstyle2);
      cell2.setCellStyle(cellstyle2);
      cell3.setCellStyle(cellstyle2);
    }

    sheet.autoSizeColumn(0);
    sheet.autoSizeColumn(2);
    sheet.autoSizeColumn(3);
    sheet.autoSizeColumn(4);

    try {
      FileOutputStream output = new FileOutputStream(subject + ".xls");
      workbook.write(output);
      output.close();
      workbook.close();
    } catch (Exception e) {
      System.out.println("Error");
      return false;
    }

    return true;
  }

}

