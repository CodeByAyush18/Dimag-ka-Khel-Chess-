package logic;

import board.Board;
import pieces.King;
import pieces.Piece;

public class CheckDetector {

    public static boolean isKingInCheck(Board board, boolean isWhite) {
        int kingX = -1, kingY = -1;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                Piece p = board.getPiece(i, j);
                if (p instanceof King && p.isWhite() == isWhite) {
                    kingX = i; kingY = j;
                }
            }

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                Piece p = board.getPiece(i, j);
                if (p != null && p.isWhite() != isWhite) {
                    if (p.isValidMove(board, i, j, kingX, kingY))
                        return true;
                }
            }
        return false;
    }
}
