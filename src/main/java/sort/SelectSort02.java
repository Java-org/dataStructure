package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort02 {
    public static void main(String[] args) {
        int [] arr = {101,34,119,1};
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));

        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        System.out.println("排序后");
        selectSort(arr);
        Date date1 = new Date();
        System.out.println(simpleDateFormat.format(date1));
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
             int minIndex = i;
             int min = arr[i];
             for(int j=i+1;j<arr.length;j++){
                 if(arr[j] < min){
                     minIndex = j;
                     min = arr[j];
                 }
             }
             if(minIndex != i){
                 int tmp = arr[i];
                 arr[i] = arr[minIndex];
                 arr[minIndex] = tmp;
             }
        }
    }
}
