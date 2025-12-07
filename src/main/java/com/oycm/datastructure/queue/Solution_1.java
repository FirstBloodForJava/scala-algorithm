package com.oycm.datastructure.queue;

import java.util.Deque;
import java.util.ArrayDeque;

public class Solution_1 {


}

/**
 * 933. 最近的请求次数 1338
 * https://leetcode.cn/problems/number-of-recent-calls/description/
 */
class RecentCounter {

    Deque<Integer> queue = new ArrayDeque<>();

    public RecentCounter() {

    }

    /**
     * 返回 [t-3000, t] 的历史次数
     * @param t
     * @return
     */
    public int ping(int t) {
        while (!queue.isEmpty() && queue.peekFirst() < t - 3000){
            queue.pollFirst();
        }
        queue.addLast(t);
        return queue.size();
    }
}
