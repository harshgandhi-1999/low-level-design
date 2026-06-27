package hard.chessgamelld.pieces;

import hard.chessgamelld.Board;
import hard.chessgamelld.Cell;
import hard.chessgamelld.Color;

public class King extends Piece{

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        // Cannot move to the exact same cell
        if (from.getRow() == to.getRow() && from.getCol() == to.getCol()) {
            return false;
        }
        
        // Cannot capture a piece of the same color
        Piece destPiece = to.getPiece();
        if (destPiece != null && destPiece.getColor() == this.color) {
            return false;
        }
        
        // King can move exactly 1 step in any direction
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol()- from.getCol());
        return (rowDiff <= 1 && colDiff <= 1);
    }
}
