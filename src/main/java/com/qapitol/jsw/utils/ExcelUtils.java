package com.qapitol.jsw.utils;

import com.qapitol.sauron.data.dataprovider.DataProviderFactory;
import com.qapitol.sauron.data.dataprovider.DataResource;
import com.qapitol.sauron.data.dataprovider.impl.ExcelDataProviderImpl;
import com.qapitol.sauron.data.dataprovider.impl.InputOutputStreamResource;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private SimpleLogger log = SauronLogger.getLogger();

    public static ExcelUtils getNewInstance() {
        return new ExcelUtils();
    }

    //Add new column headers and values in the Excel at the end
    public void addColumnAndValues(String inputFilePath, List<String> headers, List<Map<String, String>> values) throws IOException {

        ExcelDataProviderImpl excelDataProvider = (ExcelDataProviderImpl) DataProviderFactory.getDataProvider(getIOStreamResource(inputFilePath));
        Sheet sheet = excelDataProvider.getExcelReader().getSheetAtIndex(0);
        String sheetName = sheet.getSheetName();
        excelDataProvider.appendHeaderRowContents(sheetName, headers);
        String newInputFilePath = saveFile(inputFilePath, excelDataProvider).getAbsolutePath();

        excelDataProvider = (ExcelDataProviderImpl) DataProviderFactory.getDataProvider(getIOStreamResource(newInputFilePath));
        if(sheet.getLastRowNum() > 0) {
            for(int i = 0; i < sheet.getLastRowNum(); i++) {
                int updatedIndex = i + 1;
                excelDataProvider.updateRowContentsWithHeadersByRowIndex(sheetName, values.get(i), updatedIndex);
            }
        } else {
            throw new RuntimeException("No rows to update");
        }
        saveFile(inputFilePath, excelDataProvider);
    }

    /**
     * This method being use to copy one field from excel and paste it in any other cell
     *
     * @param filePath     Unique cell value to pass to identify the row
     * @param sourceHeader & destHeader to copy and paste any cell value
     * @throws IOException
     */
    public void copyColumnValueToAnotherCol(String filePath, String sourceHeader, String destHeader) throws IOException {
        ExcelDataProviderImpl excelDataProvider = (ExcelDataProviderImpl) DataProviderFactory.getDataProvider(getIOStreamResource(filePath));
        Sheet sheet = excelDataProvider.getExcelReader().getSheetAtIndex(0);

        // Find the source and destination column indices based on the headers
        int sourceColumnIndex = -1;
        int destColumnIndex = -1;
        Row headerRow = sheet.getRow(0); // First row is assumed to have the headers

        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            if (headerRow.getCell(i).getStringCellValue().equals(sourceHeader)) {
                sourceColumnIndex = i;
            }
            if (headerRow.getCell(i).getStringCellValue().equals(destHeader)) {
                destColumnIndex = i;
            }
        }

        // If source or destination header is not found, print an error and exit
        if (sourceColumnIndex == -1) {
            System.out.println("Source header not found: " + sourceHeader);
            saveFile(filePath, excelDataProvider);
            return;
        }
        if (destColumnIndex == -1) {
            System.out.println("Destination header not found: " + destHeader);
            saveFile(filePath, excelDataProvider);
            return;
        }

        // Copy values from the source column to the destination column
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowCount; i++) { // Start from row 1 to skip the header row
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell sourceCell = row.getCell(sourceColumnIndex);
                if (sourceCell != null) {
                    Cell destCell = row.createCell(destColumnIndex, sourceCell.getCellType());
                    copyCellValue(sourceCell, destCell); // Copy the value of the source cell to destination
                }
            }
        }
        saveFile(filePath, excelDataProvider);

        log.info("Values copied successfully from " + sourceHeader + " to " + destHeader);

    }

    // Helper method to copy the value from one cell to another
    private static void copyCellValue(Cell sourceCell, Cell destCell) {
        switch (sourceCell.getCellType()) {
            case STRING:
                destCell.setCellValue(sourceCell.getStringCellValue());
                break;
            case NUMERIC:
                destCell.setCellValue(sourceCell.getNumericCellValue());
                break;
            case BOOLEAN:
                destCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case FORMULA:
                destCell.setCellFormula(sourceCell.getCellFormula());
                break;
            default:
                destCell.setCellValue(sourceCell.toString());
        }
    }

    /**
     * Provides the updated file as a new output file.
     * @param inputFilePath
     * @param excelDataProvider
     * @return
     */
    private File saveFile(String inputFilePath, ExcelDataProviderImpl excelDataProvider){
        File outputFileSavedSuccessfully = null;
        excelDataProvider.getExcelReader().updateFile();

        File inputFile = Path.of(inputFilePath).toFile();
        int extIndex = inputFile.getName().lastIndexOf(".");
        String newFile = (extIndex == -1) ? inputFile.getName() + "_copy" : inputFile.getName().substring(0, extIndex) + "_copy" + inputFile.getName().substring(extIndex);
        File outputFile = Path.of(inputFile.getParent(), newFile).toFile();
        if(outputFile.exists()){
            if(inputFile.delete()) {
                if (outputFile.renameTo(inputFile)) {
                    outputFileSavedSuccessfully = inputFile;
                }
            }
        }
        return outputFileSavedSuccessfully;
    }

    private DataResource getIOStreamResource(String inputFilePath) throws IOException {
        String type;
        if (inputFilePath.toLowerCase().contains("xlsx")) {
            type = "xlsx";
        } else {
            type = "xls";
        }
        File inputFile = Path.of(inputFilePath).toFile();
        int extIndex = inputFile.getName().lastIndexOf(".");
        String newFile = (extIndex == -1) ? inputFile.getName() + "_copy" : inputFile.getName().substring(0, extIndex) + "_copy" + inputFile.getName().substring(extIndex);
        File outputFile = Path.of(inputFile.getParent(), newFile).toFile();
        InputStream inputStream = FileUtils.openInputStream(inputFile);
        OutputStream outputStream = FileUtils.openOutputStream(outputFile);

        return new InputOutputStreamResource(inputStream, outputStream, type);
    }
}
