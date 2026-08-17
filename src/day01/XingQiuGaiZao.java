package day01;


import java.util.*;

/**
 * 2XXX 年，人类通过对火星的大气进行宜居改造分析，使得火星已在理论上具备人类宜居的条件；
 * 由于技术原因，无法一次性将火星大气全部改造，只能通过局部处理形式；
 * 假设将火星待改造的区域为 row∗column 的网格，每个网格有 3 个值，宜居区、可改造区、死亡区，使用 YES、NO、NA 代替：
 * ● YES 表示该网格已经完成大气改造；
 * ● NO 表示该网格未进行改造，后期可进行改造；
 * ● NA 表示死亡区，不作为判断是否改造完成的宜居，无法穿过；
 * 初始化下，该区域可能存在多个宜居区，并且每个宜居区能同时在每个太阳日单位向上下左右四个方向的相邻格子进行扩散，自动将 4 个方向相邻的真空区改造成宜居区；
 * 请计算这个待改造区域的网格中，可改造区是否能全部变成宜居区，如果可以，则返回改造的太阳日天数，不可以则返回-1。
 */
public class XingQiuGaiZao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> list = new ArrayList<>();

        //逐行扫描输入
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.isEmpty()){
                break;
            }
            //分割空白字符,并添加到list中
            list.add(line.split("\\s+"));
        }

        //获取网格的行数和列数
        int rows = list.size();
        int cols = list.getFirst().length;

        //创建二维数组存储网格状态
        String[][] grid = new String[rows][cols];
        //创建队列存储宜居区的坐标
        Deque<int[]> queue = new LinkedList<>();
        //可改造区的数量
        int noCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //存储坐标
                grid[i][j] = list.get(i)[j];
                //将宜居区的坐标存储到队列中
                if ("YES".equals(grid[i][j])){
                    queue.offer(new int[]{i, j});
                    //如果遇到了可改造区,自增即可
                }else if ("NO".equals(grid[i][j])){
                    noCount++;
                }
            }
        }

        //记录扩散天数
        int days = 0;
        //四个扩散方向
        int[][] directions = new int[][]{
                {1, 0}, //下
                {-1, 0}, //上
                {0, 1}, //右
                {0, -1} //左
        };

        while (noCount > 0 && !queue.isEmpty()){
            //这一天的YES区的数量,要向外扩散
            int size = queue.size();
            for (int i = 0; i < size; i++){
                //当前YES区的坐标
                int[] cor = queue.poll();
                int row = cor[0];
                int col = cor[1];

                //向四个方向扩散
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    //检查边界
                    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || !"NO".equals(grid[newRow][newCol])){
                        continue;
                    }
                    //此时改造完成,将此刻的坐标加入队列
                    queue.offer(new int[]{newRow, newCol});
                    grid[newRow][newCol] = "YES";
                    //可改造区的数量减一
                    noCount--;
                }
            }
            //一天已经过去了
            days++;
        }

        System.out.println(noCount == 0 ? days : -1);
    }

}
