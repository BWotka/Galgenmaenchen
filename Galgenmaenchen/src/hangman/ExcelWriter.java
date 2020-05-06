package hangman;

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

/**
 * Writes information on the game in an excel table.
 * 
 * @author Benedikt Wotka, David Nickel
 * @version 0.1
 *
 */
public class ExcelWriter extends Writer {
  /**
   * Writes the information.
   * 
   * @param guessedLetters all letters that the user guessed correctly.
   * @param newLetter the last letter that the user put in.
   * @param leftFails enamored attempts.
   */
  @Override
  public boolean write(char[] guessedLetters, char newLetter, int leftFails) {
    File file = new File(subject + ".xls");
    HSSFWorkbook workbook = new HSSFWorkbook();
    Sheet sheet;


    // if the file does not exists, it would be created and the head-row would be created.
    if (!file.exists()) {
      sheet = workbook.createSheet();

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
        System.out.println("Error beim verbinden des Workbooks mit der Datei " + subject + ".xls");
      }
      sheet = workbook.getSheetAt(0);
    }

    // formate the table-body
    Font font1 = workbook.createFont();
    font1.setFontHeightInPoints((short) 11);
    CellStyle cellstyleBody = workbook.createCellStyle();
    cellstyleBody.setFont(font1);

    if (sheet.getLastRowNum() % 2 == 1) { // every second row is grey to make it more clearly
      cellstyleBody
          .setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
      cellstyleBody.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    // add the next row to the end of the sheet
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

    cell0.setCellStyle(cellstyleBody);
    cell1.setCellStyle(cellstyleBody);
    cell2.setCellStyle(cellstyleBody);
    cell3.setCellStyle(cellstyleBody);

    sheet.autoSizeColumn(0);
    sheet.autoSizeColumn(2);
    sheet.autoSizeColumn(3);
    sheet.autoSizeColumn(4);

    // save it to the file
    try {
      FileOutputStream output = new FileOutputStream(subject + ".xls");
      workbook.write(output);
      output.close();
      workbook.close();
    } catch (Exception e) {
      System.out.println("Fehler beim schreiben in die Datei " + subject + ".xls");
      return false;
    }

    return true;
  }

}

