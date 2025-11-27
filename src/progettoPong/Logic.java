package progettoPong;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Logic {

    private Data data;

    public Logic(myPanel panel, Data data) {
        this.data = data;

        SwingUtilities.invokeLater(panel::requestFocus);

        panel.setFocusable(true);
        panel.addKeyListener(new myKeyAdapter(data));

        Timer timer = new Timer(10, e -> {
            updateGame(panel);
        });

        timer.start();
    }

    private void updateGame(myPanel panel) {
        if (data.isL1Down) {
            panel.Pl1Y += 5;
        }

        if (data.isL1Up) {
            panel.Pl1Y -= 5;
        }
    }
}
