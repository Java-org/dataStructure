package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch02 {
    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89, 1000, 1000, 1234 };
        List<Integer> v = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println(v);

    }
    public static List<Integer> binarySearch(int[] arr, int l, int r, int value){
        if(l>r){
            return new ArrayList<Integer>();
        }
        int m = (l+r)/2;
        int midValue = arr[m];
        if(value < midValue){
            return binarySearch(arr,l,m-1,value);
        }else if(value>midValue){
            return binarySearch(arr,m+1,r,value);
        }else{
            ArrayList<Integer> resIndexList = new ArrayList<>();
            int tmp =m-1;//继续往左遍历
            while(true){
                if(tmp<0||arr[tmp] !=value){
                    break;
                }
                resIndexList.add(tmp);
                tmp--;
            }
            resIndexList.add(m);
            tmp=m+1;//继续往右遍历
            while(true){
                if(tmp>arr.length-1||tmp!=value){
                    break;
                }
                resIndexList.add(tmp);
                tmp++;
            }
            return resIndexList;
        }
    }
}
