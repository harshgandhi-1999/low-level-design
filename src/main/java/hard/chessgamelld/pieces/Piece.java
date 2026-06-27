package hard.chessgamelld.pieces;

import hard.chessgamelld.Board;
import hard.chessgamelld.Cell;
import hard.chessgamelld.Color;

public abstract class Piece {
    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Board board, Cell from, Cell to);

    public Color getColor() {
        return color;
    }
}
