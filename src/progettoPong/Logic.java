package progettoPong;

import javax.swing.*;
import java.awt.*;

public class Logic {

    private Data data;

    private double ballSpeedX = 2;
    private double ballSpeedY = 2;
    private int playerSpeed = 3;

    private final double SPEED_INCREMENT = 0.5;
    private final int MAX_Y_SPEED = 4;

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

        //movimento palla
        panel.ballX += ballSpeedX;
        panel.ballY += ballSpeedY;

        //collisione con i muri superiori e inferiori
        if (panel.ballY <= 0 || panel.ballY >= panelHeight - panel.ballSize) {
            ballSpeedY = - ballSpeedY;
        }


        Rectangle ballBounds = new Rectangle(panel.ballX, panel.ballY, panel.ballSize, panel.ballSize);
        Rectangle p1Bounds = new Rectangle(panel.Pl1X, panel.Pl1Y, panel.sizeX, panel.sizeY);
        Rectangle p2Bounds = new Rectangle(panel.Pl2X, panel.Pl2Y, panel.sizeX, panel.sizeY);

        if (ballBounds.intersects(p1Bounds) || ballBounds.intersects(p2Bounds)) {

            ballSpeedX = - ballSpeedX;

            if(ballSpeedX > 0){
                ballSpeedX += SPEED_INCREMENT;
            }else{
                ballSpeedX -= SPEED_INCREMENT;
            }
        }

        if (ballBounds.intersects(p2Bounds)) {

        }

        if (panel.ballX < 0 || panel.ballX > panelWidth) {
            panel.ballX = panelWidth / 2;
            panel.ballY = panelHeight / 2;
            ballSpeedX = - ballSpeedX;
        }
    }
}

// tramite un offset che mi identifica dove la pallina ha colpito, tramite il
//nuovo valore ricaviamo la nuova velocità vrticale
// l'angolo di rimbalzo è calcolato implicitamente dal rapporto tra la velocità angolare e la velocità orizzontale
// la direzione non in angolo in gradi ma in vettori di velocità
/*
* nt playerY = ballBounds.intersects(p1Bounds) ? panel.Pl1Y : panel.Pl2Y;

            // Calcola i centri
            double playerCenterY = playerY + (panel.sizeY / 2.0);
            double ballCenterY = panel.ballY + (panel.ballSize / 2.0);

            // Calcola l'Offset d'Impatto (da -sizeY/2 a +sizeY/2)
            double offset = ballCenterY - playerCenterY;

            // Calcola il fattore di deviazione (normalizzato da -1.0 a +1.0)
            double maxOffset = panel.sizeY / 2.0;
            double deviationFactor = offset / maxOffset;

            // Applica la deviazione alla velocità Y, limitandola a MAX_VERTICAL_SPEED
            ballSpeedY = deviationFactor * MAX_VERTICAL_SPEED;

            // Impedisce che la palla si "blocchi" se colpita esattamente al centro e SpeedY=0
            if (Math.abs(ballSpeedY) < 1.0) {
                 ballSpeedY = ballSpeedY > 0 ? 1.0 : -1.0;
            }
        }

        // --- Gestione del Punto (Reset al centro) ---

        if (panel.ballX < 0 || panel.ballX > panelWidth) {
            // Reinizializza la pallina al centro
            panel.ballX = (panelWidth / 2) - (panel.ballSize / 2);
            panel.ballY = (panelHeight / 2) - (panel.ballSize / 2);

            // Reset delle velocità (o le mantieni aumentate per una partita più difficile)
            ballSpeedX = 3.0;
            ballSpeedY = 3.0;

            // Inverti la direzione in modo che l'altro giocatore inizi
            ballSpeedX = - ballSpeedX;
        }
*
*
* */
