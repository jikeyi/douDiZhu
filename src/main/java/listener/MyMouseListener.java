package listener;

import view.GameFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {

    private JLabel jiaoDiZhu;
    private JLabel buQiang;

    public MyMouseListener(GameFrame gameFrame) {
        this.jiaoDiZhu = gameFrame.getJiaoDiZhu();
        this.buQiang = gameFrame.getBuQiang();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(jiaoDiZhu)){
            System.out.println("叫地主");
        }

        if(e.getSource().equals(buQiang)){
            System.out.println("不抢");
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
