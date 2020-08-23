package com.own.linkedlist.test;

import java.util.HashMap;
import java.util.Map;

public class ArrayTest<T> {

    private static final int DEFAULT_CAPACITY = 2 << 3;

    private int capacity;
    private int count;
    private T[] array;
    private Map<T,Integer> holder;

    public ArrayTest(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayTest(int capacity){
        this.capacity = capacity;
        array = (T[])new Object[capacity];
        holder = new HashMap(capacity);
    }

    public void add(T t){
        if (t == null)
            throw new IllegalArgumentException("t is not allowed null");

        Integer i = holder.get(t);
        if (i == null) {
            if (isFull()){
                //若不存在，且已满,1.删除末尾数据，2.所有数据后移一位，3.保存在首位
                delete(count-1);
                remove(count-1);
                saveFirst(t);
            }else {
                //若不存在且未满，执行2,3
                remove(count-1);
                saveFirst(t);
            }
        }else {
           //若存在，更新，原数据前所有元素后移一位，并保存在首位
            remove(i-1);
            array[0] = t;
            holder.put(t,0);

        }
    }

    public void saveFirst(T t){
        array[0] = t;
        holder.put(t,0);
        count++;
    }

    public void remove(int i){
        for (; i>=0; i--){
            array[i+1] = array[i];
            holder.put(array[i+1],i+1);
        }
    }

    public void delete(int i) {
        holder.remove(array[i]);
        count--;
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++){
            sb.append(array[i]);
            sb.append(",");
        }
        String a = sb.toString();
        System.out.println(a.substring(0,a.length()-1));
    }

    public boolean isFull(){
        return count == capacity;
    }

    public static void main(String[] args) {
        ArrayTest<Object> test = new ArrayTest(4);
        test.add(1);
        test.print();

        test.add(2);
        test.print();

        test.add(3);
        test.print();

        test.add(5);
        test.print();

        test.add(4);
        test.print();

        test.add(2);
        test.print();

    }

}
