package hard.chessgamelld.pieces;


import hard.chessgamelld.Board;
import hard.chessgamelld.Cell;
import hard.chessgamelld.Color;

public class Knight extends Piece {
    public Knight(Color color) {
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
        
        // Knight moves in an 'L' shape: 2 steps in one direction, 1 step in the other
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}