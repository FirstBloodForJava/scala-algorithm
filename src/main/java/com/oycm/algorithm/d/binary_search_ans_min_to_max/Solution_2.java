package com.oycm.algorithm.d.binary_search_ans_min_to_max;


public class Solution_2 {

    /**
     * 2064. <a href="https://leetcode.cn/problems/minimized-maximum-of-products-distributed-to-any-store/description/">分配给商店的最多商品的最小值</a> 1886
     * <p>
     * 需要将 所有商品 分配到零售商店:
     * 一间商店 至多 只能有 一种商品 ，但一间商店拥有的商品数目可以为 任意 件。
     * 分配后，每间商店都会分配 x(>= 0) 件商品
     *
     * @param n          零售商店数量
     * @param quantities m 种商品, quantities[i] 表示 第 i 种商品的数目
     * @return max(x) 表示商店中分配商品数目的最大值, 求最小化的 max(x)
     */
    public static int minimizedMaximum(int n, int[] quantities) {
        /*
        商店至多只能一种商品, 所以最多能分配的数量是 max(quantities[i])
        设 商店数量 stores 从 [m, ∞], 商店分配数目的最小化最大值是从 [max(quantities[i]), 1] 递减, 找出 stores <= n 时, 最小的 分配数量
         */
        int l = 0, r = 0;
        for (int x : quantities) {
            r = Math.max(x, r);
        }
        if (quantities.length == n) {
            return r;
        }
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(n, quantities, mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return r;
    }

    public static boolean check(int n, int[] nums, int max) {
        int cnt = 0;
        for (int num : nums) {
            // 先上取整
            cnt += (num + max - 1) / max;
        }
        return cnt <= n;
    }

    public static void main(String[] args) {
        System.out.println(minimizedMaximum(6, new int[]{11, 6}));
        System.out.println(minimizedMaximum(7, new int[]{15, 10, 10}));
        System.out.println(minimizedMaximum(1, new int[]{100000}));
    }

}
