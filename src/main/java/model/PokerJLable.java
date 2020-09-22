package model;

import lombok.Data;

import javax.swing.*;
@Data
public class PokerJLable  extends JLabel {
    private Integer id;
    private String name;
    private Integer num;
    private boolean isOut;
    private boolean isUp;


    public PokerJLable() {
        this.setSize(108,164);
    }

    public PokerJLable(Integer id, String name, Integer num, boolean isOut, boolean isUp) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.isOut = isOut;
        this.isUp = isUp;
        this.setSize(108,164);

        if(isUp){
            turnUp();
        } else {
            turnDown();
        }
    }

    public PokerJLable(Integer id, String name, Integer num) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.setSize(108,164);
    }

    public void turnDown(){
        this.setIcon(new ImageIcon("src\\main\\java\\imgs\\down.png"));
    }

    public void turnUp(){
        this.setIcon(new ImageIcon("src\\main\\java\\imgs\\"+this.id+".gif"));
    }
}
