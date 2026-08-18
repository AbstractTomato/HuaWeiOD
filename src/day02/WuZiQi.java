package day02;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 张兵和王武是五子棋迷，工作之余经常切磋棋艺。
 * 走了一会儿，轮到张兵了，他对着一条线思考起来了，这条线上的棋子分布如下：
 * 用数组表示： -1 0 1 1 1 0 1 0 1 -1
 * 棋子分布说明:
 * 1. -1 代表白子，0 代表空位，1 代表黑子；
 * 2. 数组长度 L, 满足 1 < L < 40, 且 L 为奇数；
 * 请帮他写一个程序，算出最有利的出子位置。
 *
 * 最有利定义：
 * 1. 找到一个空位 (0)，用棋子 (1/-1) 填充该位置，可以使得当前子的最大连续长度变大；
 * 2. 如果存在多个位置，返回最靠近中间的较小的那个坐标；
 * 3. 如果不存在可行位置，直接返回 -1；
 * 4. 连续长度不能超过 5 个(五子棋约束)；
 *
 *
 *
 * 输入描述
 *
 * 第一行: 当前出子颜色 第二行: 当前的棋局状态
 *
 * 输出描述
 *
 * 1 个整数，表示出子位置的数组下标
 */
public class WuZiQi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int color = Integer.parseInt(sc.nextLine());
        List<String[]> list = new ArrayList<>();
        list.add(sc.nextLine().split("\\s+"));
        int size = list.getFirst().length;
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(list.getFirst()[i]);
        }


        int ans = -1;
        int maxLen = 0;
        //假设开始放置棋子
        for (int i = 0; i < array.length; i++){
            if (array[i] != 0){
                continue;
            }
            array[i] = color;
            int len = change(array, color);
            if (maxLen < len){
                maxLen = len;
                ans = i;
            }else if (maxLen == len){
                int mid = array.length / 2;
                ans = Math.abs(mid - ans) < Math.abs(mid - i) ? ans : i;
            }
            array[i] = 0;
        }

        System.out.println(ans);
    }

    private static int change(int[] array, int color) {
        int[] dp = new int[array.length];
        dp[0] = array[0] == color ? 1 : 0;
        int maxLen = 0;

        for (int i = 1; i < array.length; i++){
            if (array[i] == color){
                dp[i] = dp[i - 1] + 1;
                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }
}
