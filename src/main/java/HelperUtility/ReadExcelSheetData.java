package HelperUtility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadExcelSheetData {


    public static Map<String, String> readTestDataSheet(String fileName)
    {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\"+fileName;
        FileInputStream fis;
        Map<String, String> dataMap = new HashMap<String, String>();
        {
            try {
                fis = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0);
                int lastRow = sheet.getLastRowNum();

                for(int i=0; i<=lastRow; i++) {
                    Row row = sheet.getRow(i);

                    //1st Cell as Value
                    Cell valueCell = row.getCell(1);

                    //0th Cell as Key
                    Cell keyCell = row.getCell(0);

                    String value = valueCell.getStringCellValue().trim();
                    String key = keyCell.getStringCellValue().trim();

                    //Putting key & value in dataMap
                    dataMap.put(key, value);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }
}


