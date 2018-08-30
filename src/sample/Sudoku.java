package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Sudoku extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SudokuGame sudokuGame = new SudokuGame();
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(sudokuGame.prepareGameBoard());
        primaryStage.show();
    }
}
