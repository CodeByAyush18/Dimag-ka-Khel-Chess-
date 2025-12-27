package pieces;

import board.Board;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
    }

   
    @Override
public boolean isValidMove(Board board, int x1, int y1, int x2, int y2) {

    if (board.isSameColorPiece(x1, y1, x2, y2)) return false;

    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);

    return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
}

}
