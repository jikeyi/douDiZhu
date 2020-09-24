package view;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.Data;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;

@Data
public class LoginFrame extends JFrame {
    private JLabel usernameLable;
    private JTextField usernameInput;
    private JButton button;

    public LoginFrame() {
         this.usernameLable = new JLabel("请在下面输入框输入用户名");
         this.usernameInput = new JTextField();
         this.button = new JButton("登录");


         // 设置窗口大小
         this.setSize(600,300);
         // 设置窗口初始化在屏幕的位置
         this.setLocationRelativeTo(null);
        // 设置窗口可以被看见
         this.setVisible(true);
        // 设置布局方式
         this.setLayout(new GridLayout(3,1));
         // 设置窗口关闭后程序也关闭
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //添加组件
         this.add(usernameLable);
         this.add(usernameInput);
         this.add(button);

         // 给登录按钮注册监听器
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String username= usernameInput.getText();
                    if(username!=null && username!=""){
                    LoginFrame.this.dispose();
                    Socket socket = new Socket("127.0.0.1",9898);
                    new GameFrame(username,socket);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // 给回车键也绑定监听事件
//        usernameInput.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                try {
//                    String username= usernameInput.getText();
//                    if(username!=null && username!=""){
//                        LoginFrame.this.dispose();
//                        Socket socket = new Socket("127.0.0.1",9898);
//                        new GameFrame(username,socket);
//                    }
//
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
    }


}
