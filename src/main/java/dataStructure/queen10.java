package dataStructure;

/**
 * 第1个皇后发生变化之后，后续的其它的皇后的位置都要同时发生变化
 * 可以假设8个皇后已在棋盘上，第一个皇后挪动位置，后续挨个挪动，产生8^8个支线，每一个支线执行完，打印一次，count++
 */
public class queen10 {
    //定义一个max表示共有8个皇后
    int max = 8;
    int[] array = new int[max];
    static int count;
    static int judgecount;

    public static void main(String[] args) {
        queen10 queen10 = new queen10();
        queen10.check(0);
        System.out.println("解法共有"+ count + "种");
    }

    private void check(int n){
        if(n == max){ // 表示一条支线已经结束，递归终止条件
            print();
            return;
        }
        //依次放入皇后判断是否冲突
        for(int i=0; i<max; i++){
            //将第n个皇后放到第i列
            array[n]=i;
            if(judge(n)){
                check(n+1);
            }
            //如果判断冲突，就下一轮循环，将第n个皇后往后挪一个位置，继续判断
        }

    }

    private void print() {
        count++;

    }

    /**
     *
     * @param n 表示第 n 个皇后
     * @return
     */
    private boolean judge(int n){
        for(int i = 0;i <n; i++){
            if(array[i]==array[n] ||Math.abs(n -i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }
}
