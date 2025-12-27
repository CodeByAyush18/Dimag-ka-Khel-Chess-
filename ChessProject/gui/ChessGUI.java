package gui;

import board.Board;
import board.Move;
import logic.CheckDetector;
import pieces.Piece;
import util.GameTimer;
import util.UndoRedoManager;

import javax.swing.*;
import java.awt.*;

public class ChessGUI extends JFrame {

    private final JButton[][] buttons = new JButton[8][8];
    private final Board board = new Board();

    private int selectedX = -1;
    private int selectedY = -1;

    private boolean whiteTurn = true;

    private final UndoRedoManager undoRedoManager = new UndoRedoManager();

    private final JLabel whiteTimerLabel = new JLabel("White Time: 300");
    private final JLabel blackTimerLabel = new JLabel("Black Time: 300");

    private final GameTimer whiteTimer;
    private final GameTimer blackTimer;

    public ChessGUI() {

        setTitle("Java Chess Game");
        setSize(720, 780);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ================= TOP PANEL (TIMERS) =================
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(whiteTimerLabel);
        topPanel.add(Box.createHorizontalStrut(40));
        topPanel.add(blackTimerLabel);
        add(topPanel, BorderLayout.NORTH);

        // ================= BOARD PANEL =================
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        initializeBoard(boardPanel);
        add(boardPanel, BorderLayout.CENTER);

        // ================= BOTTOM PANEL =================
        JPanel bottomPanel = new JPanel();
        JButton undoBtn = new JButton("Undo");
        JButton redoBtn = new JButton("Redo");

        undoBtn.addActionListener(e -> undoMove());
        redoBtn.addActionListener(e -> redoMove());

        bottomPanel.add(undoBtn);
        bottomPanel.add(redoBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // ================= TIMERS =================
        whiteTimer = new GameTimer(300, whiteTimerLabel);
        blackTimer = new GameTimer(300, blackTimerLabel);
        whiteTimer.start();

        refreshBoard();
        setVisible(true);
    }

    // ======================================================
    // BOARD INITIALIZATION
    // ======================================================
    private void initializeBoard(JPanel boardPanel) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                JButton btn = new JButton();
                btn.setFocusPainted(false);
                btn.setFont(new Font("SansSerif", Font.BOLD, 28));
                btn.setOpaque(true);

                btn.setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);

                int x = i;
                int y = j;
                btn.addActionListener(e -> handleClick(x, y));

                buttons[i][j] = btn;
                boardPanel.add(btn);
            }
        }
    }

    // ======================================================
    // HANDLE CLICK
    // ======================================================
private void handleClick(int x, int y) {

    resetBoardColors();

    // ================= SELECT PIECE =================
    if (selectedX == -1) {
        Piece p = board.getPiece(x, y);
        if (p != null && p.isWhite() == whiteTurn) {
            selectedX = x;
            selectedY = y;
            highlightMoves(x, y);
        }
        return;
    }

    // ================= MOVE PIECE =================
    Piece movingPiece = board.getPiece(selectedX, selectedY);
    Piece targetPiece = board.getPiece(x, y);

    if (movingPiece != null &&
        movingPiece.isValidMove(board, selectedX, selectedY, x, y)) {

        // 🚫 PREVENT KING CAPTURE → END GAME
        if (targetPiece instanceof pieces.King) {
            JOptionPane.showMessageDialog(
                    this,
                    "CHECKMATE! " + (whiteTurn ? "WHITE" : "BLACK") + " WINS 🏆"
            );
            disableBoard();
            return;
        }

        boolean isCapture =
                targetPiece != null &&
                targetPiece.isWhite() != movingPiece.isWhite();

        Move move = new Move(
                selectedX, selectedY,
                x, y,
                movingPiece, targetPiece,
                whiteTurn
        );

        // ===== APPLY MOVE =====
        board.setPiece(x, y, movingPiece);
        board.setPiece(selectedX, selectedY, null);

        // Mark piece as moved (important for castling)
        movingPiece.setMoved();

        undoRedoManager.recordMove(move);

        // ===== PAWN PROMOTION =====
        handlePawnPromotion(x, y);

        if (isCapture) {
            buttons[x][y].setBackground(Color.RED);
        }

        switchTimers();

        if (CheckDetector.isKingInCheck(board, !whiteTurn)) {
            JOptionPane.showMessageDialog(this, "CHECK!");
        }

        whiteTurn = !whiteTurn;

        // ✅ CHECK GAME OVER ONLY AFTER A VALID MOVE
        checkGameOver();
    }

    selectedX = -1;
    selectedY = -1;
    refreshBoard();
}

    // ======================================================
    // HIGHLIGHT VALID MOVES
    // ======================================================
    private void highlightMoves(int x, int y) {

        Piece p = board.getPiece(x, y);
        if (p == null) return;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (p.isValidMove(board, x, y, i, j)) {
                    buttons[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }

    // ======================================================
    // RESET BOARD COLORS
    // ======================================================
    private void resetBoardColors() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].setBackground(
                        (i + j) % 2 == 0 ? Color.WHITE : Color.GRAY
                );
            }
        }
    }

    // ======================================================
    // REFRESH BOARD (ICONS)
    // ======================================================
    private void refreshBoard() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece p = board.getPiece(i, j);
                buttons[i][j].setText("");
                buttons[i][j].setIcon(p == null ? null : getPieceIcon(p));
            }
        }
    }

    // ======================================================
    // LOAD PIECE ICON
    // ======================================================
    private ImageIcon getPieceIcon(Piece p) {

    if (p == null) return null;

    String prefix = p.isWhite() ? "W" : "B";
    String pieceName = p.getClass().getSimpleName(); // Pawn, Rook, etc.

    String path = "/icons/" + prefix + pieceName + ".png";

    java.net.URL location = getClass().getResource(path);

    if (location == null) {
        System.err.println("Icon not found: " + path);
        return null;
    }

    Image img = new ImageIcon(location)
            .getImage()
            .getScaledInstance(48, 48, Image.SCALE_SMOOTH);

    return new ImageIcon(img);
}


    // ======================================================
    // UNDO / REDO
    // ======================================================
    private void undoMove() {

        Move move = undoRedoManager.undo();
        if (move == null) return;

        board.setPiece(move.getFromX(), move.getFromY(), move.getMovedPiece());
        board.setPiece(move.getToX(), move.getToY(), move.getCapturedPiece());

        whiteTurn = move.isWhiteMove();
        switchTimers();
        refreshBoard();
    }

    private void redoMove() {

        Move move = undoRedoManager.redo();
        if (move == null) return;

        board.setPiece(move.getToX(), move.getToY(), move.getMovedPiece());
        board.setPiece(move.getFromX(), move.getFromY(), null);

        whiteTurn = !move.isWhiteMove();
        switchTimers();
        refreshBoard();
    }

    // ======================================================
    // SWITCH TIMERS
    // ======================================================
    private void switchTimers() {

        if (whiteTurn) {
            whiteTimer.stop();
            blackTimer.start();
        } else {
            blackTimer.stop();
            whiteTimer.start();
        }
    }
    private void checkGameOver() {

    boolean whiteAlive = board.hasAnyPiece(true);
    boolean blackAlive = board.hasAnyPiece(false);

    if (!whiteAlive) {
        JOptionPane.showMessageDialog(this, "BLACK WINS! 🏆");
        disableBoard();
    } 
    else if (!blackAlive) {
        JOptionPane.showMessageDialog(this, "WHITE WINS! 🏆");
        disableBoard();
    }
  }
    private void disableBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    private void handlePawnPromotion(int x, int y) {

    Piece p = board.getPiece(x, y);
    if (!(p instanceof pieces.Pawn)) return;

    // White reaches row 0, Black reaches row 7
    if ((p.isWhite() && x != 0) || (!p.isWhite() && x != 7)) return;

    String[] options = {"Queen", "Rook", "Bishop", "Knight"};

    int choice = JOptionPane.showOptionDialog(
            this,
            "Promote pawn to:",
            "Pawn Promotion",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
    );

    Piece newPiece;
    switch (choice) {
        case 1 -> newPiece = new pieces.Rook(p.isWhite());
        case 2 -> newPiece = new pieces.Bishop(p.isWhite());
        case 3 -> newPiece = new pieces.Knight(p.isWhite());
        default -> newPiece = new pieces.Queen(p.isWhite());
    }

    board.setPiece(x, y, newPiece);
}



}
