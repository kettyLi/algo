package com.own.linkedlist.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 易错点：
 * 1、添加元素count未自增
 * 2、移动元素时，首位也可以移动
 * 3、数组已满时，再添加元素，需删除末尾元素
 * @param <T>
 */
public class LRUArrayTest<T> {

    private static final int DEFAULT_CAPACITY = 8;
    private int capaticy;
    private int count;
    private T[] array;
    private  Map<T,Integer> holder;

    public LRUArrayTest(){
       this(DEFAULT_CAPACITY);
    }

    public LRUArrayTest(int capaticy){
        this.capaticy = capaticy;
        this.array = (T[])new Object[capaticy];
        this.holder = new HashMap(capaticy);
    }

    public void add(T t){
        if (t == null) {
            throw new IllegalArgumentException("t is not allowed null");
        }
        Integer i = holder.get(t);
        if (i == null){
            if (isFull()){
                //若数组中不存在该元素，且数组数据已满，从倒数第二位后移一位，并保存在首位,将最后一个元素删除
                delete(count-1);
                removeAndSaveFirst(count-1,t);
            }else {
                //若数组中不存在该元素，但数组未满，从最后一位元素后移一位，并保存在首位
                removeAndSaveFirst(count-1,t);
            }
        }else {
            //若存在，从该元素的前一数据后移一位，将该数据保存在首位
            update(i-1,t);
        }
    }

    public void delete(int index){
        T t = array[index];
        holder.remove(t);
        count--;
    }

    public void update(int index, T t){
        remove(index);
        array[0] = t;
        holder.put(t,0);
    }

    public void removeAndSaveFirst(int index, T t){
        remove(index);
        saveFirst(t);
    }

    public void remove(int index){
        for (int i=index; i>=0; i--){
            array[i+1] = array[i];
            holder.put(array[i+1],i+1);
        }
    }


    public void saveFirst(T t){
        array[0] = t;
        holder.put(t,0);
        count++;
    }
    public boolean isFull(){
        return count==capaticy;
    }

    public void printArray(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< count; i++) {
            sb.append(array[i]);
            sb.append(",");
        }
        String s = sb.toString();
        System.out.println(s.substring(0,s.length()-1));
    }

    public static void main(String[] args) {
        LRUArrayTest<Object> arrayTest = new LRUArrayTest(4);
        arrayTest.add("a");
        arrayTest.printArray();

        arrayTest.add("b");
        arrayTest.printArray();

        arrayTest.add("c");
        arrayTest.printArray();

        arrayTest.add("a");
        arrayTest.printArray();

        arrayTest.add("e");
        arrayTest.printArray();

        arrayTest.add("f");
        arrayTest.printArray();

        System.out.println(arrayTest.holder.toString());
    }
}
