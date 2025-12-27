package pieces;

import board.Board;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

 @Override
public boolean isValidMove(Board board, int x1, int y1, int x2, int y2) {

    if (board.isSameColorPiece(x1, y1, x2, y2)) return false;

    if (Math.abs(x2 - x1) != Math.abs(y2 - y1)) return false;

    return board.isPathClear(x1, y1, x2, y2);
}

}
