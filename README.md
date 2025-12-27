# Dimag-ka-Khel-Chess-
<img width="889" height="963" alt="image" src="https://github.com/user-attachments/assets/26885a85-688b-4403-9b6c-c6ea46851566" />
📌 Project Overview

This project is a fully functional Chess Game developed using Core Java, Object-Oriented Programming (OOP), Data Structures, and Swing GUI.
It simulates a real chess match between two players with essential and advanced chess rules implemented.

The application focuses on clean architecture, real-world Java practices, and interactive gameplay, making it suitable for academic projects, interviews, and portfolio showcases.

🚀 Key Features
♟️ Core Gameplay

Two-player chess game (White vs Black)

Fully interactive 8×8 chessboard

Turn-based play with rule enforcement

Real chess movement rules for all pieces

🎨 GUI & UX

Java Swing based GUI

Chess piece icons instead of text

Valid move highlighting (Green)

Capture indication (Red square)

Undo & Redo functionality

Clean and responsive UI

⏱️ Game Controls

Separate timers for White and Black

Automatic timer switching per turn

Game automatically ends when time expires

♜ Advanced Chess Rules

Pawn Promotion (Queen, Rook, Bishop, Knight)

Castling (King-side and Queen-side)

Check detection

Checkmate handling

King capture prevention (as per real chess rules)

🏁 Game End Logic

Game ends with a victory message when:

Checkmate occurs

All opponent pieces are eliminated

Board disables automatically after game over

🔁 Move Management

Undo last move

Redo undone move

Internal move stack using data structures

💾 Database Integration (MySQL)

Move history stored using JDBC

MySQL used for persistent storage

Clean DAO design pattern

🧠 Concepts Used
💡 Java & OOP

Abstraction (Piece hierarchy)

Inheritance (Pawn, Rook, Knight, etc.)

Polymorphism (isValidMove() overrides)

Encapsulation (Board & Piece state handling)

🧮 Data Structures

Stack for Undo/Redo operations

2D Array for board representation

🖥️ GUI

Java Swing (JFrame, JButton, JPanel)

Event-driven programming

Custom UI updates

🔌 Database

JDBC

DAO pattern

MySQL Workbench

📁 Project Structure
src/
├── main/
│   └── ChessGame.java        (Application entry point)
│
├── gui/
│   └── ChessGUI.java         (GUI & game controller)
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
    ├── WPawn.png
    ├── BPawn.png
    └── (all chess piece icons)
