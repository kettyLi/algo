package com.own.heap;

import java.util.Arrays;

/**
 * 堆是一种完全二叉树，可用数组实现
 *      主要操作：添加元素、删除堆顶元素、建堆、堆排序(按大顶堆来实现)
 *      注意事项：堆的起始位置为0还是1，对应求父节点的表达式是不一样的
 *
 */
public class HeapTest {

    private int[] data;
    private int count;
    private int capacity;

    public HeapTest(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
    }

    /**
     * 添加元素
     * @param a
     */
    public void add(int a) {
        data[++count] = a;
        int i = count;


        while (i/2 > 0) {
            if (data[i] > data[i/2]){
                swap(i, i/2);
            }
            i = i/2;
        }
    }

    public void swap(int i, int p){
        int t = data[i];
        data[i] = data[p];
        data[p] = t;
    }

    public static void main(String[] args) {
        HeapTest heap = new HeapTest(10);
        heap.add(1);
        System.out.println(Arrays.toString(heap.data));

        heap.add(2);
        System.out.println(Arrays.toString(heap.data));

        heap.add(3);
        System.out.println(Arrays.toString(heap.data));

        heap.add(4);
        System.out.println(Arrays.toString(heap.data));

        heap.add(5);
        System.out.println(Arrays.toString(heap.data));

    }

}
