package com.own;

import com.own.util.PrintUtil;

public class Main {

    /**
     * 冒泡排序
     * @param a
     */
    public static void bubbleSort(int[] a){
        int n = a.length;
        for (int i = 0; i < n; i++){
            boolean flag = false;
            for (int j = 0; j < n-i-1; j++){
                if (a[j] > a[j+1]){
                    int t = a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
    }

    /**
     * 希尔排序
     * @param a
     * @return
     */
    public static int[] shellSort(int[] a){
        int step = a.length/2;
        while (step >= 1){
            for (int i = step; i < a.length; i++){
                int t = a[i];
                int j = i - step;
                for (; j >= 0; j = j - step){
                    if (a[j] > t){
                        a[j+step] = a[j];
                    }else {
                        break;
                    }
                }
                a[j + step] = t;
            }
            step = step/2;
        }
        return a;
    }

    /**
     * 插入排序
     * @param a
     */
    public static void insertSort(int[] a){
        for (int i = 1; i < a.length; i++){
            int t = a[i];
            int j = i -1;
            for (; j >= 0; j--){
                if (a[j] > t){
                    a[j+1] = a[j];
                }else {
                    //break后 j不再自减
                    break;
                }
            }
            a[j+1] = t;
        }
    }

    /**
     * 选择排序
     * @param a
     */
    public static void selectSort(int[] a){
        for (int i = 0; i < a.length; i++){
            int minIndex = i;
            for (int j = i+1; j < a.length; j++){
                if (a[j] < a[minIndex]){
                    minIndex = j;
                }
            }
            int t = a[i];
            a[i] = a[minIndex];
            a[minIndex] = t;
        }
    }


    /**
     *
     * 归并排序
     * @param a
     * @param n
     */
    public static void mergeSort(int[] a, int n){
        mergeSortSplit(a, 0, n-1);
    }

    private static void mergeSortSplit(int[] a, int start, int end) {
        if (start >= end)
            return;
        int mid = (start + end)/2;
        mergeSortSplit(a, start, mid);
        mergeSortSplit(a, mid+1, end);

      //  mergeBySentry(a, start, mid, end);
        mergeArrays(a,start,mid,end);
    }

    private static void merge(int[] a, int start, int mid, int end) {
        int i = start, j = mid +1, t = 0;
        int[] tmp = new int[end - start +1];

        while (i <= mid && j <= end){
            if (a[i] > a[j])
                tmp[t++] = a[j++];
            else
                tmp[t++] = a[i++];
        }

        int m = i;
        int n = mid;
        if (j <= end){
            m = j;
            n = end;
        }
        while (m <= n){
            tmp[t++] = a[m++];
        }

        for (int p = 0; p < t; p++){
            a[start + p] = tmp[p];
        }
    }

    public static void mergeBySentry(int[] a, int start, int mid, int end){
        //左右数组都要增加一个长度 存最大值
        int[] leftArr = new int[mid - start +2];    //mid- start +1 +1
        int[] rightArr = new int[end - mid +1];     //end - (mid+1) +1 +1

        //初始化左右数组
        for (int i = 0; i < leftArr.length -1; i++){
            leftArr[i] = a[start + i];
        }
        leftArr[mid - start +1] = Integer.MAX_VALUE;

        for (int j = 0; j < rightArr.length -1; j++){
            rightArr[j] = a[mid +1 +j];
        }
        rightArr[end - mid] = Integer.MAX_VALUE;

        int t = start;
        int i = 0, j = 0;
        //限制放入数组a的数量，防止把哨兵放进去
        while (t <= end){
            if (leftArr[i] <= rightArr[j])
                a[t++] = leftArr[i++];
            else
                a[t++] = rightArr[j++];
        }
    }

    public static void mergeArrays(int[] a, int low, int mid, int high){
        // Merge sorted halves (now in src) into dest
        for(int i = low, q = mid+1, p = low; i < high; i++) {
            if (q >= high || p < mid && ((Comparable)a[p]).compareTo(a[q])<=0)
                a[i] = a[p++];
            else
                a[i] = a[q++];
        }
    }

    /**
     * 快速排序
     * @param a
     * @param length
     */
    public static void quickSort(int[] a, int length){
        quickSortSplit(a, 0, length-1);
    }

    private static void quickSortSplit(int[] a, int start, int end) {
        if (start >= end)
            return;
        int q = partition(a,start,end);
        quickSortSplit(a, start, q-1);
        quickSortSplit(a,q+1,end);
    }

    private static int partition(int[] a, int start, int end) {
        int i = start;

        for (int j = i; j < end; j++){
            if (a[j] < a[end]){
                if (i == j)
                    i++;
                else {
                    int t = a[i];
                    a[i++] = a[j];
                    a[j] = t;
                }
            }
        }
        int e = a[end];
        a[end] = a[i];
        a[i] = e;
        return i;
    }

    public static void radixSort(int[] a){
        int max = a[0];
        for (int j = 1; j < a.length; j++){
            if (a[j] > max){
                max = a[j];
            }
        }

        for (int i = 1; max/i > 0; i*=10){
            countSort(a, i);
        }
    }

    private static void countSort(int[] a, int i) {
        int[] c = new int[10];
        for (int j = 0; j < a.length; j++){
            c[a[j]/i % 10]++;
        }
        for (int t = 1; t < c.length; t++){
            c[t] = c[t] + c[t-1];
        }

        int[] r = new int[a.length];
        for (int p = a.length-1; p >= 0; p--){
            int sortTh = c[a[p]/i % 10];
            r[sortTh-1] = a[p];
            c[a[p]/i % 10] --;
        }

        for (int m = 0; m < a.length; m++){
            a[m] = r[m];
        }

    }

    public static void getPerNum(int a){
        StringBuilder sb = new StringBuilder();
        for (int i = 100000; i > 0; i/=10){
            int p = (a/i) % 10;
            sb.append(p);
            sb.append(",");
        }
        System.out.println("分割开的数据为："+sb.toString());
    }

    public static void getNum(int a){
        int i = 1;
        for (int j = 10; a/j > 0; j*=10){
            i++;
        }
        System.out.println("a的位数是："+i);
    }


    public static void main(String[] args) {

        int[] a ={1371227,1851698,1301156,1503443};
        radixSort(a);
        PrintUtil.print(a);

    }
}
