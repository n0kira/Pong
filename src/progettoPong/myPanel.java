package progettoPong;

import javax.swing.*;
import java.awt.*;

public class myPanel extends JPanel {
    int Pl1X = 100, Pl1Y = 540;
    int Pl2X = 1820, Pl2Y = 540;

    int sizeX = 30, sizeY = 60;

    public myPanel() {
        setBackground(Color.darkGray);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(Pl1X, Pl1Y, sizeX, sizeY);

        g.setColor(Color.RED);
        g.fillRect(Pl2X, Pl2Y, sizeX, sizeY);

    }
}
