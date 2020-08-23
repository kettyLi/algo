import java.util.*;

public class SortTest {

    /**
     * 冒泡排序
     *      每次冒泡后最大的元素都落到了数组末尾
     * 时间复杂度O(n*n)
     * @param a
     */
    public static void bubbleSort(int[] a){
        for (int i = a.length; i >= 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i -1; j++){  //i = length; i--; j < length
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
     * 插入排序
     *      默认第一个元素有序，从第二个元素开始与前面已排序好的元素从后至前比较，将已排序好的元素后移，找到插入位置，插入即可。
     * 时间复杂度O(n*n)
     * @param a
     */
    public static void insertSort(int[] a){
        for (int i = 1; i < a.length; i++){
            int t = a[i];
            int j = i-1;
            for (; j >= 0; j--){
                if (a[j] > t){
                    a[j+1] = a[j];
                }else {
                    break;
                }
            }
            a[j+1] = t;
        }
    }

    /**
     * 希尔排序
     * 增量为n/2,最坏时间复杂度为O(n*n)
     * 增量为2^k-1，最坏时间复杂度为O(n^1.5)
     */
    public static void shellSort(int[] a){
        int length = a.length;
        int step = length/2;
        while (step > 0){
            for (int i = step; i < a.length; i += step){
                int t = a[i];
                int j = i - step;
                for (; j >= 0; j -= step){
                    if (a[j] > t)
                        a[j + step] = a[j];
                    else
                        break;
                }
                a[j + step] = t;
            }
            step = step/2;
        }
    }

    /**
     * 选择排序
     *      每次选择最小的元素放在数组前面
     * 时间复杂度为O(n*n)
     * @param a
     */
    public static void selectSort(int[] a){
        for (int i = 0; i < a.length; i++){
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++){
                if (a[minIndex] > a[j]){
                    minIndex = j;
                }
            }
            int t = a[i];
            a[i] = a[minIndex];
            a[minIndex] = t;
        }
    }

    /**
     * 归并排序
     *      利用分治思想，先分再合，合并的过程中排序
     * @param a
     */
    public static void mergeSort(int[] a){
        int end = a.length -1;
        mergeSortSplit(a, 0, end);
    }

    private static void mergeSortSplit(int[] a, int start, int end) {
        if (start == end)
            return;

        int mid = start + ((end - start) >> 1);
        mergeSortSplit(a, start, mid);
        mergeSortSplit(a, mid+1, end);

        merge(a, start, mid, end);
    }

    private static void merge(int[] a, int start, int mid, int end) {
        int[] tmp = new int[end -start +1];
        int i = 0, p = start, q = mid+1;

        while (p <= mid && q <= end){
            if (a[p] > a[q])
                tmp[i++] = a[q++];
            else
                tmp[i++] = a[p++];
        }

        int s = p;
        int e = mid;
        if (q <= end){
            s = q;
            e = end;
        }

        while (s <= e){
            tmp[i++] = a[s++];
        }

        for (int j = 0; j < i; j++){
            a[start + j] = tmp[j];
        }
    }

    /**
     * 快速排序
     *      利用分区，每次取最后一个元素进行分区
     * @param a
     */
    public static void quickSort(int[] a){
        quickSortSplit(a, 0, a.length -1);
    }

    private static void quickSortSplit(int[] a, int start, int end) {
        if (start >= end)
            return;
        int p = partition(a, start, end);
        quickSortSplit(a, start, p-1);
        quickSortSplit(a, p+1, end);
    }

    /**
     * 分区的核心思想：
     *      用指针i将数据分成已处理区(比end小的元素)和未处理区(比end大的元素)，
     *      若数a比end小，将a添加到已处理区的末尾(与选择排序相似)
     * @param a
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] a, int start, int end) {
        int i = start;
        for (int j = start; j < end; j++){
            if (a[j] < a[end]){
                int t = a[i];
                a[i++] = a[j];
                a[j] = t;
            }
        }
        int t = a[i];
        a[i] = a[end];
        a[end] = t;

        return i;
    }

    public static void main(String[] args) {
        int[] a = {5,3,7,2,8,4,9,1,6};
      //  quickSort(a);
        System.out.println(Arrays.toString(a));

        List<String> all = new ArrayList<>();
        String[] allStr = {"a","c","d","s","z"};
        all.addAll(Arrays.asList(allStr));

        List<String> part = new ArrayList<>();
        String[] partStr = {"a","b","c","d"};
        part.addAll(Arrays.asList(partStr));

        Collections.sort(all);

        System.out.println(all.toString());
        new HashMap<>();

    }
}
