package search;

/**
 * @desc：
 * @author：xub
 * @createDate：2022/12/31 17:04
 */
public class BinaryTreeDel {
    public static void main(String[] args) {
        BinaryTree03 binaryTree = new BinaryTree03();
        // 创建需要的结点
        HeroNode02 root = new HeroNode02(1, "宋江");
        HeroNode02 node2 = new HeroNode02(2, "吴用");
        HeroNode02 node3 = new HeroNode02(3, "卢俊义");
        HeroNode02 node4 = new HeroNode02(4, "林冲");
        HeroNode02 node5 = new HeroNode02(5, "关胜");

        // 先手动创建该二叉树，后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        // 测试一把删除结点
        System.out.println("删除前,前序遍历");
        binaryTree.preOrder(); // 1,2,3,5,4
        binaryTree.delNode(5);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(); // 1,2,3,4
    }
}

class BinaryTree03{
    private HeroNode02 root;

    public void setRoot(HeroNode02 root) {
        this.root = root;
    }

    public void delNode(int no){
        if(root !=null){
            if(root.getNo()==no){
                root = null;
            }else{
                root.delNode(no);
            }
        }else{
            System.out.println("空二叉树");
        }
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}

class HeroNode02{
    private int no;
    private String name;
    private HeroNode02 left;
    private HeroNode02 right;

    public HeroNode02(int no, String name) {
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

    public HeroNode02 getLeft() {
        return left;
    }

    public void setLeft(HeroNode02 left) {
        this.left = left;
    }

    public HeroNode02 getRight() {
        return right;
    }

    public void setRight(HeroNode02 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode02{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void delNode(int no){
        //递归的终点
        if(this.left !=null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
