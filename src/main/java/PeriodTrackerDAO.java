import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodTrackerDAO {

    public boolean logPeriod(int userId, String startDate, String endDate) {
        // Calculate cycle length from previous period
        int cycleLength = 0;
        String lastStartSql = "SELECT period_start FROM period_tracker WHERE user_id = ? ORDER BY period_start DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(lastStartSql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                java.sql.Date lastStart = rs.getDate("period_start");
                java.sql.Date currentStart = java.sql.Date.valueOf(startDate);
                cycleLength = (int) ((currentStart.getTime() - lastStart.getTime()) / (1000 * 60 * 60 * 24));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Insert new period
        String sql = "INSERT INTO period_tracker (user_id, period_start, period_end, cycle_length) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            stmt.setInt(4, cycleLength);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String[]> getPeriodHistory(int userId) {
        List<String[]> history = new ArrayList<>();
        String sql = "SELECT period_start, period_end, cycle_length FROM period_tracker WHERE user_id = ? ORDER BY period_start DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                history.add(new String[]{
                        rs.getString("period_start"),
                        rs.getString("period_end"),
                        String.valueOf(rs.getInt("cycle_length"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}