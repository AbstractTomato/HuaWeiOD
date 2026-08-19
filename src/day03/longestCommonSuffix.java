package day03;


import java.util.Scanner;

/**
 * 编写一个函数来查找字符串数组中的最长公共后缀；
 * 如果不存在公共后缀，返回固定字符串： @Zero。
 * 补充说明：
 * 1. 字符串长度范围：[2,1000]；
 * 2. 字符串中字符取值范围为 [1,126]。
 */
public class longestCommonSuffix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().replace("[", "")
                .replace("]", "")
                .replace("\"", "")
                .split(",");

        //处理最长后缀
        String suffix = split[0];
        for (int i = 1; i < split.length; i++){
            String str = split[i];
            int j = 1;
            while (suffix.length() - j >= 0 && str.length() - j >= 0 &&
                    suffix.charAt(suffix.length() - j) == str.charAt(str.length() - j)){
                j++;
            }
            if (j == 1){
                suffix = "@ZERO";
                break;
            }
            suffix = suffix.substring(suffix.length() - j + 1);
        }

        System.out.println(suffix);

        //处理最长前缀
        String prefix = split[0];
        for (int i = 1; i < split.length; i++){
            String str = split[i];
            int j = 0;
            while (j < prefix.length() && j < str.length() && prefix.charAt(j) == str.charAt(j)){
                j++;
            }
            if (j == 0){
                prefix = "";
                break;
            }
            prefix = prefix.substring(0, j);
        }

        System.out.println(prefix);
        /*return prefix;*/
    }
}
