package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * A JavaFX 8 program to draw a Mandelbrot fractal.
 *
 * @author John Phillips
 */
public class JavaFXFractal extends Application {

    final int WIDTH = 400;
    final int HEIGHT = 400;
    Label lbStatus;
    Pane fractalPane;
    Stage pStage;
    Canvas canvas;
    GraphicsContext g;
    int lastMouseX = WIDTH / 2;
    int lastMouseY = HEIGHT / 2;
    double magnification = 1;
    int maxLoops = 1000;
    double xCenterReal = 0.0;
    double yCenterReal = 0.0;

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;
        lbStatus = new Label("Status: starting JavaFXFractal...");
        fractalPane = new Pane();

        // set the x,y cell count proportional to the screen size or 100 whichever is greater
//        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//        int screenWidth = (int) primaryScreenBounds.getWidth();
//        System.out.println("width=" + screenWidth);
//        int screenHeight = (int) primaryScreenBounds.getHeight();
//        System.out.println("height=" + screenHeight);
        // create the main array for the game of life simulation
//        this.cells = new int[xCellCount][yCellCount];
//        randomizeCells();
        // a canvas is used to display a visual representation of the simulation
        canvas = new Canvas();
        canvas.setWidth(WIDTH);
        canvas.setHeight(HEIGHT);
        fractalPane.getChildren().add(canvas);

        g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // each rectangular cell can be clicked to turn it on or off
        canvas.setOnMouseClicked((MouseEvent event) -> {
            lastMouseX = (int) event.getX();
            lastMouseY = (int) event.getY();
        });
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(myMenuBar());
        borderPane.setCenter(fractalPane);
        borderPane.setBottom(lbStatus);

        Scene scene = new Scene(borderPane, 800, 500);
        primaryStage.setTitle("JavaFXFractal");
        primaryStage.setScene(scene);

//        primaryStage.setMaximized(true);
//        primaryStage.setFullScreen(true);
        primaryStage.hide();
        primaryStage.show();

    }

    public void drawFractal() {
        double x, x0, x2, y, y0, y2;

        // panel width center point in pixel units
        int widthCenterPoint = WIDTH / 2;

        // increment is the pixel to pixel increment amount in real units
        double increment = 4.0 / magnification / WIDTH;

        xCenterReal = xCenterReal + ((lastMouseX - WIDTH / 2) * 4.0 / magnification / WIDTH);
        yCenterReal = yCenterReal - ((lastMouseY - WIDTH / 2) * 4.0 / magnification / WIDTH);

        double centerWidthReal = WIDTH / 2 * increment;
        double xTopLeftReal = xCenterReal - centerWidthReal;
        double yTopLeftReal = yCenterReal + centerWidthReal;

        // step through each row of the drawing area
        for (int py = 0; py < WIDTH; py++) {
            double yReal = yTopLeftReal - increment * py;
            double yRealSquared = yReal * yReal;

            // step through each column of a single row of pixels
            for (int px = 0; px < WIDTH; ++px) {
                x = x0 = xTopLeftReal + increment * px;
                y = y0 = yReal;
                x2 = x * x;
                y2 = yRealSquared;

                // calculate fractal value (i) for a single pixel
                // using the complex number formula z=z*z+c
                int i = 0;
                while (i < maxLoops && (x2 + y2 < 4)) {
                    y = 2 * x * y + y0;
                    x = x2 - y2 + x0;

                    x2 = x * x;
                    y2 = y * y;

                    ++i;
                }

                int blue = i > 255 ? i % 255 : 100;
                int green = i <= 255 ? i : 50;
                int red = i <= 255 ? i : 50;
                if (i == maxLoops) {
                    g.setFill(Color.BLACK);
                } else {
                    g.setFill(Color.rgb(red, green, blue));
                }
                g.fillRect(px, py, 1, 1);
            }
        }
    }

    /**
     * Displays a menu for this application.
     *
     * FYI: menu accelerator key codes are listed at:
     * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCode.html
     *
     * @return
     */
    public MenuBar myMenuBar() {
        MenuBar myBar = new MenuBar();
        final Menu fileMenu = new Menu("File");
        final Menu drawMenu = new Menu("Draw");
        final Menu optionsMenu = new Menu("Options");
        final Menu helpMenu = new Menu("Help");

        myBar.getMenus().addAll(fileMenu, drawMenu, optionsMenu, helpMenu);

        /**
         * *********************************************************************
         * File Menu Section
         */
        MenuItem newCanvas = new MenuItem("New");
        newCanvas.setOnAction((ActionEvent e) -> {
            g.setFill(Color.BLUE);
            g.fillRect(0, 0, 200, 150);
        });
        fileMenu.getItems().add(newCanvas);

        MenuItem open = new MenuItem("Open");
        open.setOnAction((ActionEvent e) -> {
//            fractalPane.pause();
//            fractalPane.clearCells();
//            FileChooser fileChooser = new FileChooser();
//            File file = fileChooser.showOpenDialog(pStage);
//            if (file != null) {
//                readFile(file);
//            }
//            fractalPane.drawCells();
        });
        fileMenu.getItems().add(open);

        MenuItem save = new MenuItem("Save");
        save.setOnAction((ActionEvent e) -> {
//            fractalPane.pause();
//            FileChooser fileChooser = new FileChooser();
//            File file = fileChooser.showSaveDialog(pStage);
//            if (file != null) {
//                writeFile(file);
//            }
        });
        fileMenu.getItems().add(save);
        MenuItem exit = new MenuItem("Exit");

        exit.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exit);
        /**
         * *********************************************************************
         * Draw Menu Section
         */

        MenuItem draw = new MenuItem("Draw");
        draw.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        draw.setOnAction(e -> {
            drawFractal();
        });
        drawMenu.getItems().add(draw);

        MenuItem zoomIn = new MenuItem("Zoom-In");
        zoomIn.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
        zoomIn.setOnAction(e -> {
            magnification *= 2;
//            drawFractal();
        });
        drawMenu.getItems().add(zoomIn);

        MenuItem zoomOut = new MenuItem("Zoom-Out");
        zoomOut.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        zoomOut.setOnAction(e -> {
            magnification = (magnification < 2) ? 1 : (magnification /= 2);
//            drawFractal();
        });
        drawMenu.getItems().add(zoomOut);

        /**
         * *********************************************************************
         * Options Menu Section
         */
        MenuItem randomize = new MenuItem("Randomize Cells");
        randomize.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
//        randomize.setOnAction(e -> fractalPane.randomizeCells());
        optionsMenu.getItems().add(randomize);

        CheckMenuItem color = new CheckMenuItem("Color Cells");
        color.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
//        color.setOnAction(e -> fractalPane.setShowColors(color.isSelected()));
        optionsMenu.getItems().add(color);

        /**
         * *********************************************************************
         * Help Menu Section
         */
        MenuItem about = new MenuItem("About");
        about.setOnAction((ActionEvent e) -> {
            String message = "Fractal\n";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
            alert.setTitle("About");
            alert.setHeaderText("JavaFXFractal v1.0 by John Phillips");
            alert.showAndWait();
        });
        helpMenu.getItems().add(about);

        return myBar;
    }

    /**
     * *************************************************************************
     * File helper methods
     */
    private void readFile(File myFile) {
        int y = 0;
        try (Scanner sc = new Scanner(myFile)) {
            while (sc.hasNextLine()) {
                String s[] = sc.nextLine().split("");
                System.out.println("s=" + Arrays.toString(s));
                for (int i = 0; i < s.length; i++) {

//                    lifePane.cells[i][y] = Integer.parseInt(s[i]);
                }
                y++;

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JavaFXFractal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    // only write 100x100 pattern to file
    private void writeFile(File myFile) {
        try (FileWriter writer = new FileWriter(myFile)) {
            StringBuilder sb = new StringBuilder("");
            for (int y = 0; y < 100; y++) {
                for (int x = 0; x < 100; x++) {
//                    sb.append(lifePane.cells[x][y]);
                }
                sb.append("\n");
            }
            writer.write(sb.toString());
            System.out.println(sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(JavaFXFractal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
