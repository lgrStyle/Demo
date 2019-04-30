package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NioClient {

    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
        nioClient.start("nioClient");
    }
    
    public void start(String nickname) throws IOException {
        
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8000));
        
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();
        
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String request = scanner.nextLine();
            if(request != null && !request.isEmpty()) {
                if("exit".equals(request)) {
                    scanner.close();
                }
                socketChannel.write(Charset.forName("UTF-8").encode(nickname + " : " +request));
            }
        }
    }
    
}