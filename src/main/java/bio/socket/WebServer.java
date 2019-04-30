package bio.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class WebServer {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8888);
            Tomcat tomcat = new Tomcat(server);
            tomcat.start();
            System.out.println("服务器已启动！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
