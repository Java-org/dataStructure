package search;

/**
 * @desc：
 * @author：xub
 * @createDate：2022/12/31 21:04
 */
public class ThreadBinaryTree {
    public static void main(String[] args) {
        HeroNode04 root = new HeroNode04(1, "tom");
        HeroNode04 node2 = new HeroNode04(3, "jack");
        HeroNode04 node3 = new HeroNode04(6, "smith");
        HeroNode04 node4 = new HeroNode04(8, "mary");
        HeroNode04 node5 = new HeroNode04(10, "king");
        HeroNode04 node6 = new HeroNode04(14, "dim");

        // 二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 测试中序线索化
        ThreadBinaryTree04 threadedBinaryTree = new ThreadBinaryTree04();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadNodes(root);

        // 测试: 以10号节点测试
        HeroNode04 leftNode = node5.getLeft();
        HeroNode04 rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 =" + leftNode); // 3
        System.out.println("10号结点的后继结点是 =" + rightNode); // 1

        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}

class ThreadBinaryTree04 {
    private HeroNode04 root;
    private HeroNode04 pre = null; //预留指针

    public void setRoot(HeroNode04 root) {
        this.root = root;
    }

    //中序线索化二叉树
    public void threadNodes(HeroNode04 node) {
        if (node == null) {
            return;
        }
        //1.线索化左子树
        threadNodes(node.getLeft());
        //2.线索化当前节点
        if (node.getLeft() == null) { //待线索化的节点没左指针或者右指针
            node.setLeft(pre);
            node.setLeftType(1);//默认为0
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //3.线索化右子树
        threadNodes(node.getRight());
    }

    // 遍历线索化二叉树的方法
    public void threadedList() {
        // 定义一个变量，存储当前遍历的结点，从root开始
        HeroNode04 node = root;
        while (node != null) {
            // 循环的找到leftType == 1的结点，第一个找到就是8结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            // 打印当前这个结点
            System.out.println(node);
            // 如果当前结点的右指针指向的是后继结点,就一直输出
            while (node.getRightType() == 1) {
                // 获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            // 让node指向该结点的右孩子
            node = node.getRight();
        }
    }
}

class HeroNode04{
    private int no;
    private String name;
    private HeroNode04 left;
    private HeroNode04 right;
    private int leftType;
    private int rightType;

    public HeroNode04(int no, String name){
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode04 getLeft() {
        return left;
    }

    public void setLeft(HeroNode04 left) {
        this.left = left;
    }

    public HeroNode04 getRight() {
        return right;
    }

    public void setRight(HeroNode04 right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode04{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}