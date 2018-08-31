package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

class SudokuGame {
    private Tile[][] gameBoard;
    private Tile currentTile;
    private int[][] solutionBoard = new int[][]
    {
            {4,7,3,2,9,6,8,1,5},
            {2,9,5,1,3,8,4,7,6},
            {6,1,8,5,7,4,3,9,2},
            {8,4,7,6,1,9,5,2,3},
            {1,5,6,8,2,3,9,4,7},
            {9,3,2,4,5,7,6,8,1},
            {5,6,1,9,4,2,7,3,8},
            {7,8,4,3,6,1,2,5,9},
            {3,2,9,7,8,5,1,6,4}
    };

    private void selectTile(Tile newSelect, Tile oldSelect) {
        oldSelect.undoSelect();
        newSelect.setSelected();
        currentTile = newSelect;
    }

    Scene prepareGameBoard(Tile[][] gameBoard) throws IOException {
        this.gameBoard = gameBoard;
        Pane root = new Pane();
        root.setPrefSize(800, 367);
        root.setStyle("-fx-background-color: deepskyblue;");
        Scene scene = new Scene(root);
//        this.gameBoard = GameStatusIO.readConstGame();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Tile tile = this.gameBoard[x][y];
                tile.setOnMouseClicked(mouseEvent -> selectTile(tile, currentTile));
                root.getChildren().add(tile);
            }
        }

        for (int i = 1; i < 10; i++) {
            Button button = new Button();
            button.setText(String.valueOf(i));
            button.setTranslateX(370 + i * 30);
            button.setTranslateY(40);
            button.setOnAction(actionEvent -> setTileValue(currentTile, button.getText()));
            root.getChildren().add(button);
        }

        Button saveGame = new Button();
        saveGame.setText("Save current game");
        saveGame.setOnAction(actionEvent -> GameStatusIO.saveGame(this.gameBoard));

        Button loadGame = new Button();
        loadGame.setText("Load last game");
        loadGame.setOnAction(actionEvent -> {
            try {
                loadGame(saveGame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        root.getChildren().addAll(saveGame, loadGame);
        saveGame.setTranslateX(400);
        saveGame.setTranslateY(80);
        loadGame.setTranslateX(400);
        loadGame.setTranslateY(120);
        currentTile = this.gameBoard[0][0];
        currentTile.setSelected();
        return scene;
    }

    private void setTileValue(Tile tile, String text) {
        currentTile.setTileText(tile, text);
        checkSolution(tile);
    }

    private void checkSolution(Tile tile) {
        int value = solutionBoard[tile.getX()][tile.getY()];
        if (!tile.getText().getText().contentEquals(String.valueOf(value))){
            tile.setError();
        } else {
            tile.unSetError();
        }
    }

    private void loadGame(Button node) throws IOException {
        node.getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setTitle("Sudoku");
        stage.setScene(prepareGameBoard(GameStatusIO.loadGame()));
        stage.show();
    }
}
