package com.oycm.algorithm.d.binary_search_first_k;


public class Solution_1 {

    /**
     * 668. <a href="https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/description/">乘法表中第k小的数</a>
     * <p>
     * 乘法表矩阵 m * n
     *
     * @param m
     * @param n
     * @param k
     * @return 找出第 k 小的数字
     */
    public int findKthNumber(int m, int n, int k) {
        /*
        第 k 小问题等价于: 存在最小值 x, 满足 <= x 的数至少有 k 个
        x 越大, 越能找到 k 个数, x 越小, 越不能找到 k 个数, check(right) >= k
         */
        int left = 0;
        int right = m * n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(m, n , mid, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }
    public boolean check(int m, int n, int x, int k) {
        /*int cnt = 0;
        for (int i = 1; i <= m; i++) {
            cnt += Math.min(x / i, n);
        }*/
        /*
        优化: min(x/i, n)
        当 x/i >= n 时, x/n >= i, min(x/i, n) = n;
        当 x/i < n 时, x/n < i, min(x/i, n) = x/i;
        记录 k = x/n; [1, m] 符合条件的个数 = k * n + [1+k, m] x/i 的 和
         */
        int cnt = x / n * n;
        for (int i = x / n + 1; i <= m ; i++) {
            cnt += x / i;
        }
        return cnt >= k;
    }

}
