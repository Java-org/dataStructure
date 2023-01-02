package search;

/**
 * @desc：二叉树遍历
 * @author：xub
 * @createDate：2022/12/31 15:38
 */
public class BinaryTree {
    public static void main(String[] args) {
        // 先创建一颗二叉树
        BinaryTree01 binaryTree = new BinaryTree01();
        // 创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 先手动创建该二叉树，后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        // 测试
        System.out.println("前序遍历"); // 1,2,3,5,4
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder(); // 2,1,5,3,4
        System.out.println("后序遍历");
        binaryTree.postOrder(); // 2,5,4,3,1
    }
}

class BinaryTree01{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(this.root !=null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.root !=null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后续遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right !=null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left !=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right !=null){
            this.right.infixOrder();
        }
    }
    //后续遍历
    public void postOrder(){
        if(this.left !=null){
            this.left.infixOrder();
        }
        if(this.right !=null){
            this.right.infixOrder();
        }
        System.out.println(this);
    }
}
