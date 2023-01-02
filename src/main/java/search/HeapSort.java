package search;

import java.util.Arrays;

/**
 * @desc：
 * @author：xub
 * @createDate：2023/1/1 9:24
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = { 4, 6, 8, 5, 9 };
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        int tmp = 0;
        /**
         * 1. int i = arr.length/2 -1 为最后一个非叶子节点的位置
         * 2. 从下往上依次调整成大堆顶（从 i--可看出）。但是上层的一个非叶子节点变动之后，其下方的非叶子节点和叶子节点也要变动
         *     ，即从上往下再调整（adjustHeap方法实现的）。即整体从下往上，但上方变动，又要从上往下调整
         */
        //将数调整成大堆顶
        for(int i = arr.length/2 -1; i>=0; i--){
            adjustHeap(arr, i , arr.length);
        }
        //
        for(int j= arr.length - 1; j>0; j--){
            tmp= arr[j];
            arr[j] = arr[0];
            arr[0] = tmp;  //交换首末元素的位置
            adjustHeap(arr, 0, j);//每次交换之后都需要从上往下调整，根节点元素变了之后，子节点也需要跟着调整
        }
    }

    /**
     * 通过循环将每一颗子树调整成大堆顶树
     * @param arr  数组
     * @param i  非叶子节点位置索引（完全二叉树中非叶子节点对应在数组中的索引），可以手动设置要调整的非叶子节点的起始位置
     * @param length 数组的长度
     */
    public static void adjustHeap(int[] arr, int i, int length){
        /**
         * k = 2*i + 1非叶子节点的位置
         * k = 2*k + 1为 k节点左子节点的位置
         */
        for(int k=2*i + 1; k<length; k=2*k + 1){
            int tmp = arr[(k-1)/2];//父节点
            if((k+1)<length && arr[k] < arr[k+1]){ //比较左右子节点
                k++;
            }
            //调整子树的节点位置
            if(arr[k] > tmp){
                arr[(k-1)/2] = arr[k];
                arr[k] = tmp;
            }
        }
    }
}
