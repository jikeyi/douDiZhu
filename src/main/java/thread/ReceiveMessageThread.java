package thread;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import model.Player;
import model.Poker;
import view.GameFrame;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
public class ReceiveMessageThread extends Thread {
    private Socket socket;
    private GameFrame gameFrame;

    public ReceiveMessageThread(Socket socket, GameFrame gameFrame) {
        this.socket = socket;
        this.gameFrame = gameFrame;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            while(true){

                ArrayList<Player> players = new ArrayList<>();

                String json = inputStream.readUTF();
                JSONArray playerJsonArray = JSONArray.parseArray(json);
                for (int i=0;i<playerJsonArray.size();i++){
                    JSONObject playerJson = (JSONObject) playerJsonArray.get(i);
                    Integer playerId = playerJson.getInteger("id");
                    String playerName = playerJson.getString("name");
                    JSONArray pokerListJsonArray = playerJson.getJSONArray("pokerList");

                    ArrayList<Poker> pokerList = new ArrayList<Poker>();

                    for (int k=0;k<pokerListJsonArray.size();k++){
                        JSONObject pokerJson = (JSONObject) pokerListJsonArray.get(k);
                        Integer pokerId = pokerJson.getInteger("id");
                        String pokerName = pokerJson.getString("name");
                        Integer pokerNum = pokerJson.getInteger("num");
                        Poker poker = new Poker(pokerId, pokerName, pokerNum);
                        pokerList.add(poker);

                        pokerList.sort(new Comparator<Poker>() {
                            public int compare(Poker o1, Poker o2) {
                                if(o1.getId()-o2.getId()>0){
                                    return 1;
                                } else{
                                    return -1;
                                }

                            }
                        });
                    }

                    Player player = new Player(playerId,playerName,pokerList);
                    players.add(player);
                }

                if(players.size()==3){
                    gameFrame.showPlayer(players);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
