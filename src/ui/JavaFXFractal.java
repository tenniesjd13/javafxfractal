package javafxlife;

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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * A JavaFX 8 program to draw a Mandelbrot fractal.
 *
 * @author John Phillips
 */
public class JavaFXFractal extends Application {

    Label lbStatus;
    Pane fractalPane;
    Stage pStage;

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;
        lbStatus = new Label("Status: starting JavaFXFractal...");
        fractalPane = new Pane();

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

    /**
     * Displays a menu for this application.
     *
     * FYI: menu accelerator key codes are listed at:
     * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCode.html
     * @return 
     */
    public MenuBar myMenuBar() {
        MenuBar myBar = new MenuBar();
        final Menu fileMenu = new Menu("File");
        final Menu speedMenu = new Menu("Speed");
        final Menu optionsMenu = new Menu("Options");
        final Menu helpMenu = new Menu("Help");

        myBar.getMenus().addAll(fileMenu, speedMenu, optionsMenu, helpMenu);

        /**
         * *********************************************************************
         * File Menu Section
         */
        MenuItem newCanvas = new MenuItem("New");
        newCanvas.setOnAction((ActionEvent e) -> {
//            fractalPane.clearCells();
//            fractalPane.drawCells();
//            fractalPane.pause();
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
         * *********************************************************************
         * Speed Menu Section
         */

        MenuItem pause = new MenuItem("Pause");
        pause.setAccelerator(KeyCombination.valueOf("Left"));
//        pause.setOnAction(e -> fractalPane.pause());
        speedMenu.getItems().add(pause);

        MenuItem play = new MenuItem("Play");
        play.setAccelerator(KeyCombination.valueOf("Right"));
//        play.setOnAction(e -> fractalPane.play());
        speedMenu.getItems().add(play);

        MenuItem faster = new MenuItem("Faster");
        faster.setAccelerator(KeyCombination.keyCombination("Ctrl+Up"));
//        faster.setOnAction(e -> fractalPane.increaseSpeed());
        speedMenu.getItems().add(faster);


        MenuItem slower = new MenuItem("Slower");
        slower.setAccelerator(KeyCombination.keyCombination("Ctrl+Down"));
//        slower.setOnAction(e -> fractalPane.decreaseSpeed());
        speedMenu.getItems().add(slower);


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
