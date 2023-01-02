package search;

/**
 * @desc：二叉树查找
 * @author：xub
 * @createDate：2022/12/31 15:38
 */
public class BinaryTreeSearch {
    public static void main(String[] args) {
        BinaryTree02 binaryTree = new BinaryTree02();
        // 创建需要的结点
        HeroNode01 root = new HeroNode01(1, "宋江");
        HeroNode01 node2 = new HeroNode01(2, "吴用");
        HeroNode01 node3 = new HeroNode01(3, "卢俊义");
        HeroNode01 node4 = new HeroNode01(4, "林冲");
        HeroNode01 node5 = new HeroNode01(5, "关胜");

        // 先手动创建该二叉树，后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //前序遍历查找的次数 4次
        System.out.println("前序遍历方式~~~");
        HeroNode01 resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }
    }
}

class BinaryTree02{
    private HeroNode01 root;

    public void setRoot(HeroNode01 root) {
        this.root = root;
    }

    //前序遍历
    public HeroNode01 preOrderSearch(int no){
        if(this.root !=null){
            return this.root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历
    public HeroNode01 infixOrderSearch(int no){
        if(this.root !=null){
            return this.root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后续遍历
    public HeroNode01 postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

class HeroNode01{
    private int no;
    private String name;
    private HeroNode01 left;
    private HeroNode01 right;

    public HeroNode01(int no, String name) {
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

    public HeroNode01 getLeft() {
        return left;
    }

    public void setLeft(HeroNode01 left) {
        this.left = left;
    }

    public HeroNode01 getRight() {
        return right;
    }

    public void setRight(HeroNode01 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public HeroNode01 preOrderSearch(int no){
        System.out.println("进入前序遍历");
        System.out.println(this.no);
        if(this.no == no){
            return this;
        }
        HeroNode01 resNode = null;
        if(this.left != null){
            resNode =  this.left.preOrderSearch(no);
        }
        if(resNode !=null){
            return resNode;
        }
        if(this.right !=null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历
    public HeroNode01 infixOrderSearch(int no){
        //有两种情况可以返回：1.已找到 2.按顺序3个节点走完都没找到
        HeroNode01 resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    //后续遍历
    public HeroNode01 postOrderSearch(int no){
        HeroNode01 resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
