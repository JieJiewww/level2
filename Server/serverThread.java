package Server;

import Client.client;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class serverThread implements Runnable{
    Properties properties = new Properties();  //进行文件管理

//        ----------------------------------------------------
    private final Socket accept;               //进行读入读出
    public serverThread(Socket s) {
        this.accept = s;
    } //    定义aceept接收socket      通过构造方法赋值
//    ----------------------------------------------------



    //    重写多线程Run方法实现功能
    @Override
    public void run() {
        System.out.println("成功连接客户端");
        System.out.println();


        try {
//          建输入输出对象
            BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            PrintWriter out = new PrintWriter(accept.getOutputStream(),true);
            Scanner sc = new Scanner(System.in);




//            处理指令   其他指令都实现并能响应    除help[command]指令没有实现和拓展指令没有实现
            String order;
            while((order=in.readLine())!=null){
                System.out.println("执行"+order+"指令");
                String key="";
                String value="";


                switch (order){
//
//              以下是字符串类型（一个key对应一个字符串value）
//

// set
                    case ">set"://此处我的>set指令 逻辑是不能重复修改同一个键的值  我的想法是如果键已经存在  要进行覆盖的话则需要再进行一次交互确认覆盖（还没实现）
//
                        key = in.readLine();
                        value = in.readLine();

                        if(Order.set(key,value)==1){    //判断如果怎么样就写进去
//                            写入文件进行永久保存
                            properties.setProperty(key,value);
                            properties.store(new FileWriter("src/resultOrder/oder.properties"),"数据--键值对");
                            System.out.println("执行set指令成功--请在客户端查看并下达下一次指令");
                            out.println("成功添加 键:"+key+" -- "+"值:"+value);
                        }else{
//                            暂时设置为不能覆盖  直接执行失败
                            System.out.println("执行set指令失败");
                            out.println("因为键已经存在（此处应该添加一个确认按钮 未实现） 所以没有成功添加 键:"+key+"--"+"值:"+value);

//                            out.println("键已经存在其值为"+Server.Order.hm.get(key)+"请选择是否进行覆盖(输入yes或者no)");
//                            String option = sc.nextLine();
//                            if(option=="yes"){
//                                properties.setProperty(key,value);
//                                properties.store(new FileWriter("C:\\Program Files\\Java\\code\\level2\\src\\oder.properties"),"数据--键值对");
//                                System.out.println("执行set指令成功--请在客户端查看并下达下一次指令");
//                                out.println("成功添加 键:"+key+" -- "+"值:"+value);
//                            }
//                            else{
//
//                            }
                        }
                        break;

//                     get指令我定义了查询方法再order这个类中  调用其方法进而判断键值
//                    有键值  则返回值
//                    无键则  执行失败给出失败原因

//  get
                    case ">get":
                        key = in.readLine();
                        System.out.println(key);
                        value= Order.get(key);

                        if(!value.equals("")){
                            System.out.println("执行get指令成功--请在客户端查看并执行下一次指令");
                            out.println(key+"键值是"+value);
                        }
                        else{
                            System.out.println("执行get指令失败--请在客户端查看原因并执行下一次指令");
                            out.println("执行get指令失败 失败原因是:"+key+"没有对应的值");
                        }
                        break;

//                      del指令我定义了查询方法再order这个类中  调用其方法进而判断键值
//                      有键值 返回值
//                      无键值 就返回错误原因


// del
                    case ">del":
                        key = in.readLine();
                        value= Order.del(key);
                        if(!value.equals("")){
//                            从文件中进行删除 并保存文件
                            properties.remove(key);
                            properties.store(new FileWriter("C:\\Program Files\\Java\\code\\level2\\src\\oder.properties"),"数据--键值对");
                            System.out.println("执行del指令成功--请在客户端查看并执行下一次指令");
                            out.println("成功删除键:"+key+" -- "+"其值:"+value+" 成功");
                        }
                        else{
                            System.out.println("执行del指令失败--请在客户端查看原因并下达下一次指令");
                            out.println("执行del指令失败 原因是数据中没有对应的"+key+"可以进行删除");
                        }
                        break;


//
//                  双向链表类型（一个key对应一个双向链表，即可左右遍历，可以当栈，也可以当队列使用的数据结构
//
                    case ">lpush":
                        key = in.readLine();  //读入数据
                        value = in.readLine();//读入数据
                        Order2.lpush(key,value);
                        System.out.println("执行lpush指令成功--请在客户端查看并执行下一次指令");
                        out.println("lpush指令执行成功，新加入的键是:"+key+" 值是:"+value);
                        break;

                    case">rpush":
                        key = in.readLine();
                        value = in.readLine();

                        Order2.rpush(key,value);

                        System.out.println("执行rpush指令成功--请在客户端查看并执行下一次指令");
                        out.println("rpush指令执行成功，新加入的键是:"+key+" 值是:"+value);
                        break;

//                    case">range":
//                        //
//                        key = in.readLine();
//                        int start = Integer.parseInt(in.readLine());
//                        int end = Integer.parseInt(in.readLine());
//
//                        List<String> keyvalue =  Order2.range(key,start,end);
//                        if(keyvalue.size()!=0){
//                            out.println("true");
//                            System.out.println("执行range指令成功--请在客户端查看并执行下一次指令");
//                        }
//                        else{
//                            out.println("false");
//                            System.out.println("执行range指令失败--请在客户端查看原因并执行下一次指令");
//                            out.println(key+"的值不存在，或索引异常");
//                        }
//                        break;

                    case">len":
                        key = in.readLine();
                        int count =  Order2.len(key);
                        System.out.println("执行len指令成功--请在客户端查看并执行下一次指令");
                        out.println(key+"其值的个数是:"+count);
                        break;

                    case">lpop":
                        key = in.readLine();
                        value =  Order2.lpop(key);
                        if(!value.equals(null)){
                            System.out.println("执行lpop指令成功--请在客户端查看并执行下一次指令");
                            out.println("lpop指令执行成功，被操作的键是:"+key+" 被删除的值是:"+value);
                        }else{
                            System.out.println("执行lpop指令失败--请在客户端查看原因并执行下一次指令");
                            out.println("lpop指令执行失败 原因是该键没有值");
                        }
                        break;

                    case">rpop":
                        key = in.readLine();
                        value = Order2.rpop(key);
                        if(!value.equals(null)){
                            System.out.println("执行rpop指令成功--请在客户端查看并执行下一次指令");
                            out.println("rpop指令执行成功，新加入的键是:"+key+" 被删除的值是:"+value);
                        }else{
                            System.out.println("执行rpop指令失败--请在客户端查看原因并执行下一次指令");
                            out.println("rpop指令执行失败 原因是键:"+key+"没有值");
                        }
                        break;

                    case">ldel":
                        key = in.readLine();

                        if(Order2.ldel(key)){
                            System.out.println("执行ldel指令成功--请在客户端查看并执行下一次指令");
                            out.println("ldel指令执行成功 被操作的键:"+key);
                        }
                        else{
                            System.out.println("执行ldel指令失败--请在客户端查看原因并执行下一次指令");
                            out.println("ldel指令执行失败 原因是不存在键:"+key);
                        }
                        break;
                    default:
                        System.out.println("输入的指令有误");
                        out.println("输入的指令有误");
                }
                System.out.println("");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
