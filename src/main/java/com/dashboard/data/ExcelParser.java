package com.dashboard.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel parser for the dashboard application.
 * This class is used to parse an Excel file containing product data.
 *
 * @implNote
 * The data in the Excel file is expected to be in the following format:
 * - Sheet 1: Daily Metrics 
 *  - Row 1: Header row
 *      - Column 1: Date
 *      - Column 2: Product
 *      - Column 3: Average Response Time (ms)
 *      - Column 4: Customer Satisfaction Score (1 - 5)
 *      - Column 5: Customer Effort Score (1 - 5)
 *      - Column 6: Net Promoter Score (percentage)
 * 
 * - Sheet 2: Satisfaction Breakdown
 *  - Row 1: Header row
 *     - Column 1: Product
 *     - Column 2: Score 1 (%)
 *     - Column 3: Score 2 (%)
 *     - Column 4: Score 3 (%)
 *     - Column 5: Score 4 (%)
 *     - Column 6: Score 5 (%)
 *
 * - Date format: "yyyy-mm-dd hh:mm:ss"
 * 
 * @version 1.0
 * @author 1705125 - PFJN
 * 
 * @param file The Excel file to parse
 * 
 * @return A list of data models containing the product data
 * 
 * @see ExcelParser
 */
public class ExcelParser {

    public List<DataModel> parseExcelFile(File file){
        List<DataModel> dataModels = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis)) {
            // Daily metrics are in the first sheet of the workbook
            Sheet dailyMetricsSheet = workbook.getSheetAt(0);

            for (Row row : dailyMetricsSheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                DataModel dailyMetricsModel = new DataModel();
                // Date in format ("dd/mm/yy")
                dailyMetricsModel.setDate(row.getCell(0).getDateCellValue());
                dailyMetricsModel.setProductName(row.getCell(1).getStringCellValue());
                dailyMetricsModel.setAverageResponseTime(row.getCell(2).getNumericCellValue());
                dailyMetricsModel.setCustomerSatisfactionScore(row.getCell(3).getNumericCellValue());
                dailyMetricsModel.setCustomerEffortScore(row.getCell(4).getNumericCellValue());
                dailyMetricsModel.setNetPromoterScore(row.getCell(5).getNumericCellValue());

                dataModels.add(dailyMetricsModel);
            }

            // Satisfaction breakdown is in the second sheet of the workbook
            Sheet satisfactionBreakdownSheet = workbook.getSheetAt(1);

            for (Row row : satisfactionBreakdownSheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                // Find the data model with the matching product name
                DataModel satisfactionBreakdownsModel = dataModels.stream()
                        .filter(dataModel -> dataModel.getProductName().equals(row.getCell(0).getStringCellValue()))
                        .findFirst()
                        .orElse(null);
                
                        if (satisfactionBreakdownsModel != null) {
                            List<Double> satisfactionBreakdown = new ArrayList<>();
                            
                            for (int i = 1; i < row.getLastCellNum(); i++) {
                                satisfactionBreakdown.add(row.getCell(i).getNumericCellValue());
                            }
                            satisfactionBreakdownsModel.setSatisfactionBreakdown(satisfactionBreakdown);
                            
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataModels;
    }
}
