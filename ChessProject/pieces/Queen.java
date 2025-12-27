package pieces;

import board.Board;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
public boolean isValidMove(Board board, int x1, int y1, int x2, int y2) {

    if (board.isSameColorPiece(x1, y1, x2, y2)) return false;

    boolean straight = x1 == x2 || y1 == y2;
    boolean diagonal = Math.abs(x2 - x1) == Math.abs(y2 - y1);

    if (!straight && !diagonal) return false;

    return board.isPathClear(x1, y1, x2, y2);
}

}
