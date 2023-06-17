package app.petstore.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	@DataProvider(name = "excelData")
    public static Object[][] getUsername() throws IOException {
        String filePath = "C:\\Users\\VGSL-SW10\\eclipse-workspace\\app.petstore\\src\\test\\java\\app\\petstore\\testdata\\testdata.xlsx";
        FileInputStream file=new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0); // Assuming the sheet index is 0 (first sheet)

        int rowCount = sheet.getLastRowNum();
        Object[][] data = new Object[rowCount][1];

        for (int i = 0; i <rowCount; i++) {
            Row row = sheet.getRow(i+1);
            Cell cell = row.getCell(1); // Get the 2nd column cell (index 1)

            if (cell != null) {
                String cellValue = cell.getStringCellValue(); // Assuming the data in the cell is a string
                data[i][0] = cellValue;
            }
        }

        workbook.close();
        return data;
    }
}
