package com.own.sort.test;

import java.util.Arrays;

public class SortTest {

    /**
     * 计数排序
     * a[2,5,3,0,2,3,0,3]
     * c1[2,0,2,3,0,1]
     * c2[2,2,4,7,7,8]
     * r[0,0,2,2,3,3,3,5]
     * @param a
     */
    public static int[] countNumSort(int[] a){
        int max = a[0];
        for (int i = 0; i < a.length; i++){
            if (a[i] > max){
                max = a[i];
            }
        }
        //将数组c中的元素初始化为0
        int[] c = new int[max +1];
        for (int i = 0; i < c.length; i++){
            c[i] = 0;
        }
        //再将a中的元素值作为c的下标，每个元素的个数统计至c中
        for (int i = 0; i < a.length; i++){
            c[a[i]]++;
        }
        System.out.println("c统计数组a后的数据为："+ Arrays.toString(c));

        //将c的元素从前至后累加，结果就是每个元素的排序位置
        for (int i = 1; i < c.length; i++){
            c[i] = c[i-1] + c[i];
        }
        System.out.println("c中最终数据为："+ Arrays.toString(c));

        int[] r = new int[a.length];
        //此处遍历数组a需从尾至头遍历，才能保证算法是稳定排序的，否则排序不稳定
        for (int i = a.length -1; i >=0; i--){
            int aVal = a[i];
            // c[aVal]--是为了计算下次排序的位置，如c[2] = 4，即元素2排第四位，下次元素2就排第三位
            int cVal = c[aVal]--;
            r[cVal -1] = aVal;
        }

        System.out.println("最终的有序数组为："+Arrays.toString(r));
        return r;
    }


    /**
     * 快速排序：核心思想：分治、分区
     * 1、实现：1）从数组中任取一数据a(通常取最后一个)；
     *          2）将小于a的数据放于a的左侧，大于a的放于a的右侧；
     *          3）在a左侧及右侧的数据组成的数组，重复步骤1)、2)直至左右数组只有一个数据为止，则排序结束，所有数据已排好序。
     * 2、时间复杂度O(nlogn)，空间复杂度O(1)
     * 3、不是稳定排序
     *
     * @param a
     * @return
     */
    public int[] quickSort(int[] a, int n) {
        quickSortSplit(a, 0, n-1);
        return a;
    }

    private void quickSortSplit(int[] a, int start, int end) {
        if (start >= end)
            return;

        //获取分区点
        int q = partition(a, start, end);
        quickSortSplit(a, start, q - 1);
        quickSortSplit(a, q + 1, end);
    }

    private int partition(int[] a, int start, int end) {
        int point = a[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (a[j] < point) {
                if (i == j){
                    i++;
                }else {
                    int s = a[i];
                    a[i++] = a[j];
                    a[j] = s;
                }
            }
        }
        a[end] = a[i];
        a[i] = point;
        return i;
    }


    /**
     * 归并排序：核心思想：分治思想
     * 1、实现：将数据从中间分成前后两部分，一直分，直到每部分都只有一个数据为止(递归)，再将每组数据排序并合并。
     * 2、时间复杂度稳定O(nlogn),空间复杂度O(n)
     * 3、是稳定排序法,取决于merge()方法中的标记(1)，如果前后部分有值相同，先把前部分的元素放入tmp中
     *
     * @param a 待排序的数组
     * @param n 数组长度
     * @return
     */
    public int[] mergeSort(int[] a, int n) {
        mergeSortSplit(a, 0, n - 1);
        return a;
    }

    /***
     * 将数组a从中间分成前后两部分，直到每部分只有一个元素为止
     * @param a
     * @param i 分解数组的起始元素
     * @param n  分解数组的末尾元素
     */
    private void mergeSortSplit(int[] a, int i, int n) {
        if (i == n)
            return;
        int mid = (i + n) / 2;
        mergeSortSplit(a, i, mid);
        mergeSortSplit(a, mid + 1, n);
        merge(a, i, mid, n);
    }

    /**
     * 排序后进行合并，使用两个指针分别指向两数组的起始位置
     *
     * @param a
     * @param i   需要合并的A数组的起始下标
     * @param mid 需要合并的A数组的结束下标，mid+1也是数组B的起始下标
     * @param n   需要合并的B数组的结束下标
     */
    private void merge(int[] a, int i, int mid, int n) {
        int[] tmp = new int[n - i + 1];
        int p = i, q = mid + 1;
        int m = 0;
        //比较大小，排序结果保存在tmp中
        while (p <= mid && q <= n) {
            if (a[p] > a[q])
                tmp[m++] = a[q++];
            else
                tmp[m++] = a[p++];
        }

        //判断哪个数组中有未转移的数据，将其转移到临时数组中
        int start = p;
        int end = mid;
        if (q <= n) {
            start = q;
            end = n;
        }
        while (start <= end) {
            tmp[m++] = a[start++];
        }

        //将临时数组中的数据copy到数组a中
        for (int t = 0; t < m; t++) {
            a[i + t] = tmp[t];
        }
        System.out.println(a);

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

    /**
     * 选择排序：
     * 1、与插入排序类似，分已排序、未排序两组，
     * 从未排序的元素中找最小的元素，放在已排序序列的末尾
     * 2、最好时间复杂度O(n*n)，最坏时间复杂度O(2n*n+4n)，空间复杂度O(1)
     * 3、不是稳定排序算法，如4,3,5,4,6,1 第一次排序后第一个4与1交换了位置，稳定性发生了破坏
     *
     * @param a
     */

    public int[] selectSort(int[] a) {
        for (int i = 0; i < a.length; i++) { //O(n)
            int original = a[i];
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) { //O(n)
                if (a[j] < a[minIndex]) {    //O(1)
                    minIndex = j;
                }
            }
            a[i] = a[minIndex];
            a[minIndex] = original;
        }

        return a;
    }

    /**
     * 冒泡排序：升序排序，核心操作：比较、交换
     * 1、每次比较相邻的两个数据。第一个元素a与第二个b相比，若a>b,交换a、b的位置，否则继续比较第二个与第三个，第一轮比较完之后，最大的元素已经在末尾了。
     * 2、最好时间复杂度O(n)，数组a为已升序排序好的，最外层for循环1次，最内层for循环n次；最差时间复杂度为O(5n*n)
     * 空间复杂度O(1)
     * 3、优化：若内层for循环后a[j]<=a[j+1]，说明0---i-1间的数据都是已排序好的，可不再进行比较，直接跳出
     * 4、是稳定排序
     * 如：按订单时间排序后，再按订单金额排序，金额相同的订单的先后顺序没有改变
     *
     * @param a
     */
    public int[] bubbleSort(int[] a) {
        for (int i = a.length; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return a;
    }

    /**
     * 插入排序法：核心操作：比较、移动
     * 将数据分成已排序和未排序两部分，默认第一个元素是有序的，
     * 指针从第二个元素开始往后移，指针指向的元素与前面已排序的每个元素比较，
     * 找到插入点，将插入点前的数据后移一位，腾出位置放置未排序的第一个元素。
     * 最好时间复杂度为O(3n)，最差为O(3n*n+3n);
     * 最好、最差空间复杂度为O(n)
     *
     * @param a
     * @return
     */
    public int[] insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int t = a[i];
            int emptyIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] > t) {
                    a[j + 1] = a[j];
                    emptyIndex = j;
                } else {
                    break;
                }
            }
            a[emptyIndex] = t;
        /*
          int leftIndex = i-1;
          while (leftIndex >= 0 && a[leftIndex] > t ){
              a[leftIndex + 1] = a[leftIndex];
              leftIndex--;
          }
          a[leftIndex+1] = t;*/
        }
        return a;
    }

    private void print(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(",");
            sb.append(a[i]);
        }
        String s = sb.toString();
        System.out.println(s.substring(1, s.length()));
    }

    public static void main(String[] args) {
        SortTest test = new SortTest();
      /*  int[] arr = new int[500];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int) (Math.random()*1000);
        }
       System.out.println("开始选择排序"+System.currentTimeMillis());
        int[] b = test.selectSort(arr);
        System.out.println(System.currentTimeMillis());

        System.out.println("开始冒泡排序"+System.currentTimeMillis());
        int[] m = test.bubbleSort(arr);
        System.out.println(System.currentTimeMillis());

        System.out.println("开始插入排序"+System.currentTimeMillis());
        int[] c = test.insertSort(arr);
        System.out.println(System.currentTimeMillis());*/

        int[] a = {2, 4, 6, 1, 9, 7, 5, 0};
        a = test.quickSort(a, a.length);
        test.print(a);
    }
}
