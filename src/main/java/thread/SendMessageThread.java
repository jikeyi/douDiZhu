package thread;

import lombok.Data;
import model.Poker;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Data
public class SendMessageThread extends Thread {

    private String msg;

    private Socket socket;

    public SendMessageThread(String msg, Socket socket) {
        this.msg = msg;
        this.socket = socket;
    }

    public SendMessageThread(Socket socket) {
        this.socket = socket;
    }

    public SendMessageThread(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        DataOutputStream out = null;

        try {
            out = new DataOutputStream(socket.getOutputStream());
            while(true){
                if(msg!=null && msg!=""){
                    out.writeUTF(msg);
                    msg=null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
