package board;

import pieces.Piece;

public class Move {

    private int fromX, fromY, toX, toY;
    private Piece movedPiece;
    private Piece capturedPiece;
    private boolean whiteMove;

    public Move(int fromX, int fromY, int toX, int toY,
                Piece movedPiece, Piece capturedPiece, boolean whiteMove) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
        this.whiteMove = whiteMove;
    }

    // 🔥 THIS METHOD WAS MISSING
    public String getPiece() {
        return movedPiece.getClass().getSimpleName();
    }

    public int getFromX() { return fromX; }
    public int getFromY() { return fromY; }
    public int getToX() { return toX; }
    public int getToY() { return toY; }

    public boolean isWhiteMove() { return whiteMove; }

    public Piece getMovedPiece() { return movedPiece; }
    public Piece getCapturedPiece() { return capturedPiece; }
}
