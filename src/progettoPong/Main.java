package progettoPong;

import javax.swing.*;
import java.awt.*;

public class Main {
    static void main() {
        JFrame myFrame = new JFrame("PONG :D");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.setResizable(true);

        myPanel panel = new myPanel();
        Data data = new Data();
        Logic logic = new Logic(panel, data);
        panel.setData(data);

        myFrame.add(panel);
        myFrame.setVisible(true);
    }
}
