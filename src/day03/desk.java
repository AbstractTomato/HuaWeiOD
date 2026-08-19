package day03;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 疫情期间课堂的座位进行了特殊的调整，不能出现两个同学紧挨着，必须隔至少一个空位。
 * 给你一个整数数组 desk 表示当前座位的占座情况，由若干 0 和 1 组成，其中 0 表示没有占位，1 表示占位。在不改变原有座位秩序情况下，还能安排坐几个人？
 *
 *
 * 输入
 * 第一行是个子数组表示作为占座情况，由若干 0 和 1 组成，其中 0 表示没有占位，1 表示占位
 * 输出
 * 输出数值表示还能坐几个人
 */
public class desk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(",");
        int[] array = new int[split.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(split[i]);
        }

        int ans = 0;
        //将坐人的位置左右两格置为2,表示不能坐
        for (int i = 0; i < array.length; i++){
            //如果当前位置坐人
            if (array[i] == 1){
                //将左右标记为不能坐人
                if (i >= 1){
                    array[i - 1] = 2;
                }
                if (i <= array.length - 2){
                    array[i + 1] = 2;
                }
            }
        }
        for (int i = 0; i < array.length; i++){
            if (array[i] == 0){
                ans++;
                array[i] = 1;
                //向前移动
                i++;
            }
        }

        System.out.println(ans);
    }
}
