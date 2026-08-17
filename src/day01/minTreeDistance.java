package day01;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 小明在直线的公路上种树，现在给定可以种树的坑位的数量和位置，以及需要种多少棵树苗，问树苗之间的最小间距是多少时，可以保证种的最均匀（两棵树苗之间的最小间距最大）
 *
 * 输入
 * 输入三行：
 * ● 第一行一个整数：坑位的数量
 * ● 第二行以空格分隔的数组：坑位的位置
 * ● 第三行一个整数：需要种植树苗的数量
 * 输出
 * 树苗之间的最小间距
 */
public class minTreeDistance {
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

        int n = Integer.parseInt(list.getFirst()[0]);
        int[] positions = new int[n];
        int k = Integer.parseInt(list.getLast()[0]);
        for (int i = 0; i < n; i++) {
            positions[i] = Integer.parseInt(list.get(1)[i]);
        }

        Arrays.sort(positions);
        int ans = 0;
        int left = 0;
        int right = positions[n - 1] - positions[0];
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (canPlace(positions, n, k, mid)){
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }

    //在最小距离是d的情况下,能否种植k颗树
    private static boolean canPlace(int[] positions, int n, int k, int d) {
        //第一棵树种植在索引为0的位置
        int count = 1;
        int lastPosition = positions[0];
        for (int i = 1; i < n; i++){
            if (positions[i] - lastPosition >= d){
                count++;
                lastPosition = positions[i];
            }
            if (count >= k){
                return true;
            }
        }

        return count >= k;
    }
}
