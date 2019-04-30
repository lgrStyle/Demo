package nio;

import java.io.IOException;

public class ClientA {

    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
        nioClient.start("ClientA");
    }
}
