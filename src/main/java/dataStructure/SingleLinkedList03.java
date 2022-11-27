package dataStructure;

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
    }
}

//定义SingleLinkedList 管理英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "","");

    //方式一：不考虑编号，添加节点到单向链表
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

    //方式2：考虑编号
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

    //显示链表
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

