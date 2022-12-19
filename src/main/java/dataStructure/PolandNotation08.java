package dataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation08 {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //(3+4)*5-6 --> 3 4 + 5 X 6 -
        String suffixExpression = "3 4 + 5 x 6 -";
        /**
         * 1.逆波兰表达式放入arraylist中
         * 2.定一个方法完成计算
         */
        List<String> list = getListString(suffixExpression);
        System.out.println(list);

        int res = calculate(list);
        System.out.println("计算结果是："+ res);
    }

    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<String>();
        for (String item : ls) {
            //使用正则表达式取出数
            if(item.matches("\\d+")){ //匹配多位数
               stack.push(item);
            }else{
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("x")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式转换成对应的list
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<String>();
        int i = 0; // 这时是一个指针，用于遍历中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到c
        do {
            // 如果c是一个非数字，需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; // i需要后移
            } else { // 如果是一个数，需要考虑多位数
                str = ""; // 先将str 置成"" '0'[48]->'9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;// 拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;// 返回
    }

    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        Stack<String> stack = new Stack<String>();
        List<String> list = new ArrayList<String>();
        for (String ele : ls) {
            if (ele.matches("\\d+")) {
                list.add(ele);
            } else if (ele.equals("(")) {
                stack.push(ele);
            } else if (ele.equals(")")) {
                while (!stack.peek().equals("(")) {//peek查看并没有pop出去
                    list.add(stack.pop());
                }
                stack.pop();//把括号pop出去，并没有加入list
            } else {//运算符的判断
                while (stack.size() != 0 && priority(ele) <= priority(stack.peek())) {
                    list.add(stack.pop());
                }
                stack.push(ele);
            }
        }
        while (stack.size() != 0) {
            list.add(stack.pop());
        }
        return list;
    }

    public static int priority(String s) {
        if (s.equals("*") || s.equals("/")) {
            return 1;
        } else if (s.equals("+") || s.equals("-")) {
            return 0;
        } else {
            return -1;
        }
    }
}
