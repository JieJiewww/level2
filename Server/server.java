package Server;

import Server.serverThread;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;

public class server {
    public static void main(String[]args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/resultOrder/oder.properties"));
        String port1 = properties.getProperty("port");
        int port = Integer.parseInt(port1);

        ServerSocket sv = new ServerSocket(port);   //监听端口

        System.out.println("正在等待客户端连接");
        while(true){
//            System.out.println("-----------------------");
            Socket socket = sv.accept();

            //将properties文件中的数据加载进hm这个hashmap中
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Order.hm.put((String)entry.getKey(),(String)entry.getValue());
            }
            System.out.println("当前存在的键值对有");
            System.out.println(Order.hm);
            //将所有的存储在properties中的数据全部导入进hm这个哈希表中  方便后面进行增删改查

//            启动多线程使用程序
            new Thread(new serverThread(socket)).start();
        }
    }
}
