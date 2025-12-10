package progettoPong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class myKeyAdapter extends KeyAdapter {

    private Data data;

    public myKeyAdapter(Data data) {
        this.data = data;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            data.isL1Up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            data.isL1Down = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            data.isL2Up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            data.isL2Down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            data.isL1Up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            data.isL1Down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            data.isL2Up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            data.isL2Down = false;
        }
    }
}
