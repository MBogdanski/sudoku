package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class GameStatusIO {

    static String gameFilePath = "/home/mariusz/IdeaProjects/Sudoku/src/sample/gameFile";

    static Tile[][] readConstGame() throws IOException {
        Tile[][] gameBoard = new Tile[9][9];
        char[][] columnsLines = new char[9][9];
        File gameFile = new File("/home/mariusz/IdeaProjects/Sudoku/src/sample/constGameFile");
        try (BufferedReader br = new BufferedReader(new FileReader(gameFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                System.out.println("Linia: " + line);
                columnsLines[i] = line.toCharArray();
                i++;
            }
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    char value = columnsLines[x][y];
                    gameBoard[x][y] = new Tile(x, y, value);
                }
            }
            return gameBoard;
        }
    }

    static void saveGame(Tile[][] gameBoard) {
        File gameFile = new File(gameFilePath);
    }

    static Tile[][] loadGame() {
        File gameFile = new File(gameFilePath);
        return new Tile[0][];
    }
}
