package nio.net;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.charset.Charset;

public class PipeTest {

    public static void main(String[] args) throws IOException {
        /**
         * 实现单向管道的一对通道。
         * 管道由一对通道组成:可写接收器通道和可读源通道。一旦某些字节被写入接收器通道，就可以按照写入字节的顺序从源通道读取这些字节。
         * 向管道写入字节的线程是否会阻塞，直到另一个线程从管道读取这些字节，或者以前写入的一些字节，这取决于系统，因此未指定。许多管道实现将在接收器和源通道之间缓冲一定数量的字节，但是不应该假定存在这样的缓冲。
         */
        Pipe pipe = Pipe.open();
        
        SinkChannel sinkChannel =  pipe.sink();
        ByteBuffer buffer = Charset.forName("UTF-8").encode("通过单向管道发送数据");
        sinkChannel.write(buffer);
        
        SourceChannel sourceChannel = pipe.source();
        buffer.clear();
        sourceChannel.read(buffer);
        buffer.flip();
        System.out.println(Charset.forName("UTF-8").decode(buffer));
        
        sourceChannel.close();
        sinkChannel.close();
        
    }
}
