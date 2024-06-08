package Server;//双向链表类型（一个key对应一个双向链表，即可左右遍历，可以当栈，也可以当队列使用的数据结构）
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Order2 {
    public static HashMap<String, LinkedList<String>>hl = new HashMap<>();  //声明静态HashMap使得全局共享
    public static void lpush (String key,String value){         ////声明静态lpush方法指令
        LinkedList<String>currentKeyList = hl.putIfAbsent(key,new LinkedList<>());  //使用putifAbsent方法判断当前的键有没有对应的LinkedList
        if(currentKeyList==null){//如果没有    新建一个并进行赋值
            LinkedList<String> newList = new LinkedList<>();
            hl.put(key,newList);
            newList.add(0,value);
        }
        else{ //如果有就不用新建直接赋值即可
            currentKeyList.add(0,value);
        }
    }
    //rpush与lpush逻辑一致
    public static void rpush (String key,String value){          //声明静态rpush方法指令   与左插入逻辑一样
        LinkedList<String>currentKeyList = hl.putIfAbsent(key,new LinkedList<>());  //使用方法判断当前的键有没有对应的LinkedList
        if(currentKeyList==null){//如果没有
            LinkedList<String> newlist = new LinkedList<>();//新建一个并进行赋值
            hl.put(key,newlist);
            newlist.add(value);
        }
        else{ //如果有就不用新建直接赋值即可
            currentKeyList.add(value);
        }
    }


    //range没有完成
//    public static LinkedList range (String key,int range,int end){         //声明静态range方法指令    //遍历链表知识
//        LinkedList<String> tmp = new LinkedList<>();
//        if(hl.containsKey(key)){
//            LinkedList<String> currentKeyList = hl.get(key);
//            if(currentKeyList.size()!=0){
//                for (int i = range; i <= end; i++) {
//                    if(!currentKeyList.get(i).equals(null))
//                        tmp.add(currentKeyList.get(i));
//                }
//            }
//        }
//        return tmp;
//
//    }

    public static int len (String key){//声明静态len方法指令
        if(hl.containsKey(key)){  //先判断该键存不存在
            //如果存在  得到该键的链表
            LinkedList<String> currentKeyList = hl.get(key);
            return currentKeyList.size();  //返回链表的长度
        }
        return 0;//如果不存在那也就没有长度直接返回0
    }
    public static String lpop (String key){                     //声明静态lpop方法指令
        if(hl.containsKey(key)){  //先判断该键存不存在
            LinkedList<String>currentKeyList  = hl.get(key); //如果存在  得到该键的链表
            if(currentKeyList.size()==0){  //如果链表长度为0 删除不了返回null
                return null;
            }
            return currentKeyList.removeFirst();  //否则返回被删除的值
        }
        else {
            return null;
        }
    }
    //rpop与lpop逻辑一致
    public static String rpop (String key){                   //声明静态rpop方法指令
        if(hl.containsKey(key)){
            LinkedList<String>currentKeyList  = hl.get(key);
            if(currentKeyList.size()==0){
                return null;
            }
            return currentKeyList.removeLast();
        }
        else {
            return null;
        }
    }
    public static boolean ldel (String key){                 //声明静态ldel方法指令
        if(hl.containsKey(key)){  ////先判断该键存不存在
            LinkedList<String>currentKeyList  = hl.get(key);//如果存在  得到该键的链表
            if(currentKeyList.size()==0){//如果长度为0  不能删除直接返回false表示删除失败
                return false;
            }
            currentKeyList.clear();//不然就进行删除
            return true;//返回true
        }
        else {      //如果键都不存在直接返回false;
            return false;
        }
    }
}