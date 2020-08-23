package com.own.stack.test;

import com.own.linkedlist.test.SingleLinkedTest;

/**
 * 栈：先进后出，后进先出；
 *     操作受限，只提供有限的操作-入栈、出栈
 * 链式栈：用链表实现的栈；
 *        若将最新数据置于链尾，入栈、出栈时间复杂度都是O(n),
 *        若将最新数据置于链头，入栈、出栈时间时间复杂度则为O(1)
 */
public class StackOfLinkedList {

    private SingleLinkedTest.Node top;

    /**
     * 往栈中压入数据
     */
    public void push(Object val){
        SingleLinkedTest.Node newNode = new SingleLinkedTest.Node(val);
        if (top != null) {
            newNode.next = top;
        }
        top = newNode;
    }

    /**
     * 弹出栈顶数据
     * @return
     */
    public Object pop(){
        if (top == null){
            //若栈中无数据，返回-1
            return -1;
        }else {
            SingleLinkedTest.Node originalTop = top;
            top = top.next;
            return originalTop;
        }
    }

    public Object getTop(){
        return top == null ? "":top.data;
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        SingleLinkedTest.Node p = top;
        while (p != null){
            sb.append(p.data.toString());
            sb.append(",");
            p = p.next;
        }
        //若StringBuilder中不append任何值，StringBuilder.toString()方法会new一个string对象，再与空字符串比较结果是flse
        String a = sb.toString();
        if (a != null && !a.equals("")){
            System.out.println(a.substring(0,a.length()-1));
        }else {
            System.out.println("栈已为空");
        }
    }

    public static void main(String[] args) {
        StackOfLinkedList stack = new StackOfLinkedList();
        stack.push("a");
        stack.print();
        stack.push("b");
        stack.print();
        stack.push("c");
        stack.print();

        stack.pop();
        stack.print();
        stack.pop();
        stack.print();
        stack.pop();
        stack.print();

    }
    
}
