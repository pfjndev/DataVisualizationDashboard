package com.dashboard.data;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class DataLoader {
    
    private final ExcelParser parser;

    public DataLoader() {
        this.parser = new ExcelParser();
    }

    public void loadData(File file, Consumer<List<DataModel>> onDataLoaded, Consumer<String> onError) throws IOException {
        SwingWorker<List<DataModel>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<DataModel> doInBackground() throws Exception {
                return parser.parseExcelFile(file);
            }

            @Override
            protected void done() {
                try {
                    onDataLoaded.accept(get());
                } catch (Exception e) {
                    onError.accept("Failed to load data: " + e.getMessage());
                }
            }
        };
        worker.execute();
    }
}
