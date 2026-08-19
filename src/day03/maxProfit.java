package day03;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 给你一个由 '0'（空地）、 '1'（银矿）、'2'（金矿）组成的的地图，矿堆只能由上下左右相邻的金矿或银矿连接形成。
 * 超出地图范围可以认为是空地。
 * 假设银矿价值 1 ，金矿价值 2 ，请你找出地图中最大价值的矿堆并输出该矿堆的价值。
 *
 *
 * 输入
 * 地图元素信息如：
 * 22220
 * 00000
 * 00000
 * 11111
 * 地图范围最大 300*300
 * 0<= 地图元素 <=2
 * 输出
 * 矿堆的最大价值。
 */
public class maxProfit {
    private static int ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.isEmpty()){
                break;
            }
            list.add(line);
        }
        int m = list.size();
        int n = list.getFirst().length();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = list.get(i).charAt(j) - '0';
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] != 0){
                    int cur = dfs(grid, i, j);
                    ans = Math.max(ans, cur);
                }
            }
        }

        System.out.println(ans);
    }

    private static int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0){
            return 0;
        }

        int value = grid[i][j];
        grid[i][j] = 0;


        value +=  dfs(grid, i + 1, j) +
            dfs(grid, i - 1, j) +
            dfs(grid, i, j + 1) +
            dfs(grid, i, j - 1);

        return value;
    }
}
