package dataStructure;

import java.util.Scanner;

public class HashTable11 {
    public static void main(String[] args) {
        // 创建哈希表,链表和数组相结合
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    // 创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

//创建雇员
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

//创建链表管理雇员
class EmpLinkedList{
    private Emp head;

    //添加雇员
    public void add(Emp emp){
        if(head == null){
            head = emp;
            return;
        }
        Emp currentEmp = head;
        while(true){
            if(currentEmp.next == null){
                currentEmp.next = emp;
                return;
            }
            currentEmp = currentEmp.next;
        }
    }
    //遍历链表上的雇员
    public void list(int no){
        if(head == null){
            System.out.println("第 " + (no + 1) + " 链表为空");
            return;
        }
        System.out.print("第 " + (no + 1) + " 链表的信息为:");
        Emp currentEmp = head;
        while(true){
            System.out.printf(" => id=%d name=%s\t", currentEmp.id, currentEmp.name);
            if(currentEmp.next == null){ //说明是最后一个节点
                break;
            }
            currentEmp = currentEmp.next;
        }
        System.out.println();
    }
    //根据雇员ID查找
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp currentEmp = head;
        while (true){
            if(currentEmp.id == id){
                break;
            }
            if(currentEmp.next==null){
                break;
            }
            currentEmp=currentEmp.next;
        }
        return currentEmp;
    }
}

//创建HashTab 管理多条链表
class HashTab{
    private EmpLinkedList [] empLinkedLists;
    private int size;

    //散列函数
    public int hashFun(int id){
        return id % size;
    }

    //初始化
    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size]; //创建一个链表为元素的数组
        for(int i=0;i<size;i++){
            empLinkedLists[i] = new EmpLinkedList(); //初始化很重要
        }
    }

    //添加雇员
    public void add(Emp emp){
        int empLinkedListNo = hashFun(emp.id);
        empLinkedLists[empLinkedListNo].add(emp);
    }
    //遍历hashTab
    public void list(){
        for(int i=0; i<size; i++){
            empLinkedLists[i].list(i);
        }
    }
    //查找雇员
    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedLists[empLinkedListNo].findEmpById(id);
        if(emp == null){
            System.out.println("为找到");
        }else{
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNo + 1), id);
        }
    }

}
