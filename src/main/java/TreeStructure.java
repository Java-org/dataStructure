import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class TreeStructure {
    public static void main(String[] args) {
        TreeStructure deptTree = new TreeStructure();
        List<DepNode> data = deptTree.getData();
        System.out.println(data);
        List<DepNode> depNodes = deptTree.buildTree(data);
        System.out.println(depNodes);
        for (DepNode depNode : depNodes) {
            Stack<DepNode> pathstack = new Stack<DepNode>();
            deptTree.iteratorNode(depNode, pathstack);
        }

    }
    
    private void iteratorNode(DepNode depNode, Stack<DepNode> pathstack){
        pathstack.push(depNode);
        List<DepNode> childId = depNode.getChildId();
        if (childId == null){
            ArrayList<DepNode> depNodes = new ArrayList<DepNode>();
            Iterator<DepNode> stackNode = pathstack.iterator();
            while (stackNode.hasNext()){
                depNodes.add(stackNode.next());
            }
            System.out.println(depNodes);
            return;
        }else{
            Iterator<DepNode> it = childId.iterator();
            while (it.hasNext()){
                DepNode next = it.next();
                iteratorNode(next, pathstack);
                pathstack.pop();
            }

        }
    }

    private List<DepNode> buildTree(List<DepNode> depNodes){
        ArrayList<DepNode> topDepNodes = new ArrayList<DepNode>();
        for (DepNode depNode : depNodes) {
            if("-1".equals(depNode.getParentId())){
                topDepNodes.add(depNode);
            }
        }
        ArrayList<DepNode> result = new ArrayList<DepNode>();
        for (DepNode topDepNode : topDepNodes) {
            DepNode child = this.getChild(topDepNode, depNodes);
            result.add(child);
        }
        return result;
    }

    private DepNode getChild(DepNode parent, List<DepNode> depNodes){
        ArrayList<DepNode> childList = new ArrayList<DepNode>();
        for (DepNode depNode : depNodes) {
            if(parent.getDeptId().equals(depNode.getParentId())){
                childList.add(this.getChild(depNode, depNodes));
            }
        }
        parent.setChildId(childList);
        return parent;
    }


    private List<DepNode> getData() {
        String jsonData = "[{\"deptId\":\"1001\",\"parentId\":\"-1\"}," +
                "{\"deptId\":\"100101\",\"parentId\":\"1001\"}," +
                "{\"deptId\":\"10010101\",\"parentId\":\"100101\"}," +
                "{\"deptId\":\"10010102\",\"parentId\":\"100101\"}," +
                "{\"deptId\":\"100102\",\"parentId\":\"1001\"}," +
                "{\"deptId\":\"100103\",\"parentId\":\"1001\"}," +
                "{\"deptId\":\"1002\",\"parentId\":\"-1\"}," +
                "{\"deptId\":\"100201\",\"parentId\":\"1002\"}," +
                "{\"deptId\":\"10020101\",\"parentId\":\"100201\"}," +
                "{\"deptId\":\"10020102\",\"parentId\":\"100201\"}," +
                "{\"deptId\":\"100202\",\"parentId\":\"1002\"}]";
        // json数据转换为实体类
        List<DepNode> nodeData = JSON.parseArray(jsonData, DepNode.class);
        return nodeData;
    }

}

class DepNode{
    private String deptId;
    private String parentId;
    private List<DepNode> childId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<DepNode> getChildId() {
        return childId;
    }

    public void setChildId(List<DepNode> childId) {
        this.childId = childId;
    }

    @Override
    public String toString() {
        return "Node{" +
                "deptId='" + deptId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", childId=" + childId +
                '}';
    }
}