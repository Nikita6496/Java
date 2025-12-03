import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameStatsRepository {
    public static void addGameStat(String levelName, long completionTime, int stepsCount) {
        String sql = "INSERT INTO game_stats (level_name, completion_time, steps_count, completion_date) VALUES (?, ?, ?, ?)";

        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, levelName);
            pstmt.setLong(2, completionTime);
            pstmt.setInt(3, stepsCount);
            pstmt.setString(4, currentDate);

            pstmt.executeUpdate();
            System.out.println("Статистика игры сохранена в базу данных!");

        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении статистики:");
            e.printStackTrace();
        }
    }
}