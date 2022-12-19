package dataStructure;

import java.util.Stack;

/**
 * @desc：
 * @author：xub
 * @createDate：2022/11/26 18:20
 */
public class SingleLinkedList03 {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();

        HeroNode hero_new = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(hero_new);
        singleLinkedList.list();

        System.out.println("反转链表...");
        singleLinkedList.reverseSingleLinkedList(singleLinkedList.getHead());
        singleLinkedList.list();
    }
}

//定义SingleLinkedList 管理英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "","");

    public HeroNode getHead() {
        return head;
    }

    //1.1新增节点方式一：不考虑编号，添加节点到单向链表
    //当不考虑编号顺序时，找到链表的最后一个节点，将最后的节点next指向新的节点
    public void add(HeroNode heroNode){
         HeroNode tmp = head;
         while(true){
             if(tmp.next == null){
                 break;
             }
             tmp = tmp.next;
         }
         tmp.next = heroNode;//加入新节点
    }

    //1.2方式二：考虑编号
    public void addByOrder(HeroNode heroNode){
        HeroNode tmp = head;
        boolean flag = false;
        //1.先找到插入点
        while (true){
            if(tmp.next==null){
                break;
            }
           if(tmp.next.no > heroNode.no){
               break;
           }else if(tmp.next.no == heroNode.no){
               flag = true; //编号已存在
               break;
            }
           tmp = tmp.next;
        }
        //2.执行插入操作
        if(flag){
            System.out.printf("准备插入的编码 %d 已存在\n",heroNode.no);
        } else {
          heroNode.next = tmp.next;
          tmp.next=heroNode;
        }
    }

    //2.修改节点
    public void update(HeroNode heroNode){
        HeroNode tmp = head;
        while (true){
            if(tmp.next == null){
                System.out.println("没有找到该节点...");
                break;
            }else if(tmp.next.no == heroNode.no){
                tmp.next.name = heroNode.name;
                tmp.next.nickname = heroNode.nickname;
                System.out.println("修改成功...");
                break;
            }
            tmp = tmp.next;
        }
    }

    //3.删除节点
    public void delete(HeroNode heroNode){
        HeroNode tmp = head;
        while(true){
            if(tmp.next == null){
                System.out.println("链表中无该节点...");
                break;
            }else if(tmp.next.no == heroNode.no){
                tmp.next = tmp.next.next;
                System.out.println("删除节点成功...");
                break;
            }
            tmp = tmp.next;
        }
    }


    //4.显示链表
    public void list(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head.next;
        while(true){
            if(tmp == null){
                break;
            }
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }

    //5.获取单链表节点个数
    // 方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
    public static int getLenth(HeroNode head) {
        int length = 0;
        HeroNode tmp = head.next;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    //6.查找单向链表中的倒数第k个结点
    public static HeroNode findLastIndexNode(HeroNode head, int index) { //index 表示是倒数第index个节点
        if (head.next == null) {
            return null;
        }
        int size = getLenth(head);
        // 先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义给辅助变量， for 循环定位到倒数的index
        HeroNode tmp = head.next;
        // 例如：size4-index2=2，cur只需移动2步就可以得到结果
        for (int i = 0; i < size - index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    //7.链表反转
    /**
     * 1.先定义一个reverseHead 节点
     * 2.从头到尾遍历原来的链表，没遍历一个节点，就将其取出，并放在新的链表的reverHead的最前端
     * 3.原来的链表head.next = reverseHead.next
     */
    public void reverseSingleLinkedList(HeroNode headNode){
        if(headNode.next == null || headNode.next.next == null){
            System.out.println("链表为空或只有一个节点不需要反转...");
            return;
        }
        HeroNode reverheadNode = new HeroNode(0, "", "");
        HeroNode current = headNode.next;
        HeroNode next = null;
        while(current != null){
            next = current.next;
            current.next = reverheadNode.next;
            reverheadNode.next = current;
            current = next;
        }
        headNode.next = reverheadNode.next;
    }

    //8.链表逆序打印
    /**
     * 利用栈的数据结构，将各个节点压入栈中，利用栈的先进后出的特点，实现逆序打印
     */
    public static void reversePrint(HeroNode headNode){
        if(headNode.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode current = headNode.next;
        while (current != null){
            stack.push(current);
            current = current.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

}


//定义HeroNode，每个HeroNode 对象就是一个节点
class HeroNode{
    //data
    public int no;
    public String name;
    public String nickname;
    //指针
    public HeroNode next;

    public HeroNode(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

