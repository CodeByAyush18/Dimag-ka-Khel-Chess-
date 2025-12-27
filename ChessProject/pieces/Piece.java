package pieces;

import board.Board;

public abstract class Piece {

    protected boolean isWhite;
    protected boolean hasMoved = false;   // ✅ NEW

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    // ✅ REQUIRED FOR CASTLING
    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved() {
        this.hasMoved = true;
    }

    public abstract boolean isValidMove(Board board,
                                        int x1, int y1,
                                        int x2, int y2);
}
