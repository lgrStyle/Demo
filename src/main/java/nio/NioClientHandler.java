package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioClientHandler implements Runnable{
    
    private Selector selector;
    
    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    public void run() {
        try {
            for(;;) {
                int selects = selector.select();
                if(selects == 0) continue;
                
                /**
                 * 获取可用channel的集合
                 */
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while(iterator.hasNext()) {
                    
                    /**
                     * selectionKey实例
                     */
                    SelectionKey selectionKey = iterator.next();
                    
                    /**
                     * **移除Set中的当前selectionKey**
                     */
                    iterator.remove();
                    
                    /**
                     * 处理不同事件
                     */
                    if(selectionKey.isAcceptable()) {
                        
                    }else if(selectionKey.isReadable()) {
                        readHandler(selectionKey);
                    }else if(selectionKey.isWritable()) {
                        
                    }else if(selectionKey.isConnectable()) {
                        
                    }
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readHandler(SelectionKey selectionKey) throws IOException {
        
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        
        String response = "";
        while(socketChannel.read(byteBuffer) > 0) {
            
            //转换读模式
            byteBuffer.flip();
            
            response += Charset.forName("UTF-8").decode(byteBuffer);
        }
        
        if(response.length() > 0) {
            System.out.println(response);
        }
    }
    
}
