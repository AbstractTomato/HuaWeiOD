package day03;


import java.lang.ref.PhantomReference;
import java.util.Scanner;

/**
 * 某公司员工食堂以盒饭方式供餐。为将员工取餐排队时间降低为 0，食堂的供餐速度必须要足够快。
 * 现在需要根据以往员工取餐的统计信息，计算出一个刚好能达成排队时间为 0 的最低供餐速度。
 * 即，食堂在每个单位时间内必须至少做出多少份盒饭才能满足要求。
 *
 * 输入
 * ● 第 1 行为一个正整数 N，表示食堂开餐时长。1<=N<=1000。
 * ● 第 2 行为一个正整数 M，表示开餐前食堂已经准备好的盒饭份数。Pi<=M<=1000。
 * ● 第 3 行为 N 个正整数，用空格分隔，依次表示开餐时间内按时间顺序每个单位时间进入食堂取餐的人数 Pi。1<=i<=N，0<=Pi<=100。
 * 输出
 * 一个整数，能满足题目要求的最低供餐速度(每个单位时间需要做出多少份盒饭)。
 */
public class canteen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //食堂的开餐时长
        int n = Integer.parseInt(sc.nextLine());
        //已准备好的盒饭数
        int m = Integer.parseInt(sc.nextLine());
        String[] split = sc.nextLine().split("\\s+");
        int len = split.length;
        int[] people = new int[len];
        for (int i = 0; i < len; i++) {
            people[i] = Integer.parseInt(split[i]);
        }
        System.out.println(minSpeed(people, n, m));
    }

    private static int minSpeed(int[] people, int n, int m) {
        int total = 0;
        for (int p : people) {
            total += p;
        }

        //用二分查找找最小的出餐速度
        int left = 0;
        int right = total;
        while (left < right){
            int mid = left + (right - left) / 2;
            //判断当前出餐速度能不能满足条件
            //如果当前能赶得上,减小出餐速度
            if (canDeliver(people, n, m, mid)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean canDeliver(int[] people, int n, int m, int speed) {
        int remain = m;
        for (int person : people) {
            //刚开餐时,库存余量
            if (remain < person) {
                return false;
            } else {
                remain -= person;
                remain += speed;
            }
        }
        return true;
    }
}
