package db;

import board.Move;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class GameDAO {

    public static void saveMove(Move m) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "INSERT INTO moves (piece, from_x, from_y, to_x, to_y, is_white) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, m.getPiece());
            ps.setInt(2, m.getFromX());
            ps.setInt(3, m.getFromY());
            ps.setInt(4, m.getToX());
            ps.setInt(5, m.getToY());
            ps.setBoolean(6, m.isWhiteMove());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
