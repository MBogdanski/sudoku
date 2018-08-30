package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

class SudokuGame {
    private Tile[][] gameBoard;
    private Tile currentTile;

    private void selectTile(Tile newSelect, Tile oldSelect) {
        oldSelect.undoSelect();
        newSelect.setSelected();
        currentTile = newSelect;
    }

    Scene prepareGameBoard() throws IOException {
        Pane root = new Pane();
        root.setPrefSize(800, 367);
        root.setStyle("-fx-background-color: deepskyblue;");
        Scene scene = new Scene(root);

        this.gameBoard = GameStatusIO.readConstGame();

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
//            button.setOnAction(actionEvent -> currentTile.setTileText(currentTile, button.getText()));
            button.setOnAction(actionEvent -> setTileValue(currentTile, button.getText()));
            root.getChildren().add(button);
        }

        Button saveGame = new Button();
        saveGame.setText("Save current game");
        saveGame.setOnAction(actionEvent -> GameStatusIO.saveGame(this.gameBoard));

        Button loadGame = new Button();
        loadGame.setText("Load last game");
        saveGame.setOnAction(actionEvent -> this.gameBoard = GameStatusIO.loadGame());

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
        checkSolution();
    }

    private void checkSolution() {

    }
}
