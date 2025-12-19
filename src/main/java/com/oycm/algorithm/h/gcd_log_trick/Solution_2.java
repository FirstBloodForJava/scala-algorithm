package com.oycm.algorithm.h.gcd_log_trick;


import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 2654. <a href="https://leetcode.cn/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/description/">使数组所有元素变成 1 的最少操作次数</a> 1929
     * <p>
     * 0 <= i < n - 1， 可将 nums[i] 或 nums[i+1] 两者之一替换成它们的最大公约数, 记为一次操作
     *
     * @param nums
     * @return 求 将 nums 所有元素变成 1 的最少操作次数, 不能变成 1, 则返回 -1
     */
    public int minOperations(int[] nums) {
        /*
        怎么样能最快?
        nums[i] 和 nums[i+1] 的最大公约数有哪几种情况?
            [1, min(nums[i], nums[i+1])] 1, 大于 1 的公约数, 无论是 nums[i] 还是 nums[i+1] 换成 gcd, 只要 gcd > 1, nums[i] 和 nums[i+1] 的 gcd 都会大于 1
        找最小的连续子数组长度 使得 nums[i,j] 的最大公约数是 1, ans = j-i + n-1
        找到 1 之后，和任意的最大公约数都是 1
        还需要 把 元素是 1 的个数排掉
         */
        int ans = -1;
        int n = nums.length;
        int min = n;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int gcd = 0;
            if (nums[i] == 1) {
                cnt++;
            }
            for (int j = i; j >= 0; j--) {
                gcd = gcd(gcd, nums[j]);
                if (gcd == 1) {
                    min = Math.min(min, i - j);
                    break;
                }
            }
        }
        if (min != n) {
            ans = min + n - 1;
        }
        if (cnt > 0) {
            ans = n - cnt;
        }

        return ans;
    }

    public static int gcd(int a, int b) {
        // a >= 0, b >= 0;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int gcdOptimize(int[] nums) {
        int n = nums.length, gcdAll = 0, cnt = 0;
        for (int x : nums) {
            gcdAll = gcd(gcdAll, x);
            if (x == 1) cnt++;
        }
        if (gcdAll > 1) return -1;
        if (cnt > 0) return n - cnt;

        int minSize = n;
        // 记录 [GCD，相同 GCD 闭区间的右端点]
        List<int[]> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new int[]{nums[i], i});
            // 原地去重，因为相同的 GCD 都相邻在一起
            int j = 0;
            for (int[] p : g) {
                p[0] = gcd(p[0], nums[i]);
                if (g.get(j)[0] == p[0])
                    g.get(j)[1] = p[1]; // 合并相同值，下标取最大的
                else g.set(++j, p);
            }
            g.subList(j + 1, g.size()).clear();
            if (g.get(0)[0] == 1)
                // 这里本来是 i-g.get(0)[1]+1，把 +1 提出来合并到 return 中
                minSize = Math.min(minSize, i - g.get(0)[1]);
        }
        return minSize + n - 1;
    }

    public static void main(String[] args) {
        gcdOptimize(new int[]{2, 6, 3, 4});
    }

}
