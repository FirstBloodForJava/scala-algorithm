package com.oycm.month2026.april;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_9 {

    /**
     * 3655. <a href="https://leetcode.cn/problems/xor-after-range-multiplication-queries-i/description/">区间乘法查询后的异或 II</a> 1556
     *
     * @param nums    nums.length = [1, 1e5]
     * @param queries queries[i] = [l, r, k, v]; queries.length = [1, 1e5]
     * @return
     */
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = (int) Math.sqrt(queries.length);
        // 记录 k 相同的区间操作
        List<int[]>[] groups = new ArrayList[B];
        Arrays.setAll(groups, l -> new ArrayList<>());

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k < B) {
                groups[k].add(new int[]{l, r, v});
            } else {
                // 暴力计算
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) ((long) nums[i] * v % MOD);
                }
            }
        }

        int[] diff = new int[n + 1];
        for (int k = 1; k < B; k++) {
            // 等于 k 的区间操作
            List<int[]> g = groups[k];
            if (g.isEmpty()) {
                continue;
            }
            // 根据 l % k 再 分组 [0, k)
            List<int[]>[] buckets = new ArrayList[k];
            Arrays.setAll(buckets, l -> new ArrayList<>());
            for (int[] t : g) {
                // k 分组之后，再根据 l % k 分成 [0, k) 组
                buckets[t[0] % k].add(t);
            }

            for (int start = 0; start < k; start++) {
                List<int[]> bucket = buckets[start];
                if (bucket.isEmpty()) {
                    continue;
                }
                if (bucket.size() == 1) {
                    // 只有一个询问，直接暴力
                    int[] t = bucket.get(0);
                    int l = t[0], r = t[1];
                    long v = t[2];
                    for (int i = l; i <= r; i += k) {
                        nums[i] = (int) (nums[i] * v % MOD);
                    }
                    continue;
                }
                // l/k 作为商分数组的下标 压缩到 n / k 下标
                int m = (n - start - 1) / k + 1;
                // 初始化用到的商分数组范围
                Arrays.fill(diff, 0, m, 1);
                for (int[] t : bucket) {
                    int l = t[0];
                    long v = t[2];
                    diff[l / k] = (int) (diff[l / k] * v % MOD);
                    int r = (t[1] - start) / k + 1;
                    diff[r] = (int) (diff[r] * pow(v, MOD - 2) % MOD);
                }

                long mulD = 1;
                // 还原商分数组
                for (int i = 0; i < m; i++) {
                    mulD = mulD * diff[i] % MOD;
                    int j = start + i * k;
                    nums[j] = (int) (nums[j] * mulD % MOD);
                }
            }
        }

        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }

    private static final int MOD = 1000000007;

    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }
}

class Solution_9_1 {

    public int xorAfterQueries(int[] nums, int[][] queries) {
        /*
        对于每个查询，按以下步骤执行操作：
            设定 idx = l；
            当 idx <= r 时：
                nums[idx] = (nums[idx] * v) % (1e9 + 7)
                idx += k
         */
        /*
        题解思路：暴力计算的复杂度是 q n/k
        假设 k = 3, l = 1, r = 9; 需要对 1, 4, 7 的 nums * v; 可以建一个 商分数组，初始化值都为 1
        diff[1] * v, diff[10] * 1/v; 除以 v, (a / b) % mod = a * b^(mod - 2) % mod 时间复杂度是 log mod
        n = 10; k = 3
        0, 3, 6, 9
        1, 4, 7,
        2, 5, 8
        遍历一个长为 k 的商分数组，需要 O(n) 的时间, 总的时间复杂度是 nK, K 是最大的 k
        根据均值不等式 a + b >= 2sqrt(ab)
        总的时间复杂度 qn/k + kn + q log mod 什么时候这里的值最小，最小值为 n sqrt(q) + q log mod

        时间复杂度 n sqrt(q) + q log mod
        空间复杂度 n sqrt(q)
         */
        int n = nums.length;
        int B = (int) Math.sqrt(queries.length);
        int[][] diff = new int[B][];
        // 判断是否存在 k 的商分数组需要遍历
        boolean[][] has = new boolean[B][];

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2];
            long v = q[3];
            if (k < B) {
                // 懒初始化
                if (diff[k] == null) {
                    diff[k] = new int[n + k];
                    Arrays.fill(diff[k], 1);
                    has[k] = new boolean[k];
                }
                has[k][l % k] = true;
                diff[k][l] = (int) (diff[k][l] * v % MOD);
                r = r - (r - l) % k + k;
                diff[k][r] = (int) (diff[k][r] * pow(v, MOD - 2) % MOD);
            } else {
                // 暴力遍历区间求 *v 的值
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) (nums[i] * v % MOD);
                }
            }
        }

        for (int k = 0; k < B; k++) {
            int[] d = diff[k];
            if (d == null) {
                continue;
            }
            for (int start = 0; start < k; start++) {
                // 是否需要遍历这个 [0, k) 的一部分
                if (!has[k][start]) {
                    continue;
                }
                long mulD = 1;
                /*
                0, k, 2k, ...
                1, k+1, 2k+1, ...
                ...
                k-1,2k-1, 3k-1, ...
                 */
                for (int i = start; i < n; i += k) {
                    mulD = mulD * d[i] % MOD;
                    nums[i] = (int) (nums[i] * mulD % MOD);
                }
            }
        }

        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;

    }

    private static final int MOD = 1000000007;

    // 快速幂
    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }
}
