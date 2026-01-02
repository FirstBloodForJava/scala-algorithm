package com.oycm.datastructure.queue.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Solution_2 {

    /**
     * LCR 184. <a href="https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/description/">设计自助结算系统</a>
     * 队列记录 顾客通过购物车的结算过程
     */
    static class Checkout {

        Deque<Integer> shop = new ArrayDeque<>();
        Deque<Integer> max = new ArrayDeque<>(); // 单调递减的队列


        public Checkout() {

        }

        /**
         * @return 获取队列中的最高价格, 队列为 空则返回 -1
         */
        public int get_max() {
            if (shop.isEmpty()) {
                return -1;
            }
            return max.getFirst();
        }

        /**
         * 添加到商品队列的尾部
         *
         * @param value
         */
        public void add(int value) {
            shop.addLast(value);
            while (!max.isEmpty() && value > max.getLast() ) {
                max.removeLast();
            }
            max.addLast(value);
        }

        /**
         * @return 移除第一个待结算的商品价格, 队列为 空则返回 -1
         */
        public int remove() {
            if (shop.isEmpty()) {
                return -1;
            }
            if (Objects.equals(shop.getFirst(), max.getFirst())) {
                max.removeFirst();
            }
            return shop.removeFirst();
        }
    }

}
