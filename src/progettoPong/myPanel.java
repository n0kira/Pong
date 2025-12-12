package progettoPong;

import javax.swing.*;
import java.awt.*;

public class myPanel extends JPanel {

    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

    int Pl1X = (int) screensize.getWidth() * 5/100, Pl1Y = (int) screensize.getHeight()/2;
    int Pl2X = (int) screensize.getWidth() * 95/100 - 30, Pl2Y = (int) screensize.getHeight()/2;

    int ballSize = 30;
    int ballX = 960 - ballSize / 2, ballY = 540;

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

        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, ballSize, ballSize);

        g.setColor(Color.WHITE);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
    }
}
