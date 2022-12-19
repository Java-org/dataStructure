package sort;

import java.util.Arrays;

public class MergeSort06 {
    public static void main(String[] args) {
        int[] arr = { 8, 4, 5, 7, 1, 3, 6, 2 };
        mergeSort(arr, 0, arr.length-1);
        System.out.println("归并排序后=" + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int l, int r){
        if(l < r){ // l<r 递归的终止条件
            int m = (l+r)/2;
            mergeSort(arr, l, m);
            mergeSort(arr,m+1,r);
            merge(arr,l,r,m);
        }
    }

    public static void merge(int[] arr, int l, int r, int m){ // [l,r]是arr的任意区间
        int i = l; // 初始化i, 左边有序序列的初始索引
        int j = m + 1; // 初始化j, 右边有序序列的初始索引
        int[] tmp = new int[r-l+1];
        int t= 0; //指向temp数组的当前索引

        // (一)
        // 先把左右两边(有序)的数据按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完毕为止
        while(i<=m && j <=r){
            if(arr[i]<=arr[j]){
                tmp[t++]=arr[i++];
            }else{
                tmp[t++]=arr[j++];
            }
        }

        // (二)
        // 把有剩余数据的一边的数据依次全部填充到temp
        while (i<=m){
            tmp[t++]=arr[i++];
        }
        while (j<=r){
            tmp[t++]=arr[j++];
        }

        // (三)
        // 将temp数组的元素拷贝到arr
        System.out.println("left: " + l + "  right:" + r + "  t:" + t);
        for(int k=0;k<t;k++){
            arr[l++]=tmp[k];
        }
    }
}
