package com.dashboard.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

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
 * - Date format: "dd-mm-yyyy"
 * - Example:
 * File name: April_Dashboard_Data.xlsx
 * Contents:
 * Date         Product	    Average Response Time (ms)	Customer Satisfaction Score (1-5)	Customer Effort Score (1-5)	Net Promoter Score
 * 01-01-2024	Product A	2895	                    4.11	                            4.28	                    99
 * 02-01-2024	Product D	1716	                    3.12	                            2.67	                    9
 * 05-01-2024	Product C	941 	                    3.12	                            3.27	                    -19
 * 10-01-2024	Product B	2069	                    3.07 	                            2.58                        -14
 *   
 * @version 2.0
 * @author 1705125 - PFJN
 * 
 * @param file The Excel file to parse
 * 
 * @return A Data model object containing the parsed data from the Excel file
 * 
 * @throws IOException If an error occurs while reading the Excel file
 * 
 * @see DataModel
 */
public class ExcelParser {

    public DataModel parseExcelFile(File file) {
        DataModel dataModel = new DataModel();

        try (FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis)) {
            // Daily metrics are in the first sheet of the workbook
            Sheet dailyMetricsSheet = workbook.getSheetAt(0);

            for (Row row : dailyMetricsSheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                // Set the date of the data
                dataModel.setDate(row.getCell(0).getDateCellValue());
                dataModel.setDate(row.getCell(0).getDateCellValue());
                // Set additional date helpers
                dataModel.setMonthsInYear(row.getCell(0).getDateCellValue());
                dataModel.setMonthsNames(row.getCell(0).getDateCellValue());
                dataModel.setWeeksInYear(row.getCell(0).getDateCellValue());
                dataModel.setWeeksInMonth(row.getCell(0).getDateCellValue());
                dataModel.setDaysInMonth(row.getCell(0).getDateCellValue());
                dataModel.setDaysInWeek(row.getCell(0).getDateCellValue());
                // Set the product data
                dataModel.productData.setProductName(row.getCell(1).getStringCellValue());
                dataModel.productData.setAverageResponseTime(row.getCell(2).getNumericCellValue());
                dataModel.productData.setCustomerSatisfactionScore(row.getCell(3).getNumericCellValue());
                dataModel.productData.setCustomerEffortScore(row.getCell(4).getNumericCellValue());
                dataModel.productData.setNetPromoterScore(row.getCell(5).getNumericCellValue());
                // The average response time over the month and customer satisfaction score over the month
                // are calculated by the data model based on the daily metrics and are not set here

            }

            // Satisfaction breakdown is in the second sheet of the workbook
            Sheet satisfactionBreakdownSheet = workbook.getSheetAt(1);

            for (Row row : satisfactionBreakdownSheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                // Set the satisfaction breakdown data for the corresponding product name
                if (dataModel.productData.getProductName().equals(row.getCell(0).getStringCellValue())) {
                    for (int i = 1; i < row.getLastCellNum(); i++) {
                        dataModel.productData.setCustomerSatisfactionBreakDownPercentage(row.getCell(i).getNumericCellValue());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataModel;
    }
}
