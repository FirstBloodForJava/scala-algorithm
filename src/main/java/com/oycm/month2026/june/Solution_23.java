package com.oycm.month2026.june;

import java.util.Arrays;

public class Solution_23 {

    /**
     * 3699. <a href="https://leetcode.cn/problems/number-of-zigzag-arrays-i/description/">锯齿形数组的总数 I</a> 2123
     *
     * @param n
     * @param l
     * @param r
     * @return
     */
    public int zigZagArrays(int n, int l, int r) {
        /*
        给你 三个整数 n、l 和 r。
        长度为 n 的锯齿形数组定义如下：
            每个元素的取值范围为 [l, r]。
            任意 两个 相邻的元素都不相等。
            任意 三个 连续的元素不能构成一个 严格递增 或 严格递减 的序列。
        返回满足条件的锯齿形数组的总数。
        由于答案可能很大，请将结果对 109 + 7 取余数。
        序列 被称为 严格递增 需要满足：当且仅当每个元素都严格大于它的前一个元素（如果存在）。
        序列 被称为 严格递减 需要满足，当且仅当每个元素都严格小于它的前一个元素（如果存在）。
         */
        /*
        3 <= n <= 2000
        1 <= l < r <= 2000
         */
        /*
        相邻元素不能相等，则相邻元素一定是严格递增或严格递减的。
        要求三个连续元素，不能 严格递增 或 严格递减，那么只能是
            递增，递减，递增，递减 ... 顺序。
            递减，递增，递减，递增 ... 顺序。
        从右往左思考，最后两个数有两种情况，递增/递减。[l, r] 区间可以转换成 [0, r-l] 区间，记为 [0, k]
            后两个数选严格递增，前 n-1 个最后两个数选严格递减
         */

        return zigZagArrays_dp_optimize_space(n, l, r);
    }


    public int zigZagArrays_dp(int n, int l, int r) {
        int mod = 1000000007;
        int m = r - l + 1;
        // dpAsc[i][j] 标识 [0, i] 区间的数，第 i-1 个和第 i 个数的关系是递增的，且第 i 个数是 j 的方案数
        int[][] dpAsc = new int[n][m];
        int[][] dpDesc = new int[n][m];
        // 长度为 1 的，每个值是一种方案，可视为 递增或递减。
        for (int j = 0; j < m; j++) {
            dpAsc[0][j] = 1;
            dpDesc[0][j] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 计算 dpAsc[i][j], 找 0 < k < j 的 dpDesc[i-1][k] 方案数和
                long sumAsc = 0;
                for (int k = 0; k < j; k++) {
                    sumAsc += dpDesc[i - 1][k];
                }
                dpAsc[i][j] = (int) (sumAsc % mod);
                // 计算 dpDesc[i][j], 找 j < k && k < m 的 dpAsc[i-1][k] 方案数和
                long sumDesc = 0;
                for (int k = j + 1; k < m; k++) {
                    sumDesc += dpAsc[i - 1][k];
                }
                dpDesc[i][j] = (int) (sumDesc % mod);
            }

        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans += dpAsc[n - 1][i] + dpDesc[n - 1][i];
        }

        return (int) (ans % mod);
    }

    public int zigZagArrays_dp_optimize_space(int n, int l, int r) {
        int mod = 1000000007;
        int m = r - l + 1;
        /*
        dpAsc[i][j] 计算过程用到了 dpDesc[i-1][k] [0, j) 的所有元素和；
        在计算 dpAsc[i][j] 时，先计算以下 dpDesc[i-1] [0, m) 的前缀和；
        计算 dpDesc[i][j] 时，同理；
        这样 由于 dpAsc[i-1] 数据被保存在前缀和数组中了，所有 dpAsc 可以优化成 asc[m] 一维数组，dpDesc 数组同理
         */
        int[] asc = new int[m];
        int[] desc = new int[m];
        Arrays.fill(asc, 1);
        Arrays.fill(desc, 1);
        // 不用在循环中创建，会一直覆盖即可
        long[] descSum = new long[m + 1];
        long[] ascSum = new long[m + 1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                descSum[j + 1] = descSum[j] + desc[j];
                ascSum[j + 1] = ascSum[j] + asc[j];
            }
            for (int j = 0; j < m; j++) {
                // [0, j) 区间和
                asc[j] = (int) (descSum[j] % mod);
                // (j, n) 区间和
                desc[j] = (int) ((ascSum[m] - ascSum[j + 1]) % mod);
            }

        }

        long ans = 0;

        for (int i = 0; i < m; i++) {
            ans += asc[i] + desc[i];
        }

        return (int) (ans % mod);
    }

    public int zigZagArrays_dp_optimize_space_1(int n, int l, int r) {
        int mod = 1000000007;
        int m = r - l + 1;
        int[] asc = new int[m];
        int[] desc = new int[m];
        Arrays.fill(asc, 1);
        Arrays.fill(desc, 1);
        /*
        前一个 desc 前缀和结果就是 下一个 asc
        前一个 asc 后缀和结果就是下一个 desc
         */
        for (int i = 1; i < n; i++) {
            // 计算前缀和的过程中，计算 asc[i]
            long pre = 0;
            for (int j = 0; j < m; j++) {
                int v = desc[j];
                desc[j] = (int) (pre % mod);
                pre += v;
            }
            long suf = 0;
            for (int j = m - 1; j >= 0 ; j--) {
                int v = asc[j];
                asc[j] = (int) (suf % mod);
                suf += v;
            }
            int[] temp = asc;
            asc = desc;
            desc = temp;
        }

        long ans = 0;

        for (int i = 0; i < m; i++) {
            ans += asc[i] + desc[i];
        }

        return (int) (ans % mod);
    }




}
