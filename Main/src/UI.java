import javax.swing.*;
import java.awt.*;

public class UI {
    private JFrame frame;
    private GamePanel panel;

    void createAndShow(){
        frame = new JFrame("Управление роботом");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1050,600);

        JPanel top = new JPanel();
        JButton fwd = new JButton("Вперёд");
        JButton left = new JButton("Влево");
        JButton right = new JButton("Вправо");
        JLabel status = new JLabel("Статус: готов");

        JTextField stepField = new JTextField("30", 4);
        stepField.setFocusable(false);

        top.add(new JLabel("Шаг:"));
        top.add(stepField);
        top.add(fwd);
        top.add(left);
        top.add(right);
        top.add(status);

        panel = new GamePanel(status, stepField);

        fwd.addActionListener(e -> panel.executeCommand("ПРЯМО"));
        left.addActionListener(e -> panel.executeCommand("ВЛЕВО"));
        right.addActionListener(e -> panel.executeCommand("ВПРАВО"));

        fwd.setFocusable(false);
        left.setFocusable(false);
        right.setFocusable(false);

        frame.setLayout(new BorderLayout());
        frame.add(top, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }
}