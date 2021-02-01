//Sapozhnikov Arkady
//JumpNBump(Main)
//25.03.18

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Main {
    static Level level;
    static int WIDTH = 1600;
    static int HEIGHT = 900;

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        JLabel label = new JLabel();
        label.setText("0");
        level = new Level("level.txt");
        class MyPanel extends JPanel {
            private final Background BACKGROUND = new Background();
            private final Score SCORE = new Score();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                try {
                    BACKGROUND.draw(g2d);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    level.draw(g2d);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SCORE.draw(g2d);
            }
        }
        MyPanel myPanel = new MyPanel();
        window.add(myPanel, BorderLayout.CENTER);
        Timer timer = new Timer(5, e -> {
            level.update();
            window.repaint();
        });
        timer.start();
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                for (Rabbit rabbit : level.rabbits) {
                    rabbit.keyPressed(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (Rabbit rabbit : level.rabbits) {
                    rabbit.keyReleased(e);
                }
            }
        });
        window.setVisible(true);
    }
}
