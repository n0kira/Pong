package progettoPong;

import javax.swing.*;
import java.awt.*;

public class Logic {

    private final Data data;

    // Velocità palla e giocatore
    private final double defaultBallSpeed = 4 ;
    private double ballSpeedX = defaultBallSpeed;
    private double ballSpeedY = defaultBallSpeed;

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

        int playerSpeed = 8;

        if (data.isP1Down && panel.Pl1Y < panelHeight - panel.playerSizeY) {
            panel.Pl1Y += playerSpeed;
        }

        if (data.isP1Up && panel.Pl1Y > 0) {
            panel.Pl1Y -= playerSpeed;
        }

        if (data.isP2Down && panel.Pl2Y < panelHeight - panel.playerSizeY) {
            panel.Pl2Y += playerSpeed;
        }

        if (data.isP2Up && panel.Pl2Y > 0) {
            panel.Pl2Y -= playerSpeed;
        }

        panel.ballX += (int) ballSpeedX;
        panel.ballY += (int) ballSpeedY;

        // Collisione bordo superiore ed inferiore
        if (panel.ballY <= 0 || panel.ballY >= panelHeight - panel.ballSize) {
            ballSpeedY = - ballSpeedY;
        }

        Rectangle ballBounds = new Rectangle(panel.ballX, panel.ballY, panel.ballSize, panel.ballSize);
        Rectangle p1Bounds = new Rectangle(panel.Pl1X, panel.Pl1Y, panel.playerSizeX, panel.playerSizeY);
        Rectangle p2Bounds = new Rectangle(panel.Pl2X, panel.Pl2Y, panel.playerSizeX, panel.playerSizeY);

        if (ballBounds.intersects(p1Bounds) || ballBounds.intersects(p2Bounds)) {

            // Inversione e aumento velocita
            ballSpeedX = -ballSpeedX;

            double SPEED_INCREMENT = 0.25;
            if (ballSpeedX > 0) {
                ballSpeedX += SPEED_INCREMENT;
            } else {
                ballSpeedX -= SPEED_INCREMENT;
            }

            // Determino giocatore colpito
            boolean isP1Hit = ballBounds.intersects(p1Bounds);
            int playerY;
            if (isP1Hit) {
                playerY = panel.Pl1Y;
            } else {
                playerY = panel.Pl2Y;
            }

            // Calcolo centro giocatore e palla
            double playerCenterY = playerY + (panel.playerSizeY / 2.0);
            double ballCenterY = panel.ballY + (panel.ballSize / 2.0);

            // Offset impatto
            double offset = ballCenterY - playerCenterY;

            // Calcolo deviazione
            double maxOffset = panel.playerSizeY / 2.0;
            double deviation = offset / maxOffset;

            // Applica deviazione
            int MAX_VERTICAL_SPEED = 5;
            ballSpeedY = deviation * MAX_VERTICAL_SPEED;

            int currentDirection;
            if (isP1Hit) {
                if (data.isP1Up) {
                    currentDirection = -playerSpeed;
                } else if (data.isP1Down) {
                    currentDirection = playerSpeed;
                } else {
                    currentDirection = 0;
                }
            } else {
                if (data.isP2Up) {
                    currentDirection = -playerSpeed;
                } else if (data.isP2Down) {
                    currentDirection = playerSpeed;
                } else {
                    currentDirection = 0;
                }
            }

            ballSpeedY += currentDirection * SPEED_INCREMENT;

            // Limito la velocita della palla
            if (Math.abs(ballSpeedY) > MAX_VERTICAL_SPEED * 1.5) {
                ballSpeedY = Math.signum(ballSpeedY) * (MAX_VERTICAL_SPEED * 1.5);
            }

            panel.ballX += (int) ballSpeedX;
        }

        // Evita che al primo avvio la palla si trovi a (0 ; 0)
        if (panelWidth < 500) {
            return;
        }

        // Check punteggio
        if (panel.ballX < 0) {
            resetGame(panel);
            data.player2Score++;
        } else if (panel.ballX > panelWidth - panel.ballSize) {
            resetGame(panel);
            data.player1Score++;
        }
    }

    void resetGame(myPanel panel) {
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();

        // Reset pallina al centro
        panel.ballX = (panelWidth / 2) - (panel.ballSize / 2);
        panel.ballY = (panelHeight / 2) - (panel.ballSize / 2);

        // Reset delle velocità iniziale
        ballSpeedX = defaultBallSpeed;
        ballSpeedY = defaultBallSpeed;

        // Scelta casuale direzione pallina per inizio partita
        if (Math.random() < 0.5) {
            ballSpeedX = -ballSpeedX;
        }

        if (Math.random() < 0.5) {
            ballSpeedY = -ballSpeedY;
        }
    }
}