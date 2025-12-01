import javax.swing.*;
import java.awt.*;

public class UI {
    private JFrame frame;
    private GamePanel panel;

    void createAndShow(){
        frame = new JFrame("Управление роботом");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1050,600);

        JPanel topPanel = new JPanel();
        JButton forwardButton = new JButton("Вперёд");
        JButton leftButton = new JButton("Влево");
        JButton rightButton = new JButton("Вправо");
        JLabel statusLabel = new JLabel("Статус: готов");

        JTextField stepField = new JTextField("30", 4);
        stepField.setFocusable(false);

        topPanel.add(new JLabel("Шаг:"));
        topPanel.add(stepField);
        topPanel.add(forwardButton);
        topPanel.add(leftButton);
        topPanel.add(rightButton);
        topPanel.add(statusLabel);

        panel = new GamePanel(statusLabel, stepField);

        forwardButton.addActionListener(e -> panel.handleControlInput(ControlType.FORWARD));
        leftButton.addActionListener(e -> panel.handleControlInput(ControlType.LEFT));
        rightButton.addActionListener(e -> panel.handleControlInput(ControlType.RIGHT));

        forwardButton.setFocusable(false);
        leftButton.setFocusable(false);
        rightButton.setFocusable(false);

        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }
}
