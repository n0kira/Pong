package progettoPong;

import javax.swing.*;
import java.awt.*;

public class Logic {

    private Data data;

    private int ballSpeedX = 2;
    private int ballSpeedY = 2;
    private int playerSpeed = 3;

    public Logic(myPanel panel, Data data) {
        this.data = data;

        SwingUtilities.invokeLater(panel::requestFocus);

        panel.setFocusable(true);
        panel.addKeyListener(new myKeyAdapter(data));

        Timer timer = new Timer(5, e -> {
            updateGame(panel);
            panel.repaint();
        });

        timer.start();
    }

    private void updateGame(myPanel panel) {
        int panelHeight = panel.getHeight();
        int panelWidth = panel.getWidth();
        int playerSize = panel.sizeX;

        if (data.isL1Down && panel.Pl1Y < panelHeight - playerSize * 2) {
            panel.Pl1Y += playerSpeed;
        }

        if (data.isL1Up && panel.Pl1Y > 0) {
            panel.Pl1Y -= playerSpeed;
        }

        if (data.isL2Down && panel.Pl2Y < panelHeight - playerSize * 2) {
            panel.Pl2Y += playerSpeed;
        }

        if (data.isL2Up && panel.Pl2Y > 0) {
            panel.Pl2Y -= playerSpeed;
        }

        panel.ballX += ballSpeedX;
        panel.ballY += ballSpeedY;

        if (panel.ballY <= 0 || panel.ballY >= panelHeight - panel.ballSize) {
            ballSpeedY = - ballSpeedY;
        }


        Rectangle ballBounds = new Rectangle(panel.ballX, panel.ballY, panel.ballSize, panel.ballSize);
        Rectangle p1Bounds = new Rectangle(panel.Pl1X, panel.Pl1Y, panel.sizeX, panel.sizeY);
        Rectangle p2Bounds = new Rectangle(panel.Pl2X, panel.Pl2Y, panel.sizeX, panel.sizeY);

        if (ballBounds.intersects(p1Bounds)) {
            ballSpeedX = - ballSpeedX;
        }

        if (ballBounds.intersects(p2Bounds)) {
            ballSpeedX = - ballSpeedX;
        }

        if (panel.ballX < 0 || panel.ballX > panelWidth) {
            panel.ballX = panelWidth / 2;
            panel.ballY = panelHeight / 2;
            ballSpeedX = - ballSpeedX;
        }
    }
}
