package progettoPong;

import javax.swing.*;
import java.awt.*;

public class myPanel extends JPanel {

    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

    int Pl1X = (int) screensize.getWidth() * 5/100, Pl1Y = (int) screensize.getHeight()/2;
    int Pl2X = (int) screensize.getWidth() * 95/100 - 30, Pl2Y = (int) screensize.getHeight()/2;

    int ballSize = 30;
    int ballX = 960 - ballSize / 2, ballY = 540;

    int playerSizeX = 30, playerSizeY = 150;

    int score1X = ((int) screensize.getWidth() / 2) - 100, score1Y = (int) screensize.getHeight() * 5/100;
    int score2X = ((int) screensize.getWidth() / 2) + 40, score2Y = (int) screensize.getHeight() * 5/100;


    private Data data;
    public void setData(Data data) {
        this.data = data;
    }

    public myPanel() {
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(Pl1X, Pl1Y, playerSizeX, playerSizeY);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(Pl2X, Pl2Y, playerSizeX, playerSizeY);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(ballX, ballY, ballSize, ballSize);

        if (data != null) {
            int player1Score = data.player1Score,  player2Score = data.player2Score;

            g2d.setColor(Color.WHITE);

            // disegna 10px, salta 10px
            float[] linePattern = {10f, 10f};
            BasicStroke lineStroke = new BasicStroke(
                    2f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f,
                    linePattern,
                    0.0f
            );

            g2d.setStroke(lineStroke);
            g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
            g2d.setStroke(new BasicStroke(1f));

            g2d.drawString("Player 1 [" + player1Score + "]", score1X, score1Y);
            g2d.drawString("Player 2 [" + player2Score + "]", score2X, score2Y);
        }

    }
}
