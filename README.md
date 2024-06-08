# level2
# level2有感

## 项目的设计思路

1.  使用了socket知识搭建服务端，客户端

    &#x20;             ServerSocket sv \= new ServerSocket(port)

    &#x20;             Socket cl \= new Socket(host, port);

2.  使用了实现Runnable接口的第二种开启多线程的方式  使得服务端可以响应多台客户端                                                       public class serverThread implements Runnable

3.  Order指令 字符串类型（一个key对应一个字符串value）

    &#x20;            HashMap\<String,String> hm \= new HashMap<>()

    通过使用已经定义好的containsKey ，put， get， remove方法  实现了 set ，get ，del指令

4.  Order2指令 双向链表类型（一个key对应一个双向链表，即可左右遍历，可以当栈，也可以当队列使用的数据结构）      HashMap\<String, LinkedList\<String>>hl \= new HashMap<>()

    通过使用putIfAbsent，put，containsKey ，clear()，add，removeLast，removeFirst，size等方法  既有链表方法又有hashmap中的方法  实现lpush，rpush，lpop，rpop，ldel。     range指令没有实现  很可惜。。。。

5.  `ping` 心跳指令，ping响应pong     对于心跳指令我的理解是输入>ping才能进行连接  所以在代码里心跳指令的逻辑是  判断是否输入>ping  才进行获得连接![](Markdown_md_files/ad8ae4e0-255a-11ef-a401-59d7138ad438.jpeg?v=1\&type=image)

6.  `help` 获取所有command指令的使用方式  `help [command]` 获取单个command指令适用方式                                       对于两个help指令 我没有想到很好的解决办法尤其是help\[command]指令  由于代码中我采用switch case来判断执行的命令 导致有点不知道怎么实现 help\[command] 要输入这个还要分析他  有点迷糊了

    &#x20;         对于前一个获取所有指令 我的做法不知道算不算取巧，按照我的想法建一个公共类设计一个方法存放所有的指令使用概述           当需要执行help指令时调用help方法即可后期如果新加命令也可以直接在该类进行修改![](Markdown_md_files/033d6970-255c-11ef-a401-59d7138ad438.jpeg?v=1\&type=image)

7.  实现数据持久化，可将产生的`set`与`del`指令写入文件，开启程序时读取   使用配置文件存储监听端口号

    这里我使用了Java中的properties类    我使用load方法以及  properties的enterySet方法将我设置的properties文件的数据导入到hm的hashmap中实现开启数据时读取    &#x20;

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                        Order.hm.put((String)entry.getKey(),(String)entry.getValue());
                    }

    &#x20;在set指令中使用setProperty store方法  将产生的键值保存到我设置的properties文件               在del指令中使用remove store方法 将对应的键值删除并保存当前的properties文件

8.  io流的使用   服务端与客户端之间的交互我使用了BufferedReader,PrintWriter类   使用in.readLine，out.println

    方法进行读入写出&#x20;

        BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                    PrintWriter out = new PrintWriter(accept.getOutputStream(),true);

## 过程遇到的问题

*   起初看到题目：socket是什么 怎么使用    多线程是什么  hashmap的使用   io流的使用 &#x20;

*   经过不断网课学习  上网查资料找ai  终于明白其中逻辑  框架架构  但学习也花费很长时间

*   掌握理论知识后  开始实践   但如何设计响应 怎么使用各种方法，怎么找出bug又让我头疼

help的第二个指令 不知道怎么实现  &#x20;

range指令  尤其range指令怎么写都写不出来       将数据结合io流输出

io流有点复杂但又不能系统性看完课只能匆匆上手开始码   如自动刷新行flash()

以及各个指令响应的设计 有些甚至一开始就错误只能不断修改最终成型 &#x20;

还有就是遇到了完全不懂的问题。查完资料问ai最后都无果只能放弃的无奈。

很难形容问题  就像这份md文档我不知道怎么描述我遇到的让我无奈的问题一样。

## 心得体会

虽然这两周痛苦大过快乐，但也让我明白了我的不足。我学了socket，学了多线程，学了hashmap，properties，学会了客户端与服务端交互的使用的io流    最后将这些进行结合  或许做的不是完全正确。但看到能将部分的功能实现了还是很开心。剩下时间已经不够了所以没有实现日志，朋友和我说不是很难，有点可惜了。还是感谢有这次锻炼的机会让我看清了自己的不足。
