package com.dashboard.data;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;

import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

/**
 * Data model for the dashboard application.
 * This class is used to store the data that will be displayed in the dashboard.
 * 
 * The data model object aggregates data from an Excel file and provides methods to access and manipulate it.
 * It also contains methods to extract and format the data for display in the charts.
 * It is used by the DataLoader to load the data from the Excel file and by the visualizations to display the data.
 * 
 * The DataModel organizes the data by year, month, week, and day, combining the data from the Excel file into a single object.
 * The data is stored in efficient and performant data structures to allow for easy access and manipulation.
 * 
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
 * @param date The date of the data
 * @param months The months of the data
 * @param weeks The weeks of the data
 * @param days The days of the data
 * @param productName The name of the product
 * @param averageResponseTime The average response time for the product
 * @param customerSatisfactionScore The customer satisfaction score for the product
 * @param customerEffortScore The customer effort score for the product
 * @param netPromoterScore The net promoter score for the product
 * @param responseTimesOverMonth The response times over the month for the product
 * @param satisfactionScoresOverMonth The satisfaction scores over the month for the product
 * @param satisfactionBreakdown The satisfaction breakdown for the product
 * 
 * @return A data model object containing the product data
 * 
 * @see DataModel
 */
public class DataModel {
    private Set<Date> date;
    private Set<Integer> monthsInYear;
    private Map<Integer, String> monthsNames;
    private Set<Integer> weeksInMonth;
    private Set<Integer> weeksInYear;
    private Set<Integer> daysInMonth;
    private Map<Integer, String> daysInWeek;

    // Product data
    ProductData productData;

    // Ordered structure that stores the data sorted by the date.
    // The key is the date and the value is product data sorted by it's name.
    private Map<Date, Map<String, ProductData>> data = new TreeMap<>();

    private static DataModel dataModel;
        /**
         * DataModel
         * 
         * Constructor for the DataModel class.
         * 
         * Initializes the data model with empty sets and maps.
         * 
         * @see DataModel
         * @see DataLoader
         */
        public DataModel() {
            this.date = new TreeSet<>();
            this.monthsInYear = new TreeSet<>();
            this.monthsNames = new TreeMap<>();
            this.weeksInMonth = new TreeSet<>();
            this.weeksInYear = new TreeSet<>();
            this.daysInMonth = new TreeSet<>();
            this.daysInWeek = new TreeMap<>();
            this.productData = new ProductData();
            this.data = new TreeMap<>();
        }
    
        /**
         * getDate
         * 
         * Get the date of the data
         * 
         * If the date is null, returns null.
         * 
         * @see DataModel
         * @see DataLoader
         * 
         * @param date The date of the data
         * @return List of dates
         */
        public Date getDate(Date date) {
            return this.date.stream().filter(d -> d.equals(date)).findFirst().orElse(null);
        }
    
        public Set<Date> getDates() {
            return date;
        }
    
        public void setDate(Date date) {
            // If the date is not already in the list, add it
            if (!this.date.contains(date)) {
                this.date.add(date);
            }
        }
    
        /**
         * getMonthsInYear
         * 
         * If the date is null, returns a new empty list.
         * If not, extract the month from the date and add it to the months list
         * The excel contains a column with dates in the format "dd-mm-yyyy", Ex: 01-01-2021
         * We extract only the month from the date and display it in the chart
         * 
         * The months numbers are displayed as Ex: 01, 02, 03
         * 
         * @return List of months in the year
         * @see SimpleDateFormat
         * @see DataModel
         * @see DataLoader
         */    
        public Set<Integer> getMonthsInYear() {
            return monthsInYear;
        }
    
        public Integer getMonthInYear(int month) {
            return this.monthsInYear.stream().filter(m -> m.equals(month)).findFirst().orElse(null);
        }
    
        public void setMonthsInYear(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // If the month is not already in the list, add it
            if (!this.monthsInYear.contains(calendar.get(Calendar.MONTH) + 1)) {
                this.monthsInYear.add(calendar.get(
                    Calendar.MONTH) + 1); // Month is 0-based
            }
        }
        
        /**
         * getMonthsNames
         * 
         * If the date is null, returns a new empty list.
         * If not, extract the month from the date and add it to the months list
         * The excel contains a column with dates in the format "dd-mm-yyyy", Ex: 01-01-2021
         * We extract only the month from the date and display it in the chart
         * 
         * The months names are displayed as Ex: January, February, March
         * 
         * @return List of month names as strings
         * @see SimpleDateFormat
         * @see DataModel
         * @see DataLoader
         */
        public List<String> getMonthsNames() {
            return new ArrayList<>(monthsNames.values());
        }
    
        public String getMonthName(int month) {
            return this.monthsNames.get(month);
        }
    
        public void setMonthsNames(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // If the month is not already in the map, add it
            if (!this.monthsNames.containsKey(calendar.get(Calendar.MONTH) + 1)) {
                this.monthsNames.put(calendar.get(
                    Calendar.MONTH) + 1, // Month is 0-based
                    calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())); // Get month name
            }
        }
        /**
         * getWeeksInMonth
         * 
         * If the date is null, returns a new empty list.
         * If not, extract the week from the date and add it to the weeks list
         * The excel contains a column with dates in the format "dd-mm-yyyy", Ex: 01-01-2021
         * We extract only the week from the date and display it in the chart
         * 
         * The weeks are displayed as Ex: 01, 02, 03
         * 
         * @return List of weeks in the month
         * @see SimpleDateFormat
         * @see DataModel
         * @see DataLoader
         */
        public Set<Integer> getWeeksInMonth() {
            return weeksInMonth;
        }
    
        public Integer getWeekInMonth(int week) {
            return this.weeksInMonth.stream().filter(w -> w.equals(week)).findFirst().orElse(null);
        }
    
        public void setWeeksInMonth(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // If the week is not already in the list, add it
            if (!this.weeksInMonth.contains(calendar.get(Calendar.WEEK_OF_MONTH))) {
                this.weeksInMonth.add(calendar.get(
                    Calendar.WEEK_OF_MONTH)); // Week of month
            }
        }
    
        /**
         * getWeeksInYear
         * 
         * If the date is null, returns a new empty list.
         * If not, extract the week from the date and add it to the weeks list
         * The excel contains a column with dates in the format "dd-mm-yyyy", Ex: 01-01-2021
         * We extract only the week from the date and display it in the chart
         * 
         * The weeks are displayed as Ex: 10, 11, 12
         * 
         * @return List of weeks in the year
         * @see SimpleDateFormat
         * @see DataModel
         * @see DataLoader
         */
    
        public Set<Integer> getWeeksInYear() {
            return weeksInYear;
        }
    
        public Integer getWeekInYear(int week) {
            return this.weeksInYear.stream().filter(w -> w.equals(week)).findFirst().orElse(null);
        }
    
        public void setWeeksInYear(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // If the week is not already in the list, add it
            if (!this.weeksInYear.contains(calendar.get(Calendar.WEEK_OF_YEAR))) {
                this.weeksInYear.add(calendar.get(
                    Calendar.WEEK_OF_YEAR)); // Week of year
            }
        }
    
        /**
         * getDaysInMonth
         * 
         * If the date is null, returns a new empty list.
         * If not, extract the day from the date and add it to the days list
         * The excel contains a column with dates in the format "dd-mm-yyyy", Ex: 01-01-2021
         * We extract only the day from the date and display it in the chart
         * 
         * The days are displayed as Ex: 01, 02, 03
         * 
         * @return List of days in the month
         * @see SimpleDateFormat
         * @see DataModel
         * @see DataLoader
         */
        public Set<Integer> getDaysInMonth() {
            return daysInMonth;
        }
    
        public Integer getDayInMonth(int day) {
            return this.daysInMonth.stream().filter(d -> d.equals(day)).findFirst().orElse(null);
        }
    
        public void setDaysInMonth(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // If the day is not already in the list, add it
            if (!this.daysInMonth.contains(calendar.get(Calendar.DAY_OF_MONTH))) {
                this.daysInMonth.add(calendar.get(
                    Calendar.DAY_OF_MONTH)); // Day of month
            }
        }
    
        /**
         * getDaysInWeek
         * 
         * If the date is null, returns a new empty list.
         * If not, extract the day from the date and add it to the days list
         * The excel contains a column with dates in the format "dd-mm-yyyy", Ex: 01-01-2021
         * We extract only the day from the date and display it in the chart
         * 
         * The days are displayed as strings, Ex: Monday, Tuesday, Wednesday
         * 
         * @return List of days in the week as strings
         * @see SimpleDateFormat
         * @see DataModel
         * @see DataLoader
         */
        public Map<Integer, String> getDaysInWeek() {
            return daysInWeek;
        }
    
        public String getDayInWeek(int day) {
            return this.daysInWeek.get(day);
        }
    
        public void setDaysInWeek(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // If the day is not already in the map, add it
            if (!this.daysInWeek.containsKey(calendar.get(Calendar.DAY_OF_WEEK))) {
                this.daysInWeek.put(calendar.get(
                    Calendar.DAY_OF_WEEK), // Day of week
                    calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())); // Get day name
            }
        }
    
        /**
         * getProductData
         * 
         * Get the product data
         * 
         * If the product data is null, returns null.
         * 
         * @see DataModel
         * @see DataLoader
         * 
         * @return Product data
         */
        public ProductData getProductData() {
            return this.productData;
        }
    
        public void setProductData(ProductData productData) {
            this.productData = productData;
        }
    
        /**
         * getData
         * 
         * Get the data
         * 
         * If the data is null, returns null.
         * 
         * @see DataModel
         * @see DataLoader
         * 
         * @param data The data
         * @return Data
         */
        public Map<Date, Map<String, ProductData>> getData() {
            return data == null ? null : data;
        }
    
        public void setData(DataModel data) {
            // Ordered structure that stores the data sorted by the date.
            // The key is the date and the value is product data sorted by it's name.
            // private Map<Date, Map<String, ProductData>> data = new TreeMap<>();
            this.data = data.getData();
        }
    
        /**
         * getDataModel
         * 
         * Get the data model
         * 
         * If the data model is null, returns null.
         * 
         * @see DataModel
         * @see DataLoader
         * 
         * @return Data model
         */
        public static DataModel getDataModel() {
            return dataModel == null ? null : dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        DataModel.dataModel = dataModel;
    }
}
