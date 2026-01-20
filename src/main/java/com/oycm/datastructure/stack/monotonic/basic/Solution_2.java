package com.oycm.datastructure.stack.monotonic.basic;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_2 {

    /**
     * 1475. <a href="https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop/description/">商品折扣后的最终价格</a> 1212
     *
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        // 维持一个单调递增的栈
        Deque<Integer> stack = new ArrayDeque<>();
        for (int j = 0; j < prices.length; j++) {
            while (!stack.isEmpty() && prices[j] <= prices[stack.peek()]) {
                int i = stack.pop();
                prices[i] = prices[i] - prices[j];
            }
            stack.push(j);
        }
        return prices;
    }

}
