package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Tile extends StackPane {
    private int x;
    private int y;
    private final int BORDER_SIZE = 1;
    private final int TILE_SIZE = 40;
    private boolean readOnly;
    private boolean selected;
    private Rectangle cell = new Rectangle(TILE_SIZE - 2 * BORDER_SIZE, TILE_SIZE - 2 * BORDER_SIZE);
    private Rectangle border = new Rectangle(TILE_SIZE, TILE_SIZE);
    private Text text = new Text();

    Tile(int x, int y, char value) {
        this.x = x;
        this.y = y;
        String text = Character.toString(value);
        if (text.equals("0")) {
            this.text.setText("");
            this.readOnly = false;
        } else {
            this.text.setText(text);
            this.readOnly = true;
        }
        this.border.setStroke(Color.BLUE);
        this.border.setFill(Color.BLUE);
        this.cell.setStroke(Color.LIGHTGRAY);
        this.cell.setFill(Color.WHITE);
        this.text.setFont(Font.font(18));
        this.text.setVisible(true);
        this.border.setVisible((selected));
        getChildren().addAll(border, cell, this.text);
        int BONUS_BORDER = 2;
        setTranslateX(x * TILE_SIZE + BORDER_SIZE + BONUS_BORDER * (this.x / 3));
        setTranslateY(y * TILE_SIZE + BORDER_SIZE + BONUS_BORDER * (this.y / 3));
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Text getText() {
        return text;
    }

    void setSelected() {
        selected = true;
        border.setVisible(true);
        cell.setWidth(TILE_SIZE - 6 * BORDER_SIZE);
        cell.setHeight(TILE_SIZE - 6 * BORDER_SIZE);
    }

    void undoSelect() {
        this.border.setStroke(Color.BLUE);
        this.border.setFill(Color.BLUE);
        border.setVisible(false);
        cell.setWidth(TILE_SIZE - 2 * BORDER_SIZE);
        cell.setHeight(TILE_SIZE - 2 * BORDER_SIZE);
    }

    void setTileText(Tile currentTile, String newText) {
        if (!currentTile.readOnly) {
            text.setText(newText);
        }
    }

    public void setError() {
        this.border.setStroke(Color.RED);
        this.border.setFill(Color.RED);
        this.text.setFill(Color.RED);
    }

    public void unSetError() {
        this.text.setFill(Color.BLACK);
        this.border.setStroke(Color.BLUE);
        this.border.setFill(Color.BLUE);
    }
}
