package dataStructure;

import java.util.Scanner;

public class ArrayStack06 {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key="";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop){
            key = scanner.next();
            if ("show".equals(key)) {
                stack.list();
            } else if ("push".equals(key)) {
                System.out.println("请输入一个数");
                int value = scanner.nextInt();
                stack.push(value);
            } else if ("pop".equals(key)) {
                try {
                    int res = stack.pop();
                    System.out.printf("出栈的数据是 %d", res);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if ("exit".equals(key)) {
                scanner.close();
                loop = false;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayStack{
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈
    private int top = -1; //栈顶

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况
    public void list(){
        if(isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for(int i= top;i >=0;i--){
            System.out.printf("stack[%d] = %d",i,stack[i]);
        }
    }

}
