package search;

import java.util.Arrays;

/**
 * @desc：
 * @author：xub
 * @createDate：2022/12/19 21:40
 */
public class FibonacciSearch04 {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = { 1, 8, 10, 89, 1000, 1234 };
        System.out.println("查找元素的索引值index=" + fibSearch(arr, 90));
    }

    /**
     * 获取一个斐波那契数列
     * @return
     */
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for(int i=2; i<maxSize;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }

    /**
     *
     * @param arr
     * @param value，需要查找的值
     * @return 返回对应的下标
     */
    public static int fibSearch(int[] arr, int value){
        int low = 0;
        int high = arr.length -1;
        int k=0; //f[k]中的k值
        int mid = 0;
        int f[] = fib();
        //1.找到合适的 k 值
        while(arr.length > f[k]-1){
            k++;
        }
        //2.填充数组，长度对齐 f[k]-1
        int[] tmp = Arrays.copyOf(arr, f[k] - 1); //arr的长度填充到f[k]-1，默认用0填充
        for(int i=high+1 ; i < tmp.length;i++){
            tmp[i] = arr[high]; //将0填充的部分变为用0填充
        }
        //3.开始查找
        //此处未使用递归（相比于普通的二分查找，插值查找），使用while循环
        while (low <=high){
            mid = low + f[k-1]-1;
            if(value < tmp[mid]){
                //调整区间，重新进行黄金分割，f[k] = f[k-1] + f[k-2], [low,mid]的长度为f[k-1], [mid, high]的长度为f[k-2]
                //一个线段有两个黄金分割点，所以f[k-1]，f[k-2]孰大孰小无所谓
                high = mid - 1;
                k-=1;//对长度为f[k-1]的这一段重新进行黄金分割，即 f[k-1] = f[k-2] + f[k-3]
            }else if(value > tmp[mid]){
                low = mid + 1;
                k-=2;
            }else{
                //下述的判断主要是数组被扩充过，需要返回原数组对应的下标索引
                if(mid <=high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1; //while循环结束都没有找到
    }

}
