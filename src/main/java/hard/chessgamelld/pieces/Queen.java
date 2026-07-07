package hard.chessgamelld.pieces;

import hard.chessgamelld.Board;
import hard.chessgamelld.Cell;
import hard.chessgamelld.Color;

public class Queen extends Piece{
    public Queen(Color color) {
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
        
        // Queen must move either diagonally (rowDiff == colDiff) OR straight (same row or same col)
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        if (rowDiff != colDiff && from.getRow() != to.getRow() && from.getCol() != to.getCol()) {
            return false;
        }
        
        // Calculate the direction of movement (1, -1, or 0)
        int rowStep = Integer.compare(to.getRow(), from.getRow());
        int colStep = Integer.compare(to.getCol(), from.getCol());
        
        // Check all intermediate cells to ensure the path is not obstructed
        int r = from.getRow() + rowStep;
        int c = from.getCol() + colStep;
        while (r != to.getRow() || c != to.getCol()) {
            if (board.getPiece(r, c) != null) {
                return false; // Path is blocked
            }
            r += rowStep;
            c += colStep;
        }
        
        // Valid move if all checks pass
        return true;
    }
}
