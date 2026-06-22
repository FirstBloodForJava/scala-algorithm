package com.oycm.dualweek.lc2020.No19;

import com.oycm.month2026.may.Solution_18;

public class Solution_4 {

    /**
     * 1345. <a href="https://leetcode.cn/problems/jump-game-iv/description/">跳跃游戏 IV</a>
     *
     * @param arr
     * @return
     */
    public int minJumps(int[] arr) {
        /*
        bfs，注意先标记访问，再搜索，相同值下标，第一次访问后移除，避免重复访问。
         */
        return new Solution_18().minJumps(arr);
    }

}
