package com.oycm.datastructure.tree.top_down_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_1 {

    /**
     * 1376. <a href="https://leetcode.cn/problems/time-needed-to-inform-all-employees/description/">通知所有员工所需的时间</a> 1561
     *
     * @param n
     * @param headID     总负责人
     * @param manager    manager[i] 是第 i 名员工的直属负责人
     * @param informTime 第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属, 如果员工 i 没有下属，informTime[i] == 0
     * @return
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        /*
        第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属（也就是说在 informTime[i] 分钟后，他的所有直属下属都可以开始传播这一消息）
        每层取最大的通知时间
        先建树, 再 自顶向下 dfs 搜索
         */
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int i = 0; i < manager.length; i++) {
            if (i != headID) {
                g[manager[i]].add(i);
            }
        }
        dfs(headID, informTime, g, 0);
        return ans;
    }

    int ans = 0;

    public void dfs(int i, int[] informTime, List<Integer>[] g, int pathSum) {
        pathSum += informTime[i];
        ans = Math.max(ans, pathSum);
        for (Integer next : g[i]) {
            dfs(next, informTime, g, pathSum);
        }
    }

}
