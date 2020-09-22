package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data

public class Poker {
    private int id;
    private String name;
    private int num;

    public Poker(int id) {
        this.id = id;
    }

    public Poker() {
    }

    public Poker(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Poker(int id, String name, int num) {
        this.id = id;
        this.name = name;
        this.num = num;
    }



    public Poker(String name, int num) {
        this.name = name;
        this.num = num;
    }


}
