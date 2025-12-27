package pieces;

import board.Board;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);   // 🔥 THIS WAS MISSING
    }

  
    @Override
public boolean isValidMove(Board board, int x1, int y1, int x2, int y2) {

    if (board.isSameColorPiece(x1, y1, x2, y2)) return false;

    if (x1 != x2 && y1 != y2) return false;

    return board.isPathClear(x1, y1, x2, y2);
}

}
