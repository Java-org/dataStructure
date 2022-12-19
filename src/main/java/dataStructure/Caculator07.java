package dataStructure;


/**
 * 该方法无法运算二位数，且符号顺序有问题
 */
public class Caculator07 {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        //创建两个栈，数栈好符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index =0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        while(true){
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    if(operStack.priority(ch) <= operStack.peek()){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    operStack.push(ch);
                }
            }else{
                numStack.push(ch - 48); //可能是多位数
            }
            index ++;
            if(index >= expression.length()){
                break;
            }
        }
        while (true) {
            //如果符号栈为空，则计算结束，数栈中只有一个数字，即最终结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式 %s = %d",expression, numStack.pop());
    }
}


class ArrayStack2{
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈
    private int top = -1; //栈顶

    public ArrayStack2(int maxSize){
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

    //返回栈顶的值，但不出栈
    public int peek(){
        return stack[top];
    }

    //返回运算符的优先级，优先级用数字标识，数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1; //假定只有加减乘除
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper){
        int res = 0; //用于存放结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}