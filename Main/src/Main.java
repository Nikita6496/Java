import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Инициализируем базу данных при запуске
        Database.initDatabase();

        SwingUtilities.invokeLater(() -> new UI().createAndShow());
    }
}