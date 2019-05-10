package nio.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
//import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("D:\\《鸟哥的Linux私房菜-基础篇》第四版.pdf");
        Path path2 = Paths.get("D:\\test.pdf");
        
//        if(!Files.exists(path2)) {
//            Files.createFile(path2);
//        }
        FileChannel readChannel = FileChannel.open(path1);
        FileChannel writeChannel = FileChannel.open(path2, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        
        long start = System.currentTimeMillis();
        
        ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
        while(readChannel.read(buf) != -1) {
            buf.flip();
            writeChannel.write(buf);
            buf.clear();
        };
        writeChannel.close();
        readChannel.close();
        
        long end = System.currentTimeMillis();
        System.out.println("时长：" + (end - start));
    }
}
