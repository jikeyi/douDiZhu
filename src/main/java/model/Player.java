package model;



import lombok.Data;

import java.net.Socket;
import java.util.ArrayList;


public class Player {
    private int id;
    private String name;
    private ArrayList<Poker> pokerList=new ArrayList<Poker>();
    private Socket socket;

    public Player(int id, String name, ArrayList<Poker> pokerList, Socket socket) {
        this.id = id;
        this.name = name;
        this.pokerList = pokerList;
        this.socket = socket;
    }

    public Player(int id, String name, ArrayList<Poker> pokerList) {
        this.id = id;
        this.name = name;
        this.pokerList = pokerList;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Player(int id, String name, Socket socket) {
        this.id = id;
        this.name = name;
        this.socket = socket;
    }

    public Player(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
    }

    public Player(int id) {
        this.id = id;
    }

    public Player(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Poker> getPokerList() {
        return pokerList;
    }

    public void setPokerList(ArrayList<Poker> pokerList) {
        this.pokerList = pokerList;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
