package com.own.queue.test;

import com.own.linkedlist.test.SingleLinkedTest;

/**
 * 链式队列：以链表实现，用头尾双指针
 * 注意事项：出队时，若对头元素为空，需将队尾元素置空
 *
 */
public class QueueOfLinkedListTest {

    private SingleLinkedTest.Node head;

    private SingleLinkedTest.Node tail;

    /**
     * 入队
     * 在队尾添加元素
     * 要点：1、第一个元素入队时，头指针为空，此时需将头尾指针都指向该元素
     *      2、实时更新队尾指针的指向
     * @param value
     */
    public void enqueue(Object value){
        if (value == null){
           throw new IllegalArgumentException("value is not allowed null");
        }
        SingleLinkedTest.Node newNode = new SingleLinkedTest.Node(value);
        if (head == null){
            head = newNode;
            tail = newNode;
        }else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * 出队
     * 从对列头出队
     * 要点：1、当队列头为空时，说明该队列已为空，要将队尾指针置空
     *      2、实时更新头指针的指向
     * @return
     */
    public String dequeue(){
        SingleLinkedTest.Node dequeueNode = head;
        if (head == null){
            System.out.println("队列已为空！");
            tail = null;
            return null;
        }
        head = head.next;
        return dequeueNode.data.toString();
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        SingleLinkedTest.Node p = head;
        while (p != null){
            sb.append(",");
            sb.append(p.data.toString());
            p = p.next;
        }
        String s = sb.toString();
        if (s != null && !"".equals(s)){
            System.out.println(s.substring(1,s.length()));
        }else {
            System.out.println("队列已为空");
        }
    }

    public static void main(String[] args) {
        QueueOfLinkedListTest queue = new QueueOfLinkedListTest();
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

    }

}
