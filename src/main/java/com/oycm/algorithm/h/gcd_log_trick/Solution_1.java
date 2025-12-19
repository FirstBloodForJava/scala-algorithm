package com.oycm.algorithm.h.gcd_log_trick;


import java.util.ArrayList;
import java.util.List;

public class Solution_1 {

    /**
     * 2447. <a href="https://leetcode.cn/problems/number-of-subarrays-with-gcd-equal-to-k/description/">最大公因数等于 K 的子数组数目</a> 1603
     *
     * @param nums
     * @param k
     * @return 求 nums 中 连续子数组 最大公约数 是 k 的 个数
     */
    public int subarrayGCD(int[] nums, int k) {
        /*
        子数组越来越长 最大公约数会越来越小, 具有单调性, 可以使用滑动窗口解决
        nums[r] == k, ans++;
        nums[l, r] > k r 继续扩大
        nums[l, r] == k, ans++, r 继续右移
        nums[l, r] < k 时, 移除 nums[l] 时, nums[l+1, r] 的最大公约数怎么求?
         */
        /*
        暴力做法 n^2
        外层循环 i = 0, 从左到右; 内存循环 j = i-1, 从右到左, nums[j] = gcd(nums[j], nums[i]);
         */
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x == k) {
                ans++;
            }
            for (int j = i - 1; j >= 0; j--) {
                nums[j] = gcd(nums[j], x);
                if (nums[j] == k) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public static int gcd(int a, int b) {
        // a > 0, b > 0;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int violenceOptimize(int[] nums, int k) {
        /*
        题解：暴力优化 gcd 不是 k 的 倍数, 提前结束循环
         */
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int gcd = 0;
            for (int j = i; j >= 0 ; j--) {
                gcd = gcd(gcd, nums[j]);
                if (gcd % k > 0) {
                    break;
                }
                if (gcd == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int gcdOptimize(int[] nums, int k) {
        // todo 不太懂
        int ans = 0;
        // 存储 [GCD，相同 GCD 区间的右端点]
        List<int[]> a = new ArrayList<>();
        int i0 = -1;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            // 保证后续求的 GCD 都是 k 的倍数
            if (x % k != 0) {
                a.clear();
                i0 = i;
                continue;
            }

            a.add(new int[]{x, i});
            // 原地去重，因为相同的 GCD 都相邻在一起
            int j = 0;
            for (int idx = 0; idx < a.size(); idx++) {
                int[] p = a.get(idx);
                p[0] = gcd(p[0], x);
                if (a.get(j)[0] != p[0]) {
                    j++;
                    a.set(j, p);
                } else {
                    a.get(j)[1] = p[1];
                }
            }

            // 删除多余的元素
            while (a.size() > j + 1) {
                a.remove(a.size() - 1);
            }

            if (a.get(0)[0] == k) { // a[0][0] >= k
                ans += a.get(0)[1] - i0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(gcd(1, 3));
    }
}
