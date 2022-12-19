package dataStructure;

public class Josephu05 {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);//加入5个小孩节点
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;
    //添加小孩节点，构建一个环形链表
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums值不正确");
            return;
        }
        Boy currentBoy = null;//辅助指针
        for(int i=1; i<=nums;i++){
            Boy boy = new Boy(i);
            if(i==1){
                first = boy;
                first.next = first; //如果boy的属性是 private，则只能用setNext() //构成环
                currentBoy = first;
            }else{
                currentBoy.next=boy;
                boy.next=first;
                currentBoy=boy;
            }
        }

    }

    //遍历环形链表
    public void showBoy(){
        if(first==null){
            System.out.println("链表为空...");
            return;
        }
        //first不能动，借助辅助指针移动
        Boy currentBoy = first;
        while(true){
            System.out.println("当前小孩的编号为:" + currentBoy.getNo());
            if(currentBoy.next == first){
                break;
            }
            currentBoy = currentBoy.next;
        }
    }

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums){
        //1.校验数据
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数有误...");
            return;
        }
        //2.helper移动到最后一个节点
        Boy helper = first; //环形链表的first的节点是可以移动的
        while(true){
            if(helper.getNext() == first){ //hlper已经为最后一个节点
                break;
            }
            helper =helper.getNext();
        }
        //3.报数之前，让helper 和 first移动k-1次
        for(int j = 0;j< startNo -1 ;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //4.当小孩报数时，让 first 和 helper 同时移动 m-1次，然后出圈（循环操作，知道圈中只有一个节点）
        //上一轮小孩出圈之后，first会自动后移一位，所以只用移动m-1
        while (true){
            if(helper == first) { //说明圈中只有一个节点
                break;
            }
            for(int j=0; j <countNum-1; j++){
                first = first.getNext();
                helper = helper.getNext();
                }
                System.out.printf("小孩%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
            }
        System.out.printf("最后留在圈中的小孩编号是%d \n",first.getNo()); //最后圈中只会剩余一个
        }
    }


class Boy{
    private int no;//编号
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}
