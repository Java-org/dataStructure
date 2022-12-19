package sort;

import java.util.Arrays;

public class InsertSort03 {
    public static void main(String[] args) {
        int[] arr={101,34,-1,1,119,02};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        int index, insertValue;
        for(int i=1;i<arr.length;i++){
            index = i;
            insertValue = arr[i];
            while(index-1 >=0 && arr[index-1] > insertValue){
                arr[index] = arr[index-1]; //把数往前移
                index--;//继续判断下一个数是否往前移动
            }
            arr[index] = insertValue;//while循环移动完之后，最近一次被移动的数的位置就空出来了
        }
    }
}
