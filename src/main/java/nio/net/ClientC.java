package nio.net;

import java.io.IOException;

public class ClientC {

    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
        nioClient.start("ClientC");
    }
}
