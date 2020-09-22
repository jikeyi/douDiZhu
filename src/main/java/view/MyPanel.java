package view;

import javax.swing.*;
import java.awt.*;


public class MyPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        Image background = new ImageIcon("src\\main\\java\\imgs\\background.png").getImage();
        g.drawImage(background,0,0,this.getWidth(),this.getHeight(),null);
    }

    public MyPanel() {
        this.setLayout(null);
    }
}
