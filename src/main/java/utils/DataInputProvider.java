package utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class DataInputProvider {

    public static String[][] getSheet(String dataSheetName) {
        String[][] data = null;
        try {
            FileInputStream fis = new FileInputStream("./src/main/java/Utils/" + dataSheetName + ".xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();
            data = new String[rowCount][columnCount];
            for (int i = 1; i < rowCount + 1; i++) {
                try {
                    XSSFRow row = sheet.getRow(i);
                    for (int j = 0; j < columnCount; j++) {
                        try {
                            String cellValue = "";
                            try {
                                cellValue = row.getCell(j).getStringCellValue();
                            } catch (NullPointerException e) {
                            }
                            data[i - 1][j] = cellValue;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            fis.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @DataProvider(name = "GenericDataProvider")
    public static Object[][] getExcelData(Method m) {
        String testSheet = (m.getAnnotation(Test.class)).testName();
        return DataInputProvider.getSheet(testSheet);
    }
}