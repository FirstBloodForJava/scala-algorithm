package com.oycm.algorithm.d.binary_search_ans_max_to_min;

import java.util.Arrays;

public class Solution_3 {

    /**
     * 2517. <a href="https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/description/">礼盒的最大甜蜜度</a> 2021
     * <p>
     * 商店组合 k 类 不同 糖果打包成礼盒出售。
     * <p>
     * 礼盒的 甜蜜度: 礼盒中任意两种糖果 价格 绝对差的最小值
     *
     * @param price
     * @param k
     * @return 求 礼盒的 最大 甜蜜度
     */
    public int maximumTastiness(int[] price, int k) {
        /*
        首先对 price 排序, 甜蜜度要越大, 礼盒的商品种类就越少才能符合要求, check(right) 不符合要求, 则右边的都不符合要求
        二分答案, 是否存在 k 个可选礼物价格符合要求
         */
        int n = price.length;
        Arrays.sort(price);
        int left = -1;
        int right = price[n - 1] - price[0] + 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(price, k, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public boolean check(int[] price, int k, int max) {
        int cnt = 1;
        int preMin = price[0];
        for (int i = 1; i < price.length; i++) {
            // 选则最小的符合要求的
            if (price[i] - preMin >= max) {
                preMin = price[i];
                cnt++;
            }
        }

        return cnt >= k;
    }

}
