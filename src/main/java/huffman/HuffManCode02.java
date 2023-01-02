package huffman;

import jdk.nashorn.internal.ir.CallNode;

import java.util.*;

/**
 * @desc：
 * @author：xub
 * @createDate：2023/1/2 19:09
 */
public class HuffManCode02 {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        /**
         * 样例：String str = "abcdefg"; 转换成字节数组：b[] = {97, 98, 99, 100, 101, 102,103}
         */
        byte[] contentBytes = content.getBytes();//会将字符转为Ascii码存储，b[] = {97, 98, 99, 100, 101, 102,103}
        System.out.println(contentBytes.length);//40
//        for (byte b : contentBytes) {
//            System.out.println(b);
//        }
        //生成huffman树
        List<Node01> nodes = getNodes(contentBytes);
        Node01 huffmanTreeRoot = createHuffManTree(nodes);
        preOrder(huffmanTreeRoot);
        //提取huffman编码
        huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println(getCodes(huffmanTreeRoot));
        //编码
        byte[] zip = Huffmanzip(contentBytes, huffmanCodes);
        System.out.println(zip.length);
        //解码
        byte[] bytes = huffmanDecode(huffmanCodes, zip); //b[] = {97, 98, 99, 100, 101, 102,103}
//        for (byte b : bytes) {
//            System.out.println(b);
//        }
        System.out.println(bytes.length);
    }

    //解码
    private static byte[] huffmanDecode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){
        // 1.将压缩后的字节数组转换成原先的"10101000101111111..."
        // 示例[-88,-38,56....] ==> 10101000101111111...
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<huffmanBytes.length - 1; i++){ //取不到最后一位
            stringBuilder.append(byteToString(true, huffmanBytes[i]));
        }
        // 字节数组的最后一个字节另做处理，如果是负数，flag为true；
        // 如果是正数，flag为false，拼接后长度与原先相等不做处理，若小于原先长度则先补0后拼接，使其与原先长度相等
        if(huffmanBytes[huffmanBytes.length - 1] < 0){
            stringBuilder.append(byteToString(true, huffmanBytes[huffmanBytes.length - 1]));
        }else{
            //假如最后一位是 3，其对应的二进制可能为 011或0011或11，所以需对齐位数
            String str = byteToString(false, huffmanBytes[huffmanBytes.length - 1]);
            while(str.length() + stringBuilder.length() < huffmanStr.length()){
                stringBuilder.append(0);
            }
            stringBuilder.append(str);
        }

        // 2. 将哈夫曼编码表转换成哈夫曼解码表
        HashMap<String, Byte> huffmanDecodes = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> b : huffmanCodes.entrySet()) {
            huffmanDecodes.put(b.getValue(), b.getKey());
        }

        // 3.把"10101000101111111..."按照指定的哈夫曼解码表进行解码
        ArrayList<Byte> list = new ArrayList<Byte>();
        for(int i= 0; i< stringBuilder.length();){
            int count=0;
            Byte b = null;
            while(true){
                String key = stringBuilder.substring(i, i+count);
                b = huffmanDecodes.get(key);
                if(b == null){
                    count++;
                }else{
                    break;
                }
            }
            list.add(b);
            i+=count;
        }
        byte[] b = new byte[list.size()];
        for(int i=0; i<b.length;i++){
            b[i] = list.get(i);
        }
        return b;
    }

    //字节码转二进制
    private static String byteToString(boolean flag, byte b){
        int tmp = b;
        if(flag){
            //补高位，最后一个byte为正数时不能补高位
            tmp |=256; //正数和负数都可以进行该操作，负数不影响
        }
        /**
         * 正数：补齐高位
         * String str = Integer.toBinaryString(56); //返回 11 1000
         * String str = Integer.toBinaryString(56 | 256); //返回 1 0011 1000
         * 负数：不变化
         * ring str = Integer.toBinaryString(-88); //返回11111111111111111111111110101000
         *
         * String str = Integer.toBinaryString(-88 | 256); //返回11111111111111111111111110101000
         */
        String str = Integer.toBinaryString(tmp);
        if(flag){
            return str.substring(str.length()-8); //针对负数，正数无影响
        }else{
            return str; //最后一个byte为正数时，不用截取
        }
    }

    static StringBuilder huffmanStr = new StringBuilder(); //存储赫夫曼编码对应的字符串

    //哈夫曼编码之后进行压缩，每8位存储在一个字节
    private static byte[] Huffmanzip(byte[] bytes, Map<Byte, String> huffmanCodes){
        //1.利用 huffmanCodes哈夫曼编码表 将 contentBytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        huffmanStr = stringBuilder;
        System.out.println(huffmanStr.toString());

        //2.将赫夫曼编码对应的字符串每8位（补码）进行压缩（十进制数）
        int length = 0;
        if(huffmanStr.length() % 8 ==0){
            length = huffmanStr.length() / 8;
        }else{
            length = huffmanStr.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[length];
        int index = 0;
        for(int i=0; i < huffmanStr.length(); i = i+8){
            String strByte;
            if(i+8 > huffmanStr.length()){
                strByte = huffmanStr.substring(i);
            }else{
                strByte = huffmanStr.substring(i, i+8);
            }
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanCodeBytes;
    }

    //定义为类变量，递归的时候可以重复调用
    static Map<Byte, String> huffmanCodes =  new HashMap<Byte, String>();

    private static Map<Byte, String> getCodes(Node01 root){
        StringBuilder stringBuilder = new StringBuilder();
        if(root == null){
            return null;
        }else{
            getCodes(root, "", stringBuilder);
            return huffmanCodes;
        }
    }

    private static void getCodes(Node01 node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if(node.data == null){
            getCodes(node.left, "0", stringBuilder1);
            getCodes(node.right, "1", stringBuilder1);
        }else{
            huffmanCodes.put(node.data, stringBuilder1.toString());
        }
    }


    //字符串转换成字节数组，然后计算每个字符出现的次数作为权重，并生成Node节点
    public static List<Node01> getNodes(byte[] contentBytes){
        ArrayList<Node01> nodes = new ArrayList<Node01>();
        HashMap<Byte, Integer> map = new HashMap<Byte, Integer>();
        for (byte b : contentBytes) {
//            System.out.println(b);
            Integer count = map.get(b);
            if(count == null){
                map.put(b, 1);
            }else{
                map.put(b, count+1);
            }
        }
        for (Map.Entry<Byte, Integer> e : map.entrySet()) {
            nodes.add(new Node01(e.getKey(),e.getValue()));
        }
        System.out.println(nodes);
        return nodes;
    }
    //对Node数组进行排序生成哈夫曼树
    public static Node01 createHuffManTree(List<Node01> nodes){
        while(nodes.size()>1){
            Collections.sort(nodes);
            Node01 left = nodes.get(0);
            Node01 right = nodes.get(1);

            Node01 parent = new Node01(null, left.weight + right.weight);//生成的节点 data存入的是null
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //前序遍历
    public static void preOrder(Node01 root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("根节点不为空，不能遍历");
        }
    }
}
//Node除了权重属性，增加一个值属性
class Node01 implements Comparable<Node01>{
    Byte data;
    int weight;
    Node01 left;
    Node01 right;

    public Node01(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node01 o) {
        return this.weight - o.weight;
    }
}
