package com.oycm.datastructure.stack.monotonic.basic;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_5 {

    /**
     * 901. <a href="https://leetcode.cn/problems/online-stock-span/description/">股票价格跨度</a> 1709
     * 当日股票价格的 跨度: 股票价格小于或等于今天价格的最大连续日数(包括今天)
     */
    static class StockSpanner {
        /*
        二元组, [股票价格, 小于当前股票价格的连续天数]
         */
        Deque<int[]> stack = new ArrayDeque<>();
        public StockSpanner() {

        }

        public int next(int price) {
            int cnt = 1;
            while (!stack.isEmpty() && price >= stack.peek()[0]) {
                cnt += stack.pop()[1];
            }
            stack.push(new int[]{price, cnt});
            return cnt;
        }
    }
}