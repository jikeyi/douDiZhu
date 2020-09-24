package thread;

import lombok.Data;
import view.GameFrame;
import view.MyPanel;

import javax.swing.*;
import java.awt.*;


public class ClockThread extends Thread {
    private GameFrame gameFrame;
    private Integer num;


    public ClockThread(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.num=14;
    }

    @Override
    public void run() {
        while (num>0) {
            try {
                Thread.sleep(1000);
                gameFrame.getJiShiQi().setIcon(new ImageIcon("src\\main\\java\\imgs\\clock\\"+num--+".png"));


            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }
}
