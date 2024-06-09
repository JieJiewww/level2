package resultOrder;

public class userManual {

    public static void help() {
        System.out.println("以下为本程序使用手册");
        System.out.println("使用规则为   host:port>指令名称   ");
        System.out.println("例如    127.0.0.1>set" + " 执行set指令  ");
        System.out.println();
        System.out.println();
        System.out.println("help指令 获取所有command指令的使用方式\n" +
                "help [command]指令 获取单个command指令适用方式\n" +
                "set = [key] [value]指令 存储 key-value 类型数据\n" +
                "get = [key]指令 获取 key 对应的 value\n" +
                "del = [key]指令 删除 key 对应的 value\n" +
                "lpush = [key] [value]指令 可直接放一个数据在左端\n" +
                "rpush = [key] [value]指令 可直接放一个数据在右端\n" +
                "range = [key] [start] [end]指令 将key 对应 start 到 end 位置的数据全部返回\n" +
                "len  = [key]指令 获取 key 存储数据的个数\n" +
                "lpop = [key]指令 获取key最左端的数据并删除\n" +
                "rpop = [key]指令 获取key最右端的数据并删除\n" +
                "ldel = [key]指令 删除key 所有的数据\n" +
                "ping =心跳指令，ping响应pong\n" +
                "输入>886指令退出程序");
        System.out.println();
        System.out.println();
    }
}
