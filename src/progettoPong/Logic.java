package progettoPong;

import javax.swing.*;
import java.awt.*;

public class Logic {

    private Data data;

    // Velocità dichiarate come double (per l'incremento di 0.5)
    private double ballSpeedX = 3.0;
    private double ballSpeedY = 3.0;
    private int playerSpeed = 5;

    private final double SPEED_INCREMENT = 0.5;
    private final int MAX_VERTICAL_SPEED = 4; // Massima velocità Y (per l'angolo)

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

        // --- Movimento Giocatori (Correzione dei limiti) ---
        // Utilizza panel.sizeY per l'altezza della racchetta

        if (data.isL1Down && panel.Pl1Y < panelHeight - panel.sizeY) {
            panel.Pl1Y += playerSpeed;
        }

        if (data.isL1Up && panel.Pl1Y > 0) {
            panel.Pl1Y -= playerSpeed;
        }

        if (data.isL2Down && panel.Pl2Y < panelHeight - panel.sizeY) {
            panel.Pl2Y += playerSpeed;
        }

        if (data.isL2Up && panel.Pl2Y > 0) {
            panel.Pl2Y -= playerSpeed;
        }

        // --- Movimento Palla ---
        panel.ballX += ballSpeedX;
        panel.ballY += ballSpeedY;

        // --- Collisione con Muri Superiori e Inferiori ---
        if (panel.ballY <= 0 || panel.ballY >= panelHeight - panel.ballSize) {
            ballSpeedY = - ballSpeedY;
        }


        Rectangle ballBounds = new Rectangle(panel.ballX, panel.ballY, panel.ballSize, panel.ballSize);
        Rectangle p1Bounds = new Rectangle(panel.Pl1X, panel.Pl1Y, panel.sizeX, panel.sizeY);
        Rectangle p2Bounds = new Rectangle(panel.Pl2X, panel.Pl2Y, panel.sizeX, panel.sizeY);

        // --- Gestione della Collisione con i Giocatori (LOGICA COMPLETA) ---

        if (ballBounds.intersects(p1Bounds) || ballBounds.intersects(p2Bounds)) {

            // 1. Inversione e Aumento di Velocità (Asse X)

            ballSpeedX = - ballSpeedX;

            if (ballSpeedX > 0) {
                ballSpeedX += SPEED_INCREMENT;
            } else {
                ballSpeedX -= SPEED_INCREMENT;
            }

            // 2. Calcolo dell'Angolo di Rimbalzo (Asse Y)

            // Determina quale giocatore è stato colpito
            int playerY = ballBounds.intersects(p1Bounds) ? panel.Pl1Y : panel.Pl2Y;

            // Calcola i centri (usa 2.0 per forzare la divisione in double)
            double playerCenterY = playerY + (panel.sizeY / 2.0);
            double ballCenterY = panel.ballY + (panel.ballSize / 2.0);

            // Offset d'Impatto
            double offset = ballCenterY - playerCenterY;

            // Fattore di Deviazione (Normalizzazione)
            double maxOffset = panel.sizeY / 2.0;
            double deviationFactor = offset / maxOffset;

            // Applica la deviazione: la nuova ballSpeedY è definita solo dal punto d'impatto
            ballSpeedY = deviationFactor * MAX_VERTICAL_SPEED;

            // Impedisce che la palla abbia una velocità verticale troppo bassa
            if (Math.abs(ballSpeedY) < 1.0) {
                ballSpeedY = deviationFactor > 0 ? 1.0 : -1.0;
            }

            // Sposta la palla per evitare che si incastri (opzionale, ma consigliato)
            panel.ballX += ballSpeedX;
        }

        // --- Gestione del Punto (Reset al centro) ---

        if (panel.ballX < 0 || panel.ballX > panelWidth) {
            // Reinizializza la pallina al centro (Perfettamente centrata)
            panel.ballX = (panelWidth / 2) - (panel.ballSize / 2);
            panel.ballY = (panelHeight / 2) - (panel.ballSize / 2);

            // Reset delle velocità iniziali
            ballSpeedX = 3.0;
            ballSpeedY = 3.0;

            // Inverti la direzione per far partire il servizio all'altro giocatore
            ballSpeedX = - ballSpeedX;
        }
    }
}