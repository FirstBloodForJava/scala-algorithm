package com.oycm.datastructure.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_3 {

    /**
     * 649. <a href="https://leetcode.cn/problems/dota2-senate/description/">Dota2 参议院</a>
     * <p>
     * 每一轮可以行使两项权利的一项：
     * <p>
     * - 剥夺一名参议员的权利：一名参议员可以使另一名参议员在本轮及所有后续轮次中失去所有权利。
     * <p>
     * - 宣布胜利：如果参议员发现有权利投票的参议员都是 同一个阵营的
     *
     * @param senate R 表示 Radiant, D 表示 Dire
     * @return 求哪一方能获胜 Radiant Dire
     */
    public String predictPartyVictory(String senate) {
        /*
        维护 一个 R, D 的队列，看每次谁先投票
        RDDR 第一轮 RD 被投出
         */
        int n = senate.length();
        Queue<Integer> r = new LinkedList<>();
        Deque<Integer> d = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                r.add(i);
            } else {
                d.add(i);
            }
        }
        while (!r.isEmpty() && !d.isEmpty()) {
            int r1 = r.poll();
            int d1 = d.poll();
            // 当前谁先有投票权，先票，然后下一轮采可以投
            if (r1 < d1) {
                r.add(r1 + n);
            } else {
                d.add(d1 + n);
            }
        }

        return r.size() > d.size() ? "Radiant" : "Dire";
    }
}
