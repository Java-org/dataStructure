package search;

public class BinarySearch01 {
    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89, 1000, 1000, 1234 };
        int index = binarySearch(arr, 0, arr.length - 1, 10);
        if (index == -1) {
            System.out.println("没有找到。");
        } else {
            System.out.println("找到，下标为=" + index);
        }
    }
    public static int binarySearch(int[] arr, int l, int r, int value){
        if(l>r){
            return -1;
        }
        int m = (l+r)/2;
        int midValue = arr[m];
        if(value < midValue){
            return binarySearch(arr,l,m-1,value);
        }else if(value>midValue){
            return binarySearch(arr,m+1,r,value);
        }else{
            return midValue;
        }
    }
}
