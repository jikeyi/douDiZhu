package view;
import listener.MyMouseListener;
import lombok.Data;
import model.Player;
import model.Poker;
import model.PokerJLable;
import thread.ClockThread;
import thread.ReceiveMessageThread;
import thread.SendMessageThread;
import util.ShowPokerUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
@Data
public class GameFrame extends JFrame {
    private MyPanel myPanel;
    private String username;
    private Socket socket;
    private SendMessageThread sendMessageThread;
    private Player currentPlayer;
    private ArrayList<PokerJLable> pokerJLables = new ArrayList<>();
    private static final int WIDTH=1000;
    private static final int HEIGHT=800;
    private JLabel jiaoDiZhu;
    private JLabel buQiang;
    private JLabel jiShiQi;
    private ClockThread clockThread;


    public GameFrame()  {
    }

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
                }
        }
        ArrayList<Poker> pokerList = currentPlayer.getPokerList();
        for (Poker poker : pokerList) {
            PokerJLable pokerJLable = new PokerJLable(poker.getId(), poker.getName(), poker.getNum());
            pokerJLable.turnUp();
            this.myPanel.add(pokerJLable);
            this.pokerJLables.add(pokerJLable);
            this.myPanel.setComponentZOrder(pokerJLable,0);
            ShowPokerUtil.show(pokerJLable,200+m++*25,600);
        }


        if(currentPlayer.getId()==2){
                getLord();
        }
    }




    public void getLord(){

        MyMouseListener myMouseListener = new MyMouseListener();

        jiaoDiZhu = new JLabel();
        jiaoDiZhu.setBounds(350,500,104,46);
        jiaoDiZhu.setIcon(new ImageIcon("src\\main\\java\\imgs\\jiaodizhu.png"));
        jiaoDiZhu.addMouseListener(myMouseListener);
        this.myPanel.add(jiaoDiZhu);


         buQiang = new JLabel();
        buQiang.addMouseListener(myMouseListener);
         buQiang.setBounds(460,500,184,46);
         buQiang.setIcon(new ImageIcon("src\\main\\java\\imgs\\buqiang.png"));
         this.myPanel.add(buQiang);

         jiShiQi = new JLabel();

         jiShiQi.setBounds(350,400,80,80);
         jiShiQi.setIcon(new ImageIcon("src\\main\\java\\imgs\\clock\\15.png"));
         this.myPanel.add(jiShiQi);

         this.repaint();

         this.clockThread = new ClockThread(this);
         clockThread.start();

    }











      class MyMouseListener implements MouseListener {


        public MyMouseListener( ) {

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





    public MyPanel getMyPanel() {
        return myPanel;
    }

    public void setMyPanel(MyPanel myPanel) {
        this.myPanel = myPanel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public SendMessageThread getSendMessageThread() {
        return sendMessageThread;
    }

    public void setSendMessageThread(SendMessageThread sendMessageThread) {
        this.sendMessageThread = sendMessageThread;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<PokerJLable> getPokerJLables() {
        return pokerJLables;
    }

    public void setPokerJLables(ArrayList<PokerJLable> pokerJLables) {
        this.pokerJLables = pokerJLables;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public JLabel getJiaoDiZhu() {
        return jiaoDiZhu;
    }

    public void setJiaoDiZhu(JLabel jiaoDiZhu) {
        this.jiaoDiZhu = jiaoDiZhu;
    }

    public JLabel getBuQiang() {
        return buQiang;
    }

    public void setBuQiang(JLabel buQiang) {
        this.buQiang = buQiang;
    }

    public JLabel getJiShiQi() {
        return jiShiQi;
    }

    public void setJiShiQi(JLabel jiShiQi) {
        this.jiShiQi = jiShiQi;
    }

    public ClockThread getClockThread() {
        return clockThread;
    }

    public void setClockThread(ClockThread clockThread) {
        this.clockThread = clockThread;
    }
}
