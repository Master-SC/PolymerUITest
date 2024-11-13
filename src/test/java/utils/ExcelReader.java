package utils;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class ExcelReader {

    public static HashMap<String, String> readCheckoutData(String filePath) throws IOException, IOException {
        HashMap<String, String> checkoutData = new HashMap<>();

        // Open the Excel file
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // sheet position in the excel file

        // Iterate through rows
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Assuming the first column is the field name, and the second is the value
            String field = row.getCell(0).getStringCellValue();
            String value = row.getCell(1).getStringCellValue();

            // Store the data in HashMap
            checkoutData.put(field, value);
        }

        workbook.close();
        fis.close();

        return checkoutData;
    }




}
