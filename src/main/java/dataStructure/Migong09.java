package dataStructure;

public class Migong09 {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //墙
        for(int i =0; i< 7; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for(int i =0; i < 8; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] = 1;
        //输出地图
        for(int i = 0; i < 8; i++){
            for(int j=0; j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map,1,1);
        //行动轨迹
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    // 使用递归回溯来给小球找路
    // 说明
    // 1. map 表示地图
    // 2. i,j 表示从地图的哪个位置开始出发 (1,1)
    // 3. 如果小球能到 map[6][5] 位置，则说明通路找到.
    // 4. 约定： 当map[i][j] 为 0 表示该点没有走过；为 1 表示墙 ； 2 表示走过，是通路 ； 3 表示该点已经走过，但是走不通
    // 5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){
            return true;
        }else{
            if(map[i][j]==0){
                map[i][j]=2; //假定这个步骤，是回溯的前提
                if(setWay(map,i+1, j)){
                    return true;
                }else if(setWay(map, i, j+1)){
                    return true;
                }else if(setWay(map, i-1,j)){
                    return true;
                }else if(setWay(map, i, j-1)){
                    return true;
                }else{
                    map[i][j]=3;
                    return false;
                }
            }else{
                return false;//非 0 则不能走
            }
        }
    }
}
