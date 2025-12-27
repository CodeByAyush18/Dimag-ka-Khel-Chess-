package pieces;

import board.Board;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, int x1, int y1, int x2, int y2) {

        // Prevent capturing own piece
        if (board.isSameColorPiece(x1, y1, x2, y2)) {
            return false;
        }

        // Direction: white moves up (-1), black moves down (+1)
        int direction = isWhite ? -1 : 1;

        // ============================
        // 1️⃣ FORWARD MOVE (NO CAPTURE)
        // ============================
        if (y1 == y2 && board.getPiece(x2, y2) == null) {

            // One square forward
            if (x2 - x1 == direction) {
                return true;
            }

            // Two squares forward from starting position
            if ((isWhite && x1 == 6) || (!isWhite && x1 == 1)) {
                if (x2 - x1 == 2 * direction &&
                    board.getPiece(x1 + direction, y1) == null) {
                    return true;
                }
            }
        }

        // ============================
        // 2️⃣ DIAGONAL CAPTURE
        // ============================
        if (Math.abs(y2 - y1) == 1 && x2 - x1 == direction) {

            // Capture only if opponent piece exists
            if (board.getPiece(x2, y2) != null &&
                board.getPiece(x2, y2).isWhite() != isWhite) {
                return true;
            }
        }

        return false;
    }
}
