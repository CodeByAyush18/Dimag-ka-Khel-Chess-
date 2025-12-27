package util;

import board.Move;
import java.util.Stack;

public class UndoRedoManager {

    private Stack<Move> undo = new Stack<>();
    private Stack<Move> redo = new Stack<>();

    public void recordMove(Move m) {
        undo.push(m);
        redo.clear();
    }

    public Move undo() {
        if (!undo.isEmpty()) {
            Move m = undo.pop();
            redo.push(m);
            return m;
        }
        return null;
    }

    public Move redo() {
        if (!redo.isEmpty()) {
            Move m = redo.pop();
            undo.push(m);
            return m;
        }
        return null;
    }
}
