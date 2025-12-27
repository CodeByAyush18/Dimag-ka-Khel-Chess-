# Dimag-ka-Khel-Chess-
<img width="889" height="963" alt="image" src="https://github.com/user-attachments/assets/26885a85-688b-4403-9b6c-c6ea46851566" />
About the Project

This project is a desktop-based Chess game developed using Core Java and Swing.
It allows two players to play a complete chess match with most real chess rules implemented.

The main goal of the project was to understand how object-oriented design, game logic, GUI handling, and state management work together in a real application, rather than solving isolated coding problems.

Basic Gameplay

Two-player chess game (White vs Black)

Standard 8×8 chessboard

Turn-based moves

Illegal moves are not allowed

Chess Rules Implemented

Correct movement rules for all pieces

Capturing opponent pieces

Pawn promotion (Queen, Rook, Bishop, Knight)

Castling (King-side and Queen-side)

Check detection

King capture is prevented (game ends instead)

Game Ending

The game ends when:

A king is threatened with no valid continuation (checkmate handling)

All opponent pieces are eliminated

Time runs out

Once the game ends, the board is disabled and no further moves are allowed.

User Interface

Built using Java Swing

Chess piece icons instead of text

Valid moves are highlighted

Captured squares are highlighted in red

Undo and Redo functionality

Separate timers for both players

Data Storage

Move history is stored using MySQL

JDBC is used for database connectivity

DAO pattern is followed for clean separation of logic

Technologies Used

Java (Core Java)

Java Swing (GUI)

OOP concepts (Inheritance, Polymorphism, Abstraction)

JDBC

src/
├── main/
│   └── ChessGame.java
│
├── gui/
│   └── ChessGUI.java
│
├── board/
│   ├── Board.java
│   └── Move.java
│
├── pieces/
│   ├── Piece.java
│   ├── Pawn.java
│   ├── Rook.java
│   ├── Knight.java
│   ├── Bishop.java
│   ├── Queen.java
│   └── King.java
│
├── logic/
│   └── CheckDetector.java
│
├── util/
│   ├── GameTimer.java
│   └── UndoRedoManager.java
│
├── db/
│   ├── DBConnection.java
│   └── GameDAO.java
│
└── icons/
    └── (chess piece images)


MySQL

Basic data structures (Stacks, Arrays)
