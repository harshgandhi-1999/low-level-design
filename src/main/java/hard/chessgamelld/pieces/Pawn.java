package hard.chessgamelld.pieces;

import hard.chessgamelld.Board;
import hard.chessgamelld.Cell;
import hard.chessgamelld.Color;

public class Pawn extends Piece{

    public Pawn(Color color) {
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

        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getCol() - from.getCol());

        // Direction depends on the color (White moves up the board indices, Black moves down)
        int dir = (this.color == Color.WHITE) ? 1 : -1;
        // The starting row from which a pawn is allowed to move 2 steps
        int startRow = (this.color == Color.WHITE) ? 1 : 6;

        // Straight forward move (no column change)
        if (colDiff == 0) {
            // Standard 1-step move: destination must be empty
            if (rowDiff == dir) {
                return destPiece == null;
            } 
            // Initial 2-step move: both the intermediate and destination cells must be empty
            else if (rowDiff == 2 * dir && from.getRow() == startRow) {
                return destPiece == null && board.getPiece(from.getRow() + dir, from.getCol()) == null;
            }
        } 
        // Diagonal capture move: exactly 1 step diagonally forward
        else if (colDiff == 1 && rowDiff == dir) {
            // Must be capturing an enemy piece
            return destPiece != null;
        }
        
        // Invalid move for pawn
        return false;
    }

}
