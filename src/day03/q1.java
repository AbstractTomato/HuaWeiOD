package day03;

import java.util.Arrays;


public class q1 {
    //K表示实力差距的阈值
    //R是选手的实力分数
    public int solve(int N, int K, int[] R){
        //可以匹配的场次数
        int ans = 0;

        Arrays.sort(R);
        int n = R.length;
        for (int i = n; i >= 2; i /= 2){
            int half = i / 2;
            int weak = n - i;
            int weakEnd = weak + half;
            int strong = weakEnd;
            int strongEnd = strong + half;
            while (weak < weakEnd && strong < strongEnd){
                long diff = (long) R[strong] - R[weak];
                if (diff <= K){
                    ans++;
                    weak++;
                    strong++;
                }else {
                    weak++;
                }
            }
        }

        return ans;
    }
}
