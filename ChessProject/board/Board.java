package board;

import pieces.*;

public class Board {

    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        initialize();
    }

    private void initialize() {

        // Black pieces
        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);

        // White pieces
        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);

        // Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false);
            board[6][i] = new Pawn(true);
        }
    }

    public Piece getPiece(int x, int y) {
        if (x < 0 || y < 0 || x >= 8 || y >= 8) return null;
        return board[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece;
    }
    public boolean hasAnyPiece(boolean isWhite) {
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            Piece p = getPiece(i, j);
            if (p != null && p.isWhite() == isWhite) {
                return true;
            }
        }
    }
    return false;
 }


    public boolean isPathClear(int x1, int y1, int x2, int y2) {
        int dx = Integer.compare(x2, x1);
        int dy = Integer.compare(y2, y1);

        int cx = x1 + dx;
        int cy = y1 + dy;

        while (cx != x2 || cy != y2) {
            if (board[cx][cy] != null) return false;
            cx += dx;
            cy += dy;
        }
        return true;
    }
    public boolean isSameColorPiece(int x1, int y1, int x2, int y2) {
    Piece p1 = getPiece(x1, y1);
    Piece p2 = getPiece(x2, y2);

    if (p1 == null || p2 == null) return false;
    return p1.isWhite() == p2.isWhite();
    }
}
