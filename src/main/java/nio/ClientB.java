package nio;

import java.io.IOException;

public class ClientB {

    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
        nioClient.start("ClientB");
    }
}
