package com.own.test;

import com.own.linkedlist.test.SingleLinkedTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 1．单链表反转
 * 2．两个有序链表合并
 * 3．删除链表倒数第n个节点
 * 4．链表中环的检测、求链表的中间节点(快慢指针法)
 * 5．求环中快慢指针相遇点
 * 6．环入口检测
 * 7．求单链表的中间节点(快慢指针法，快指针走到末尾，慢指针的位置)
 */
public class LinkTest {

    public static SingleLinkedTest.Node inverse(SingleLinkedTest.Node node){

        return null;
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println("n="+Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println("n右移1位后或的结果="+Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println("n右移2位后或的结果="+Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println("n右移4位后或的结果="+Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println("n右移8位后或的结果="+Integer.toBinaryString(n));
        System.out.println("n右移16位后的结果="+Integer.toBinaryString(n >>> 16));
        n |= n >>> 16;
        System.out.println("n右移16位后或的结果="+Integer.toBinaryString(n));
        return n;
    }


    public static void main(String[] args) {
       // tableSizeFor(16);
       // int a = -32 >>> 4;
        List<Integer> list = new ArrayList(10);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5,8);

       System.out.println("over");
    }

}
