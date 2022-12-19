package sort;

import java.util.Arrays;

public class RadixSort07 {
    public static void main(String[] args) {
        int arr[] = { 53, 3, 542, 748, 14, 214 };
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void radixSort(int[] arr){
        int[][] bucket = new int[10][arr.length];
        int[] bucketCountElement = new int[10];
        //1.计算最大值的位数，判断需要遍历几轮
        int max=arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        int maxlength = (max+"").length();
        //2.开始遍历数组放入桶中
        for(int k=0,n=1;k<maxlength;k++,n*=10){
            for(int i =0;i<arr.length;i++){
                int digitOfElement = arr[i]/n%10;
                bucket[digitOfElement][bucketCountElement[digitOfElement]]=arr[i];
                bucketCountElement[digitOfElement]++;
            }
            int index =0;
            for(int i=0;i<bucketCountElement.length;i++){
                if(bucketCountElement[i] !=0){
                    for(int j=0;j<bucketCountElement[i];j++){
                        arr[index++]=bucket[i][j];
                    }
                }
                bucketCountElement[i]=0; //重置
            }
        }

    }
}
