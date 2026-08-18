package day02;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从 0~N 的箱子，每个箱子上面贴有一个数字，箱子中可能有一个黄金宝箱。
 * 黄金宝箱满足排在它之前的所有箱子数字和等于排在它之后的所有箱子数字和；
 * 第一个箱子左边部分的数字和定义为 0；
 * 最后一个宝箱右边部分的数字和定义为 0。
 *
 * 请帮阿里巴巴找到黄金宝箱，输出第一个满足条件的黄金宝箱编号，如果不存在黄金宝箱，请返回-1
 *
 * 输入描述
 *
 * 箱子上贴的数字列表，使用逗号分隔，例如 1，-1，0。
 *
 * 宝箱的数量不小于 1 个，不超过 10000
 *
 * 宝箱上贴的数值范围不低于-1000，不超过 1000
 *
 * 输出描述
 *
 * 第一个黄金宝箱的编号
 */
public class treasure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String[]> list = new ArrayList<>();
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.isEmpty()){
                break;
            }
            list.add(line.split(","));
        }

        int n = list.getFirst().length;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(list.getFirst()[i]);
        }

        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 1; i < n; i++){
            left[i] = left[i - 1] + array[i - 1];
        }
        for (int j = n - 2; j >= 0; j--){
            right[j] = right[j + 1] + array[j + 1];
        }

        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (left[i] == right[i]){
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }

}
