package dataStructure;

public class DoubleLinkedList04 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 进行测试
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        // 创建管理链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        // 最后加入
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);

        // 测试修改节点的代码
//		HeroNode2 newHeroNode = new HeroNode2(1, "小卢", "玉麒麟~~");
//		doubleLinkedList.update(newHeroNode);

        // 测试删除节点的代码
        doubleLinkedList.del(2);
//		doubleLinkedList.del(4);

        doubleLinkedList.list();
    }

}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void update(HeroNode2 newHeroNode) {
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("修改失败，没有找到该节点！");
                break;
            } else if (temp.no == newHeroNode.no) {
                temp.name = newHeroNode.name;
                temp.nickname = newHeroNode.nickname;
                System.out.println("修改成功：" + temp);
                break;
            }
            temp = temp.next;
        }
    }

    public void del(int no) {
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("找不到该节点，无法删除！");
                break;
            } else if (temp.no == no) {
                // 判断temp是否为最后一个节点
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                temp.pre.next = temp.next;
                System.out.printf("编号为 %d 的英雄删除成功!\n", no);
                break;
            }
            temp = temp.next;
        }
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            // 输出节点的信息
            System.out.println(temp);
            if (temp.next == null) {
                break;
            }
            // 将temp后移
            temp = temp.next;
        }
    }
}

class HeroNode2 {
    // data域
    public int no;
    public String name;
    public String nickname;
    // next域
    public HeroNode2 next;
    // pre域
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2 [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
