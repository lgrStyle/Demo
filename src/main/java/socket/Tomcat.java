package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class Tomcat extends Thread{
    
    private ServerSocket server;
    
    public Tomcat(ServerSocket server) {
        this.server = server;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Socket client = server.accept();
                if(client != null) {
                    System.out.println("服务器收到请求");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String line = reader.readLine();
                    System.out.println("line："+line);
                    if(line != null) {
                        String resource = line.substring(line.indexOf("/"), line.lastIndexOf("/")-5);
                        System.out.println("the resource you request is: "+ resource);
                        
                        resource = URLDecoder.decode(resource, "UTF-8");
                        String method = new StringTokenizer(line).nextElement().toString();
                        System.out.println("the request method you send is: "+ method);
                        
                        while((line = reader.readLine()) != null) {
                            if("".equals(line)) {
                                break;
                            }
                            System.out.println("the http head is:"+line);
                        }
                        
                        if("post".equals(method.toLowerCase())) {
                            System.out.println("the post request body is:"+reader.readLine());
                        }else if("get".equals(method.toLowerCase())){
                            PrintWriter writer = new PrintWriter(client.getOutputStream());
                            writer.println("HTTP/1.1 200 OK");
                            writer.println("Content-Type:text/html;charset=utf-8");
                            writer.println();
                            writer.println("<html><body>");
                            writer.println("<a href='www.baidu.com'>百度</a>");
                            writer.println("<img src='https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'></img>");
                            writer.println("</html></body>");
                            writer.println();
                            writer.close();
                            client.close();
                            System.out.println(client+"已离开服务器");
                            continue;
                        }
                    }
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
