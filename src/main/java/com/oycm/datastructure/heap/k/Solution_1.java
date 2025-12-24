package com.oycm.datastructure.heap.k;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution_1 {

    /**
     * 264. <a href="https://leetcode.cn/problems/ugly-number-ii/description/">丑数 II</a>
     * <p>
     * 丑数 就是质因子只包含 2、3 和 5 的正整数
     *
     * @param n
     * @return 返回第 n 个 丑数
     */
    public int nthUglyNumber(int n) {
        /*
        用 大顶堆 记录 丑数, 堆 大小等于 n 时, 堆顶元素就是答案
        怎么判断一个数 是否为 2, 3, 5 的 n 次幂等
        通过移除最小元素构造新的 最小丑数 入堆
         */
        PriorityQueue<Long> min = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        set.add(1L);
        min.add(1L);
        int ans = 0;
        int[] ints = new int[]{2, 3, 5};
        for (int i = 0; i < n; i++) {
            long head = min.poll();
            ans = (int) head;
            for (int anInt : ints) {
                long next = head * anInt;
                if (set.add(next)) {
                    min.add(next);
                }
            }
        }

        return ans;
    }

}
