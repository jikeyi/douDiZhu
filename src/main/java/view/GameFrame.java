package view;
import model.Player;
import model.Poker;
import model.PokerJLable;
import thread.ReceiveMessageThread;
import thread.SendMessageThread;
import util.ShowPokerUtil;

import javax.swing.*;
import java.net.Socket;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    private MyPanel myPanel;
    private String username;
    private Socket socket;
    private SendMessageThread sendMessageThread;
    private Player currentPlayer;
    private ArrayList<PokerJLable> pokerJLables = new ArrayList<>();
    private static final int WIDTH=1000;
    private static final int HEIGHT=800;


    public GameFrame(String username, Socket socket)  {
        this.username=username;
        this.socket=socket;

        // 设置游戏窗口属性
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置模板
        myPanel = new MyPanel();
        myPanel.setBounds(0,0,WIDTH,HEIGHT);
        this.add(myPanel);

        // 开启发消息线程
        new SendMessageThread(username,socket).start();
        // 开启接收消息线程
        new ReceiveMessageThread(socket,this).start();

    }


    public void showPlayer(ArrayList<Player> players){
        int m=0;
        for (Player player : players) {
            if(player.getName().equals(username)){
                currentPlayer = player;
                ArrayList<Poker> pokerList = currentPlayer.getPokerList();
                for (Poker poker : pokerList) {
                    PokerJLable pokerJLable = new PokerJLable(poker.getId(), poker.getName(), poker.getNum());
                    pokerJLable.turnUp();
                    this.myPanel.add(pokerJLable);
                    this.pokerJLables.add(pokerJLable);
                    this.myPanel.setComponentZOrder(pokerJLable,0);
                    ShowPokerUtil.show(pokerJLable,200+m++*25,600);

                }
            }
        }
    }
}
