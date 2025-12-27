package util;

import javax.swing.*;

public class GameTimer {

    private int seconds;      // ✅ class-level variable
    private Timer timer;
    private JLabel label;

    public GameTimer(int seconds, JLabel label) {
        this.seconds = seconds;
        this.label = label;

        timer = new Timer(1000, e -> {
            this.seconds--;   // ✅ allowed

            label.setText("Time: " + this.seconds);

            if (this.seconds <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Time Over!");
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public int getSeconds() {
        return seconds;
    }
}
