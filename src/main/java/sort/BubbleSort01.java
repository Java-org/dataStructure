package sort;

import java.util.Arrays;

public class BubbleSort01 {
    public static void main(String[] args) {
        int[] arr = {6,3,5,7,0,-1};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr){
        boolean flag = false;
        for(int j=0;j<arr.length-1;j++) {
            for (int i = 0; i < arr.length-1-j; i++) {
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i+1] = tmp;
                }
            }
            if(!flag){ //在一趟交换中未发生任何交换
                break;
            }else{
                flag = false; //重置
            }
        }
    }
}
