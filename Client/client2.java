package Client;

import resultOrder.userManual;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class client2 {
    public static void main(String[]args) throws IOException {
//        阅读使用手册
        userManual.help();
        System.out.println("阅读完毕开始使用程序，使用程序过程中可随时使用help指令进行查看指令详细信息");


//          初始准备
        Scanner sc = new Scanner(System.in);
        Properties properties = new Properties();
        properties.load(new FileReader("src/resultOrder/oder.properties"));
        String sport = properties.getProperty("port");
        int port = Integer.parseInt(sport);
        String host = "127.0.0.1";
        String order = null;
//          初始准备




//        pingpong
        System.out.println("输入>ping指令开启程序");
        System.out.println();
        System.out.print(host + ":" + port);
        String net = sc.nextLine();//判断是不是输入>ping
        if (net.equals(">ping")) {
            Socket cl = null;
            try {
                cl = new Socket(host, port);    //阻塞  只有连接成功才会进行后续的操作
            } catch (IOException e) {
                System.err.println("未成功连接服务器可能的错误是没有打开服务器端或端口被占用，请检查");
                throw new RuntimeException(e);
            }
            System.out.println("---------------PONG---------------");
//        pingpong




//
//            进入程序后实现指令逻辑
//


//            //获得输入输出
            BufferedReader in = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            PrintWriter out = new PrintWriter(cl.getOutputStream(), true);
//            //获得输入输出


            while (true) {//无限循环进行模拟不断下达指令
                System.out.println();
                System.out.print(host+":"+port);
                order = sc.nextLine();
                if (">886".equals(order)) {
                    cl.close();
                    System.out.println("结束语");
                    break;
                }

                //写出指令到服务端
                out.println(order);
                String key = null;
                String value = null;
                boolean button = true;


                switch (order) {
                    case ">set":            //此处我的>set指令 逻辑是不能重复修改同一个键的值
                        System.out.print("请输入键:");
                        key = sc.nextLine();
                        out.println(key);
                        System.out.print("请输入值:");
                        value = sc.nextLine();
                        out.println(value);
                        break;

                    case ">get":
                        System.out.print("请输入键:");
                        key = sc.nextLine();
                        out.println(key);
                        break;

                    case ">del":
                        System.out.print("请输入键:");
                        key = sc.nextLine();
                        out.println(key);
                        break;

                    case ">help":
                        userManual.help();
                        break;

                    case ">lpush":
                        System.out.print("请输入键:");
                        key = sc.nextLine();
                        out.println(key);
                        System.out.print("请输入值:");
                        value = sc.nextLine();
                        out.println(value);
                        break;

                    case">rpush":
                        System.out.print("请输入键:");
                        key = sc.nextLine();
                        out.println(key);
                        System.out.print("请输入值:");
                        value = sc.nextLine();
                        out.println(value);
                        break;
////////////////////////////////////////////////////////////////////////
//                      指令未完成
//                    case">range":
//                        System.out.print("请输入键:");
//                        key = sc.nextLine();
//                        out.println(key);
//                        System.out.print("请输入起始索引:");
//                        int start = sc.nextInt();
//                        out.println(start);
//                        System.out.print("请输入末尾索引:");
//                        int end = sc.nextInt();
//                        out.println(end);
//
//                        String option= in.readLine();
//                        if(option.equals("true")){
//                            System.out.println(Order2.hl.containsKey(key));
//                            LinkedList<String> keyvalue =  Order2.hl.get(key);
//                            System.out.println(keyvalue);
//                            System.out.println(key+"的链表中的值依次是");
////                            for (String s : keyvalue) {
////                                System.out.print(s+" ");
////                            }
//                            button=false;
//                        }
//                        break;
/////////////////////////////////////////////////////////////


                    case">len":
                        System.out.print("请输入键:");
                        key = sc.nextLine();
                        out.println(key);
                        break;

                    case">lpop":
                        System.out.print("请输入键:");
                        key = sc.nextLine();
                        out.println(key);
                        break;
                    case">rpop":
                        System.out.print("请输入键:");
                        key = sc.nextLine();
                        out.println(key);
                        break;

                    case">ldel":
                        System.out.print("请输入键:");
                        key = sc.nextLine();
                        out.println(key);
                        break;
                    default:
                        System.out.println("输入的指令有误");
                }
                    String serverReward = in.readLine();
                    System.out.println("服务器说:"+serverReward);
                    System.out.println("");
            }
        }
        else{
            System.out.println("你输入的是非>ping指令，执行退出");
        }
    }
}
