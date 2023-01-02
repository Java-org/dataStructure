package search;

/**
 * @desc：
 * @author：xub
 * @createDate：2022/12/31 17:45
 */
public class ArrBinaryTree {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        ArrBinaryTree01 tree = new ArrBinaryTree01(arr);
        tree.preOrder();
    }
}

class ArrBinaryTree01{
    private int[] arr;
    public ArrBinaryTree01(int[] arr){
        this.arr=arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    public void preOrder(int index){
        if(arr==null || arr.length <0){
            System.out.println("数组为空，不能按照二叉树前序遍历。");
        }
        System.out.println(arr[index]);
        if(2*index + 1 < arr.length){
            preOrder(2*index+1);
        }
        if(2*index+1 < arr.length){
            preOrder(2*index+2);
        }
    }
}
