package com.own.queue.test;

/**
 * 循环队列：基于数组实现,会有一个数据存储空间闲置
 * 与顺序队列相比，优点：循环队列入队时间复杂度始终是O(1)，不会涉及数据搬移
 *
 */
public class CircularQueueOfArrayTest<T> {

    private static int DEFAULT_CAPACITY = 1<<2;

    private int head;

    private int tail;

    private T[] queue;

    private int size;

    public CircularQueueOfArrayTest(){
        this(DEFAULT_CAPACITY);
    }

    public CircularQueueOfArrayTest(int capacity){
        queue = (T[]) new Object[capacity];
    }

    /**
     * 入队
     * 对满的条件是：(tail+1)%n==head
     * @param value
     */
    public void enqueue(T value){
        if (value == null)
            throw new IllegalArgumentException("value is not allowed null");

        int length = queue.length;
        if ((tail+1) % length == head){
            System.out.println("循环队列已满，不能再添加元素");
            return;
        }

        queue[tail] = value;
        tail = (tail+1) % length;
    }

    /**
     * 出队
     * 要点：队列为空的判断 head == tail
     * @return
     */
    public T dequeue(){
        int length = queue.length;
        if (head == tail){
            System.out.println("循环队列已为空");
            return null;
        }
        T dequeueData = queue[head];
        head = (head + 1) % length;
        return dequeueData;
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        int length = queue.length;
        for (int i = head; i%length != tail; i = (i+1) % length){
            sb.append(",");
            sb.append(queue[i]);
        }
        String a = sb.toString();
        if (!"".equals(a)){
            System.out.println(a.substring(1,a.length()));
        }else {
            System.out.println("队列已空，无数据可打印");
        }
    }

    public static void main(String[] args) {
        CircularQueueOfArrayTest<Object> queue = new CircularQueueOfArrayTest(6);
        queue.enqueue("a");
        queue.print();
        queue.enqueue("b");
        queue.print();
        queue.enqueue("c");
        queue.print();

        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.print();
    }
}
