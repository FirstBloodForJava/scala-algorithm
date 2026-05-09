package com.oycm.dp.a.rob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_6 {

    /**
     * 3186. <a href="https://leetcode.cn/problems/maximum-total-damage-with-spell-casting/description/">施咒的最大总伤害</a> 1841
     *
     * @param power
     * @return
     */
    public long maximumTotalDamage(int[] power) {
        /*
        一个魔法师有许多不同的咒语。
        给你一个数组 power ，其中每个元素表示一个咒语的伤害值，可能会有多个咒语有相同的伤害值。
        已知魔法师使用伤害值为 power[i] 的咒语时，他们就 不能 使用伤害为 power[i] - 2 ，power[i] - 1 ，power[i] + 1 或者 power[i] + 2 的咒语。
        每个咒语最多只能被使用 一次 。
        返回这个魔法师可以达到的伤害值之和的 最大值 。
         */
        /*
        题解思路：值域上的 打家劫舍
        统计 power 中每个元素的出现次数，再按顺序排序成新的数组 a
        定义 dfs(i) 表示从 a[0] 到 a[i] 中选择，可以得到的伤害最大值。
        a[i] 选/不选：
            不选，问题变成从 a[0] 到 a[i-1] 中选择，可以得到的伤害最大值；
            选，a 中值为 a[i]-2, a[i]-1 不能选，问题变成从 a[0] 到 a[j-1] 中选择，可以得到的伤害最大值；其中 j 是最小下标 a[j] >= a[i] - 2
        dfs(i) = max(
            dfs(i-1),
            dfs(j-1) + a[i] * cnt(a[i])
            )
         */
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum);
        }
        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (Integer x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);
        /*
        f[i] = f[i-1] + f[j-1] + a[i] * cnt(a[i])
        i = 0 时，无法表示 -1，可以再 f[] 前面插入一个 0, f[0] = 0
        f[i + 1] = f[i] + f[j] + a[i] * cnt(a[i])
            f[i+1] 表示从 a[0] 到 a[i]中选择，可以得到的最大伤害值。
        为什么 a[i] 的下标不用变？
            因为是在 f 的前面插入一个值，只需要修改 f 相关的下标
         */
        long[] f = new long[n + 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            // a[j] >= x - 2, a[j-1] <= x-2 表示 f[j]
            while (a[j] < x - 2) {
                j++;
            }
            f[i + 1] = Math.max(f[i], f[j] + (long) x * cnt.get(x));
        }
        return f[n];

    }


    /*
    long[] memo = new long[n];
        Arrays.fill(memo, -1);
        return dfs(a, n - 1, cnt, memo);
     */
    public long dfs(int[] a, int i, Map<Integer, Integer> cnt, long[] memo) {
        if (i < 0) return 0;
        if (memo[i] != -1) return memo[i];
        int x = a[i];
        int j = i;
        while (j > 0 && a[j - 1] >= x - 2) {
            j--;
        }

        return memo[i] = Math.max(dfs(a, i - 1, cnt, memo), dfs(a, j - 1, cnt, memo) + (long) x * cnt.get(x));
    }

}
