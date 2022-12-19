package sort;

import java.util.Arrays;

public class ShellSort04 {
    public static void main(String[] args) {
        int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr){
        for(int gap=arr.length/2;gap >0;gap/=2){
            //从第gap个元素开始，逐个对其所在组进行直接插入排序，组里相邻元素的间隔为gap
            //同组的元素不相邻，i++在遍历的时候会导致不同组轮换进行插入排序
            //arr[gap]实质为每个组的第二个元素，即每个组从第二个元素开始进行插入排序
            for(int i = gap;i<arr.length;i++){
                int index = i;
                int insertValue = arr[i];
                while(index - gap >= 0 && insertValue < arr[index-gap]){
                    arr[index] = arr[index - gap];
                    index -= gap;
                }
                arr[index] = insertValue;
            }
        }
    }
}
