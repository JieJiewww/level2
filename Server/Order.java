package Server;

import java.util.HashMap;

public class Order {
    public static HashMap<String,String> hm = new HashMap<>();      //声明静态HashMap使得全局共享
    public static int set(String key,String value){                //声明静态set方法指令
        if(!hm.containsKey(key)){        //判断是否存在键  存在就不修改直接返回
            hm.put(key,value);
            return 1;
        }
        else{
            return 0;
        }
    }
    public static String get(String key){                             //声明静态get方法指令
        if(hm.containsKey(key)){        //判断是否存在键  存在就直接返回
//            System.out.println("进入了吗");
            return hm.get(key);
        }
        else{                            //如果不存在就返回表示没有该键就更没有该值
            return "";
        }
    }
    public static String del(String key){                             //声明静态del方法指令
        if(hm.containsKey(key)){        //判断是否存在键  存在就直接返回
            return hm.remove(key);
        }
        else{                            //如果不存在就返回null表示没有该键就更没有该值
            return "";
        }
    }
}
