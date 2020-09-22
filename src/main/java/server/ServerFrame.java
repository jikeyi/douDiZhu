package server;

import java.io.DataOutputStream;
import java.lang.String;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.Data;
import model.Player;
import model.Poker;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class ServerFrame {
    private ArrayList<Player> playerList=new ArrayList<Player>();
    private ArrayList<Poker> pokerList=new ArrayList<Poker>();
    private ArrayList<Poker> diZhuPai=new ArrayList<Poker>();
    private ServerSocket serverSocket;
    private Socket accept;
    private int index=0;
    public ServerFrame() throws IOException {

         serverSocket = new ServerSocket(9898);
        System.out.println("服务端准备就绪");
        while (true) {
            accept = serverSocket.accept();

            new Thread(new Runnable() {
                public void run() {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
                        while (true) {
                            String msg = dataInputStream.readUTF();
                            System.out.println(msg + "登录了");
                            Player player = new Player(index++,msg);
                            player.setSocket(accept);
                            playerList.add(player);
                            System.out.println("当前玩家数："+playerList.size());
                            if(playerList.size()==3){
                                createPork();
                                faPai();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }



    // 创建牌
    public void createPork(){

          String[] names = new  String[]{"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
         String[] colors = new  String[]{"梅花","方块","红桃","黑桃"};

         //  创建大小王
        Poker black_joker = new Poker(55, "BLACK_JOKER", 16);
        Poker red_joker = new Poker(56, "RED_JOKER", 17);
        pokerList.add(black_joker);
        pokerList.add(red_joker);

        int i =3;
         int n=3;
        for (String name : names) {
            for (String color : colors) {
                Poker poker = new Poker(i++,color+name,n);
                pokerList.add(poker);
            }
            n++;
        }
        // 洗牌
        Collections.shuffle(pokerList);
    }

    public void faPai() throws IOException {
        int a =0;
        List<List<Poker>> listList = Lists.partition(pokerList, 17);

        for (List<Poker> pokers : listList) {
            if(pokers.size()==17){
                if(a!=3) {
                    ArrayList<Poker> arrayList = new ArrayList<Poker>();
                    arrayList.addAll(pokers);
                    playerList.get(a++).setPokerList(arrayList);
                }
            } else{
                diZhuPai.addAll(pokers);
            }
        }

        // 发给客户端
        String playersJSON = JSON.toJSONString(playerList);
        for(int x=0;x<playerList.size();x++){
            DataOutputStream outputStream = new DataOutputStream(playerList.get(x).getSocket().getOutputStream());
            outputStream.writeUTF(playersJSON);
            System.out.println("成功发送玩家信息到 :"+playerList.get(x).getName()+" 的客户端");
        }
    }


    public static void main(String[] args) throws IOException {
        new ServerFrame();
    }
}
