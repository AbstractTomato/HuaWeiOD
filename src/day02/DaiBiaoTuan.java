package day02;


import java.util.Scanner;

/**
 * 某组织举行会议，来了多个代表团同时到达，接待处只有一辆汽车，可以同时接待多个代表团，为了提高车辆利用率，请帮接待员计算可以坐满车的接待方案，输出方案数量。
 * 约束:
 * 1. 一个团只能上一辆车，并目代表团人数(代表团数量小于30，每个代表团人数小于30)小于汽车容量(汽车容量小于100)
 * 2. 需要将车辆坐满
 *
 *
 * 输入描述
 * 第一行 代表团人数，英文逗号隔开，代表团数量小于30，每个代表团人数小于30。
 * 第二行 汽车载客量，汽车容量小于100
 * 输出描述
 * 坐满汽车的方案数量
 * 如果无解输出0
 */
public class DaiBiaoTuan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(",");
        int n = split.length;
        int[] array = new int[n];
        for (int i = 0; i < n; i++){
            array[i] = Integer.parseInt(split[i]);
        }
        int target = Integer.parseInt(sc.nextLine());

        //dp[i]表示组成i 共有几种方式
        int[] dp = new int[target + 1];
        //容量为0只有一种解决方案
        dp[0] = 1;

        for (int num : array) {
            if (num > target){
                continue;
            }
            for (int i = target; i >= num; i--){
                dp[i] += dp[i - num];
            }
        }

        System.out.println(dp[target]);
    }
}
