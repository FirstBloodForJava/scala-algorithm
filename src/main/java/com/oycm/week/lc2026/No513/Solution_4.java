package com.oycm.week.lc2026.No513;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_4 {

    /**
     * 按奇偶比统计子数组 II
     * @param nums
     * @param a
     * @param b
     * @return
     */
    public long countRatioSubarrays(int[] nums, int a, int b) {
        /*
        给你一个整数数组 nums，以及两个整数 a 和 b。
        对于一个子数组，定义：
            x 表示其中偶数元素的数量。
            y 表示其中奇数元素的数量。
        子数组中偶数与奇数的比例定义为 x / y，其中该比例按照精确的有理数值进行比较。
        如果一个子数组满足以下条件，则称其为有效子数组：
            y > 0，并且
            x / y <= a / b。
        返回 nums 中有效子数组的数量。
        子数组是数组中一个连续的非空元素序列。
         */
        /*
        1 <= nums.length <= 1e5
        1 <= nums[i] <= 1e9
        1 <= a, b <= 1e9
         */
        int n = nums.length;
        // 偶数 - 奇数
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + ((nums[i] % 2 == 0) ? b : -a);
        }

        // 离散化
        long[] sorted = pref.clone();
        Arrays.sort(sorted);
        int m = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != sorted[i - 1]) m++;
        }
        // 映射到 1..m
        int[] rank = new int[n + 1];
        int idx = 1;
        for (int i = 0; i < sorted.length; i++) {
            if (i > 0 && sorted[i] != sorted[i - 1]) idx++;
        }
        // 采用 HashMap 映射
        Map<Long, Integer> map = new HashMap<>();
        int r = 1;
        for (long v : sorted) {
            if (!map.containsKey(v)) {
                map.put(v, r++);
            }
        }

        FenwickTree bit = new FenwickTree(m);
        long ans = 0;
        for (int j = 0; j <= n; j++) {
            int curRank = map.get(pref[j]);
            // 已插入元素个数为 j（因为插入了 0..j-1）
            // 查询小于 curRank 的个数
            int less = bit.pre(curRank - 1);
            // 大于等于当前值的个数 = j - less
            ans += (j - less);
            bit.update(curRank, 1);
        }
        return ans;
    }

}

class FenwickTree {

    private final int[] tree;

    public FenwickTree(int n) {
        tree = new int[n + 1]; // 使用下标 1 到 n
    }

    // a[i] 增加 val, 这里为什么时 i 的 lowbit 增加 val
    // 1 <= i <= n
    public void update(int i, int val) {
        /*
        tree[i, i + lowbit(i), i + lowbit(i) + lowbit(i + lowbit(i)), ... ] 都加上 val
         */
        for (; i < tree.length; i += i & -i) {
            tree[i] += val;
        }
    }

    // 求前缀和 a[1] + ... + a[i]
    // 1 <= i <= n
    public int pre(int i) {
        int res = 0;
        /*
        res 等于以下 tree 元素之和
        [i, i - lowbit(i), i - lowbit(i) - lowbit(i - lowbit(i)), ...]
         */
        for (; i > 0; i &= i - 1) {
            res += tree[i];
        }
        return res;
    }
}