package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    private Workbook workbook;
    private Sheet sheet;

    @Given("File with name {string} exist")
    public void fileWithNameExist(String fileName) throws IOException {
        InputStream excel = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (excel == null) {
            throw new RuntimeException("File is not exist");
        }
        workbook = new HSSFWorkbook(excel);
    }

    @And("The header have next headers")
    public void theHeaderHaveNextHeaders(DataTable table) {
        List<String> expectedValues = table.transpose().asList();
        sheet = workbook.getSheetAt(0);
        Row firstRow = sheet.getRow(0);
        for (int i = 0; i < expectedValues.size(); i++) {
            assertEquals(expectedValues.get(i), firstRow.getCell(i).toString());
        }
    }

    @And("There are more than {int} lines in file")
    public void thereAreMoreThanLinesInFile(int lines) {
        assertTrue(lines < sheet.getLastRowNum());
    }

}
