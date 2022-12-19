package search;

public class InsertValueSearch03 {
    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89, 1000, 1234};
        int index = insertValueSearch(arr, 0, arr.length - 1, 10);
        System.out.println("index = " + index);
    }

    public static int insertValueSearch(int[] arr, int l, int r, int value){
        if(l > r || arr[0] > value || arr[arr.length-1] < value){
            return -1;
        }
        //求出自适应 mid值
        int m = l + (r - l) * (value - arr[l]) / (arr[r] - arr[l]);
        int midValue = arr[m];
        if(value > midValue){
            return insertValueSearch(arr, m+1, r, value);
        }else if(value < midValue){
            return insertValueSearch(arr, l, m-1, value);
        }else{
            return m;
        }
    }
}
