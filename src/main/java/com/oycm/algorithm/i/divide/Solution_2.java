package com.oycm.algorithm.i.divide;

public class Solution_2 {

    /**
     * 2698. <a href="https://leetcode.cn/problems/find-the-punishment-number-of-an-integer/description/">求一个整数的惩罚数</a> 1679
     *
     * @param n
     * @return
     */
    public int punishmentNumber(int n) {
        /*
        n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和:
            1 <= i <= n
            i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
         */
        /*
        预处理范围中有哪些惩罚数
         */
        return ans[n];
    }

    static int[] ans = new int[1001];

    static {
        for (int i = 1; i <= 1000; i++) {
            int pow = i * i;
            ans[i] = ans[i - 1] + (dfs(pow, i) ? pow : 0);
        }
    }

    public static boolean dfs(int pow, int i) {
        /*
        从右到左开始分割 1296
         */
        if (i < 0) {
            return false;
        }
        if (pow == 0) {
            return i == 0;
        }
        int pow10 = 1;
        for (int x = 0; pow > 0; pow /= 10) {
            x += pow % 10 * pow10;
            if (dfs(pow / 10, i - x)) {
                return true;
            }
            pow10 *= 10;
        }

        return false;
    }

    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();
        System.out.println(solution.punishmentNumber(37));
    }

}

class Solution_2_InputPerspective {

    public int punishmentNumber(int n) {
        return ans[n];
    }

    static int[] ans = new int[1001];

    static {
        for (int i = 1; i <= 1000; i++) {
            int pow = i * i;
            ans[i] = ans[i - 1] + ((dfs(0, 0, String.valueOf(pow), i)) ? pow : 0);
        }
    }


    public static boolean dfs(int i, int start, String pow, int sum) {
        /*
        i 得到 pow 字符串，看作
         */
        // 分割后的 sum > i
        if (sum < 0) {
            return false;
        }
        // 分隔完毕
        if (i == pow.length()) {
            return sum == 0;
        }
        if (i < pow.length() - 1) {
            if (dfs(i + 1, start, pow, sum)) {
                return true;
            }
        }
        return dfs(i + 1, i + 1, pow, sum - Integer.parseInt(pow.substring(start, i + 1)));
    }
}
