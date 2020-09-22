package util;

import model.PokerJLable;

public class ShowPokerUtil {
    public static void show(PokerJLable pokerJLable,int x,int y){
        pokerJLable.setLocation(x,y);
      //  pokerJLable.setBounds(x,y,108,164);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
