package sample;

import java.io.*;

class GameStatusIO {

    private static String gameFilePath = "src/sample/gameFile";

    public static Tile[][] readConstGame() throws IOException {
        Tile[][] gameBoard = new Tile[9][9];
        char[][] columnsLines = new char[9][9];
        File gameFile = new File("src/sample/constGameFile");
        try (BufferedReader br = new BufferedReader(new FileReader(gameFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
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

    public static void saveGame(Tile[][] gameBoard) {
        System.out.println("work");
        File gameFile = new File(gameFilePath);

        if (gameFile.exists()){
            if (gameFile.delete()){
                try {
                    gameFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(gameFile));
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    String value = gameBoard[x][y].getText().getText();
                    if (value.contentEquals("")){
                        writer.append("0");
                    } else {
                        writer.append(value);
                    }
                }
                writer.append("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Tile[][] loadGame() {
        Tile[][] gameBoard = new Tile[9][9];
        char[][] columnsLines = new char[9][9];
        File gameFile = new File(gameFilePath);
        try (BufferedReader br = new BufferedReader(new FileReader(gameFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameBoard;
    }
}
