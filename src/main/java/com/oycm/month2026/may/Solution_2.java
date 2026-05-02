package com.oycm.month2026.may;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_2 {

    /**
     * 788. <a href="https://leetcode.cn/problems/rotated-digits/description/">旋转数字</a> 1397
     *
     * @param n
     * @return
     */
    public int rotatedDigits(int n) {
        /*
        好数：如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。
        如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。
            0, 1, 和 8 被旋转后仍然是它们自己；
            2 和 5 可以互相旋转成对方，6 和 9 同理；
            除了这些以外其他的数字旋转以后都不再是有效的数字。
         */
        /*
        预处理有哪些好数，进行二分查找
         */
        init();
        int idx = Collections.binarySearch(goods, n);
        if (idx < 0) {
            idx = ~idx - 1;
        }
        return idx + 1;
    }

    private static boolean initialized = false;
    public static List<Integer> goods = new ArrayList<>();

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int i = 1; i <= 10000; i++) {
            if (isGood(i)) {
                goods.add(i);
            }
        }
    }

    private boolean isGood(int x) {
        int temp = x;
        int spin = 0;
        int k = 1;
        while (temp > 0) {
            int g = temp % 10;
            if (g == 2) {
                g = 5;
            } else if (g == 5) {
                g = 2;
            } else if (g == 6) {
                g = 9;
            } else if (g == 9) {
                g = 6;
            } else if (g == 3 || g == 4 || g == 7) {
                return false;
            }
            spin += k * g;
            k *= 10;
            temp /= 10;
        }
        return x != spin;
    }

}

class Solution_2376 {

    /**
     * 2376. <a href="https://leetcode.cn/problems/count-special-integers/description/">统计特殊整数</a> 2120
     *
     * @param n
     * @return 返回区间 [1, n] 之间特殊整数的数目。
     */
    public int countSpecialNumbers(int n) {
        /*
        如果一个正整数每一个数位都是 互不相同 的，我们称它是 特殊整数 。
         */
        /*
        题解思路：将 n 转换成字符串，定义 dfs(i, mask, isLimit, isNum) 表示构造第 i 位后及其之后数位的合法方案数
            mask 表示前面选过的集合，数位 [0, 9]，选的第 i 位不能在 mask 中；
            isLimit 表示当前是否收到 n 的约束（构造的数不能超过 n）。
                若为 true，表示当前填入的数字至多是 s[i]，否则至多是 9；
                如果在约束的情况下填了 s[i]，后续填入的数字也会受到约束；
            isNum 表示前面的数位是否填了数字。
                若为 false，可以跳过，或者填入的数字至少为 1；
                如果 true，如果没有限制，至多可以填 9，否则至多填 s[i]
         */
        String s = n + "";
        int[][] memo = new int[s.length()][1 << 10];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, true, false, s.toCharArray(), memo);
    }

    public int dfs(int i, int mask, boolean isLimit, boolean isNum, char[] cs, int[][] memo) {
        if (i == cs.length) {
            return isNum ? 1 : 0;
        }
        /*
        为什么这样记忆化？

         */
        if (!isLimit && isNum && memo[i][mask] != -1) {
            return memo[i][mask];
        }
        int res = 0;
        if (!isNum) {
            res = dfs(i + 1, mask, false, false, cs, memo);
        }
        int up = isLimit ? cs[i] - '0' : 9;
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            // 前面不存在当前 d
            if ((mask >> d & 1) == 0) {
                res += dfs(i + 1, mask | (1 << d), isLimit && d == up, true, cs, memo);
            }
        }
        if (!isLimit && isNum) {
            memo[i][mask] = res;
        }

        return res;
    }
}
