package sort;

import java.util.Arrays;

public class QuickSort05 {
    public static void main(String[] args) {
        int[] arr = { -9, 78, 0, -9, 23, -567, -9, 70, 45 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int l, int r){
        if(l<r){
            int i= l;
            int j= r;
            int tmp = arr[i];
            while(i<j){ //一轮循环表示左右各一次替换
                while(i<j && arr[j]>tmp){//遍历直到右侧替换一次，此处 i<j是有必要的
                    j--;
                }
                arr[i] = arr[j];
                while(i<j && arr[i]<=tmp){//遍历左侧直到替换一次，左侧和右侧必须有一侧需要判断=的情况，否则会卡住无法往前遍历
                    i++;
                }
                arr[j]=arr[i];
            }
            arr[i]=tmp;
            quickSort(arr,l,i-1);
            quickSort(arr,i+1,r);
        }
    }
}
