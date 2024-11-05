package com.dashboard.utils;

import java.awt.Cursor;

public class FileUtils {
    
    public static void showLoadingIndicator(boolean isLoading, java.awt.Component component) {
        if (isLoading) {
            // Assuming you want to set the cursor for a component, you need to pass the component as a parameter
            // For example, if you have a JFrame component, you can do:
            // component.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            // Here, we will add a parameter to the method to accept a component
            component.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        } else {
            component.setCursor(Cursor.getDefaultCursor());
        }
    }
}
