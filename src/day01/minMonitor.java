package day01;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 在一长方形停车场内，每个车位上方都有对应监控器，当且仅当在当前车位或者前后左右四个方向任意一个车位范围停车时，监控器才需要打开，
 * 给出某一时刻停车场的停车分布，请统计最少需要打开多少个监控器。
 *
 * 输入
 *
 * 第一行输入 m，n 表示长宽，满足 1<m,n<=20；后面输入 m 行，每行有 n 个 0 或 1 的整数，整数间使用一个空格隔开，
 * 表示该行已停车情况，其中 0 表示空位，1 表示已停。
 *
 * 输出
 *
 * 最少需要打开监控器的数量。
 */
public class minMonitor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String[]> list = new ArrayList<>();
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.isEmpty()){
                break;
            }
            list.add(line.split("\\s+"));
        }

        int m = Integer.parseInt(list.getFirst()[0]);
        int n = Integer.parseInt(list.getFirst()[1]);

        int[][] grid = new int[m][n];
        for (int i = 1; i < list.size(); i++){
            for (int j = 0; j < n; j++){
                grid[i - 1][j] = Integer.parseInt(list.get(i)[j]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    if (i - 1 >= 0 && grid[i - 1][j] != 1){
                        grid[i - 1][j] = 2;
                    }
                    if (i + 1 < m && grid[i + 1][j] != 1){
                        grid[i + 1][j] = 2;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] != 1){
                        grid[i][j - 1] = 2;
                    }
                    if (j + 1 < n && grid[i][j + 1] != 1) {
                        grid[i][j + 1] = 2;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0){
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}
