package com.own.stack.test;

/**
 * 顺序栈：以数组实现的栈
 *         出入栈时在数据末尾添加、删除数据，时间复杂度为O(1)
 */
public class StackOfArray {

    private static  int DEFAULT_CAPACITY = 1 << 2;
    private int count = 0;
    private Object[] val;

    public StackOfArray(){
        this(DEFAULT_CAPACITY);
    }

    public StackOfArray(int capacity){
        DEFAULT_CAPACITY = capacity;
        val = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object o){
        if (count >= DEFAULT_CAPACITY){
            //数组数据数量大于容器默认容量，进行扩容,容量为原来的2倍
            System.out.println("开始扩容");
            DEFAULT_CAPACITY = DEFAULT_CAPACITY * 2;
            Object[] original = val;
            val = new Object[DEFAULT_CAPACITY];
            for (int i = 0; i < original.length; i++){
                val[i] = original[i];
            }
        }
        val[count] = o;
        count++;
    }

    public Object pop(){
        if (count <= 0){
            return -1;
        }else {
            Object last = val[count];
            count--;
            return last;
        }
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++){
            sb.append(val[i].toString());
            sb.append(",");
        }
        String s = sb.toString();
        if (s.equals("")){
            System.out.println("数组已空");
        }else {
            System.out.println(s.substring(0,s.length()-1));
        }
    }

    public static void main(String[] args) {
        StackOfArray stack = new StackOfArray();
        System.out.println("数组容量："+DEFAULT_CAPACITY);
        stack.push("a");
        stack.print();
        stack.push("b");
        stack.print();
        stack.push("c");
        stack.print();
        stack.push("d");
        stack.print();
        stack.push("e");
        stack.print();

        stack.pop();
        stack.print();
        stack.pop();
        stack.print();
        stack.pop();
        stack.print();
        stack.pop();
        stack.print();
    }

}
