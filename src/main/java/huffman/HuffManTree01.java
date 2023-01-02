package huffman;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @desc：
 * @author：xub
 * @createDate：2023/1/1 10:23
 */
public class HuffManTree01 {
    public static void main(String[] args) {
        int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
        Node root = createhuffManTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root){
        if( root !=null){
            root.preOrder();
        }else{
            System.out.println("根节点为空");
        }
    }

    //创建哈夫曼树
    public static Node createhuffManTree(int[] arr){
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        while(nodes.size()>1){
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left !=null){
            this.left.preOrder();
        }
        if(this.right !=null){
            this.right.preOrder();
        }
    }
}
