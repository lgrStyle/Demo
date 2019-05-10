package collection;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class LinkedHashMapTest {

    public static void main(String[] args) {
        //默认以插入顺序遍历,HashMap没有顺序遍历
        LinkedHashMap<Integer, String> insertOrder = new LinkedHashMap<>();
        
        String value = "test";
        insertOrder.put(1, value);
        insertOrder.put(2, value);
        insertOrder.put(3, value);
        insertOrder.put(6, value);
        insertOrder.put(5, value);
        insertOrder.put(4, value);
        
        insertOrder.get(3);
        
        //默认以插入顺序遍历
//        Set<Integer> keySet = insertOrder.keySet();
//        for(Integer key : keySet) {
////            System.out.println(key + "-----" +insertOrder.get(key));
//            System.out.println(key);
//        }
        
        //默认以插入顺序遍历
        Set<Entry<Integer, String>> entrys = insertOrder.entrySet();
        for(Entry<Integer, String> entry : entrys) {
            System.out.println(entry.getKey());
        }
    }
}
