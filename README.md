# Data Visualization Dashboard

## Project Overview

The **Data Visualization Dashboard** is a Java-based application designed to display data visualizations based on various product performance metrics. It leverages the Java 2D API for custom graphics, shapes, and animations, while also integrating Excel data parsing for real-time, interactive data insights.

## Project Structure

The project is organized into key packages to promote modularity and maintainability:
- `app`: Application entry point and initialization.
- `config`: Configuration settings and constants.
- `data`: Data loading, parsing, and model classes for handling Excel data.
- `ui`: User Interface components, including the main dashboard layout, menu, and toolbar.
- `graphics`: Core graphics, including shapes, custom paints, transformations, and animations.
- `rendering`: Rendering pipeline and image processing components.
- `utils`: Helper utilities for file operations, math, and logging.

## Development Steps

### Step 1: Core Application & Configuration Setup
1. Implement `DashboardApp.java` in the `app` package as the main entry point. This initializes the application window and sets up essential components.
2. Create `DashboardFrame` using `JFrame` in the `ui` package, with the main layout and menu components.

### Step 2: UI Layout and Basic Components
1. Implement `DashboardPanel`, `MenuBar`, and `Toolbar` in the `ui` package.
   - **DashboardPanel**: Primary area where visualizations are displayed.
   - **MenuBar** and **Toolbar**: Add buttons for loading data, exporting data, and toggling views.
2. Configure the layout in `DashboardFrame` using `BorderLayout` to arrange these components.

### Step 3: Data Processing and Model Layer
1. Implement `DataLoader` and `ExcelParser` in the `data` package.
2. Use **Apache POI** for parsing data from Excel files.
3. Create a `DataModel` class to organize metrics such as Product Name, Customer Satisfaction Score, etc., and provide asynchronous data loading.

### Step 4: Rendering Core Graphics (Shapes and Paint)
1. Implement basic shapes in `BasicShapes.java` (rectangles, circles, polygons) using `Graphics2D`.
2. Set up Paint configurations (solid colors, gradients) in `ColorManager` and `GradientManager` in the `paint` package.
3. Implement stroke and basic transformation utilities in `TransformationUtils.java` for scaling and translation.

### Step 5: Custom Shapes with GeneralPath
1. Create custom shapes in `CustomShape.java` using `GeneralPath` to draw complex forms.
2. Integrate these shapes into `DashboardPanel` and apply transformations for visual testing.

### Step 6: User Interaction (Mouse and Keyboard)
1. Implement `UserInputHandler.java` for managing user interactions via mouse and keyboard.
2. Add support for dragging, zooming, and keyboard shortcuts in `DashboardPanel`.

### Step 7: Data Visualization Components
1. Create data visualizations like line and bar charts in `DashboardPanel` by drawing with basic shapes.
2. Read and map data from `DataModel` to these charts for dynamic visualization.

### Step 8: Animations and Dynamic Effects
1. Implement basic animations in `AnimationManager.java` using a `Timer`.
2. Use animations on data points (e.g., bar chart transitions) to enhance visual appeal and clarity.

### Step 9: Image Processing and Transparency
1. Implement filters and effects like grayscale and blur in `ImageProcessor.java`.
2. Use transparency settings with `AlphaComposite` to blend overlapping shapes.

### Step 10: Exporting and Printing
1. Implement export options in `PrintService.java` for high-quality visualizations.
2. Utilize vector-based rendering for consistent, clear output in various formats.

---

## Testing

- **Unit Testing**: Implement tests for core data parsing, rendering, and transformations in the `test` package.
- **UI Testing**: Verify menu interactions, toolbar actions, and general responsiveness.
  
## Running the Application

- **VS Code**: Open the project folder, configure Java paths, and use Maven to build and run.
- **Eclipse**: Import as a Maven project and use `Run As > Java Application` to launch the dashboard.