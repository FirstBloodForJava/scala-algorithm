package com.oycm.week.lc2026.No519;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 使每个元素变为回文数的最少操作次数
     *
     * @param nums
     * @return
     */
    public long minOperations(int[] nums) {
        /*
        给你一个整数数组 nums。
        一次操作中，你可以选择一个下标 i，并将 nums[i] 增加 2 或减少 2。
        返回将 nums 中的每个元素都变为正回文整数所需的最少操作次数。不同元素可以变成不同的回文整数。
        如果一个整数正着读和反着读都相同，则称其为回文整数。例如，121 是回文整数，而 123 不是。
         */
        /*
        x 增加/减少 2，不改变原有数的奇偶性
        预处理 [1, 1e9] 所有回文整数
         */
        long ans = 0;
        init();

        for (int x : nums) {
            List<Integer> list = x % 2 == 0 ? even : odd;
            // 大于等于 x 的第一个回文数
            int r = lowerBound(list, x);
            // 小于等于 x 的第一个回文数
            int l = upperBound(list, x);
            ans += Math.min((list.get(r) - x) / 2, (x - list.get(l)) / 2);
        }

        return ans;
    }

    public static boolean initialed = false;
    public static List<Integer> odd = new ArrayList<>();
    public static List<Integer> even = new ArrayList<>();

    public void init() {
        if (initialed) {
            return;
        }
        initialed = true;
        for (int digits = 1; digits <= 9; digits++) {
            int half = (digits + 1) / 2;
            for (int h = (int) Math.pow(10, half - 1); h < (int) Math.pow(10, half); h++) {
                int p = buildPalindrome(h, digits);
                if (p % 2 == 0) {
                    even.add(p);
                } else {
                    odd.add(p);
                }
            }
        }
        even.add(2000000002);
        odd.add(1000000001);
    }

    private static int buildPalindrome(int h, int digits) {
        int p = h;
        int mirror = (digits % 2 == 0) ? h : h / 10;
        while (mirror > 0) {
            p = p * 10 + mirror % 10;
            mirror /= 10;
        }
        return p;
    }

    public int lowerBound(List<Integer> list, int target) {
        int left = -1, right = list.size();
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    public int upperBound(List<Integer> list, int target) {
        int left = -1, right = list.size();
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (list.get(mid) <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
