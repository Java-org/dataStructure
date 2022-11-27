package dataStructure;

import java.util.Scanner;

/**
 * @desc：
 * @author：xub
 * @createDate：2022/11/26 13:44
 */
public class ArrayQueue02 {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue(); //显示队列
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据为：%d\n", res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出...");
    }
}

/*
使用数组模拟队列
 */
class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    //创建构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front =-1;
        rear = -1;
    }

    public boolean isFull(){
        return rear == maxSize -1;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，不能加入数据...");
            return;
        }
        rear++;
        arr[rear]=n;
    }

    //取数据
    public int getQueue(){
        if(isEmpty()){
            //抛异常
            throw new RuntimeException("队列无数据");
        }
        front++;
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列无数据");
            return;
        }
        for(int i=0; i< arr.length; i++){
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }

    }
}
