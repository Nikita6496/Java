import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:Main/db/app.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initDatabase() {
        String createGameStatsTable = """
            CREATE TABLE IF NOT EXISTS game_stats (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                level_name TEXT NOT NULL,
                completion_time INTEGER NOT NULL,
                steps_count INTEGER NOT NULL,
                completion_date TEXT NOT NULL
            );
        """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createGameStatsTable);
            System.out.println("База данных и таблица game_stats успешно инициализированы.");

        } catch (SQLException e) {
            System.out.println("Ошибка при инициализации базы данных:");
            e.printStackTrace();
        }
    }
}