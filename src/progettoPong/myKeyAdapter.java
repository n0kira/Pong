package progettoPong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class myKeyAdapter extends KeyAdapter {

    private final Data data;

    public myKeyAdapter(Data data) {
        this.data = data;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            data.isP1Up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            data.isP1Down = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            data.isP2Up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            data.isP2Down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            data.isP1Up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            data.isP1Down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            data.isP2Up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            data.isP2Down = false;
        }
    }
}
