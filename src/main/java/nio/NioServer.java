package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.start();
    }
    
    public void start() throws IOException {
        
        Selector selector = Selector.open();
        
        ServerSocketChannel serverSocketChanel = ServerSocketChannel.open();
        
        serverSocketChanel.bind(new InetSocketAddress(8000));
        
        serverSocketChanel.configureBlocking(false);
        
        serverSocketChanel.register(selector, SelectionKey.OP_ACCEPT);
        
        System.out.println("服务端已启动！");
        
        while(true) {
            
            int selects = selector.select();
            if(selects == 0) continue;
            
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while(iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if(selectionKey.isAcceptable()) {
                    acceptHandler(serverSocketChanel, selector);
                }else if(selectionKey.isReadable()) {
                    readHandler(selectionKey);
                }else if(selectionKey.isWritable()) {
                    
                }else if(selectionKey.isConnectable()) {
                    
                }
            }
        }
    }
    
    private void acceptHandler(ServerSocketChannel serverSocketChanel, Selector selector) throws IOException {
        SocketChannel socketChannel = serverSocketChanel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        
        socketChannel.write(Charset.forName("UTF-8").encode("你已加入群聊"));
    }
    
    private void readHandler(SelectionKey selectionKey) throws IOException {
        
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        
        String request = "";
        while(socketChannel.read(byteBuffer) > 0) {
            
            //转换读模式
            byteBuffer.flip();
            
            request += Charset.forName("UTF-8").decode(byteBuffer);
        }
        
        if(request.length() > 0) {
            System.out.println(request);
        }
    }
    
    
}
