package com.own.queue.test;

/**
 * 顺序队列：基于数组实现
 */
public class QueueOfArrayTest<T> {

    private T[] array;

    private int head;

    private int tail;

    private static final int DEFAULT_CAPACITY = 1 << 2;

    private int count;

    public QueueOfArrayTest(){
        this(DEFAULT_CAPACITY);
    }

    public QueueOfArrayTest(int capacity){
        array = (T[]) new Object[capacity];
    }

    /**
     * 入队:不允许空值,当队列满时，数组整体搬移
     * tail指针后移
     * @param value
     */
    public void enqueue(T value){
        if (value == null){
            throw new IllegalArgumentException("value is not allowed null");
        }
        if (tail == DEFAULT_CAPACITY){
            if (head == 0){
                System.out.println("队列已满，不能再添加数据了");
                return;
            }else {
                //搬移head-tail间的数据至0-head
                for (int i = head; i < tail; i++){
                    array[i-head] = array[i];
                }
                tail = tail-head;
                head = 0;
            }
        }
            array[tail++] = value;
            count++;
    }

    /**
     * 出队
     * head指针后移一位
     * @return 出队元素
     */
    public T dequeue(){
        if (head == tail){
            System.out.println("队列已空");
            return null;
        }

        T dequeueData = array[head];
        head++;
        count--;
        return dequeueData;
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        for (int i = head; i < tail; i++){
            sb.append(array[i]);
            sb.append(",");
        }
        String s = sb.toString();
        System.out.println(s.substring(0,sb.length()-1));
    }

    public static void main(String[] args) {
        QueueOfArrayTest<Object> array = new QueueOfArrayTest(5);
        array.enqueue("a");
        array.enqueue("b");
        array.enqueue("c");
        array.enqueue("d");
        array.enqueue("e");
        array.print();
        array.enqueue("f");

        array.dequeue();
        array.print();
        array.dequeue();
        array.print();

        array.enqueue("1");
        array.print();
        array.enqueue("2");
        array.print();

    }
}
