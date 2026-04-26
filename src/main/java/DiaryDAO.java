import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {

    public boolean saveEntry(int userId, String entryDate, String title, String content, String mood) {
        String sql = "INSERT INTO diary (user_id, entry_date, title, content, mood) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, entryDate);
            stmt.setString(3, title);
            stmt.setString(4, content);
            stmt.setString(5, mood);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String[]> getEntries(int userId) {
        List<String[]> entries = new ArrayList<>();
        String sql = "SELECT id, entry_date, title, content, mood FROM diary WHERE user_id = ? ORDER BY entry_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                entries.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("entry_date"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("mood")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public boolean deleteEntry(int id, int userId) {
        String sql = "DELETE FROM diary WHERE id = ? AND user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}