package com.oycm.month2026.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_18 {

    /**
     * 1345. <a href="https://leetcode.cn/problems/jump-game-iv/description/">跳跃游戏 IV</a>
     *
     * @param arr
     * @return
     */
    public int minJumps(int[] arr) {
        /*
        给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
        每一步，你可以从下标 i 跳到下标 i + 1 、i - 1 或者 j ：
            i + 1 需满足：i + 1 < arr.length
            i - 1 需满足：i - 1 >= 0
            j 需满足：arr[i] == arr[j] 且 i != j
        返回到达数组最后一个元素的下标处所需的 最少操作次数。
         */
        /*
        bfs 搜索，hash 表统计所有相同 value 的下标集合
        注意：当访问过相同值的所有下标时，需要从相同值 hash 表中移除该 key，避免后续的重复无效访问。
         */
        int n = arr.length;
        int cnt = 0;
        Map<Integer, List<Integer>> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idxMap.computeIfAbsent(arr[i], l -> new ArrayList<>()).add(i);
        }
        List<Integer> q = List.of(0);
        boolean[] vis = new boolean[n];
        vis[0] = true;
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int i : q) {
                if (i == n - 1) return cnt;
                if (i > 0 && !vis[i - 1]) {
                    vis[i - 1] = true;
                    t.add(i - 1);
                }
                if (!vis[i + 1]) {
                    vis[i + 1] = true;
                    t.add(i + 1);
                }
                List<Integer> list = idxMap.get(arr[i]);
                if (list == null) continue;;
                for (int j : list) {
                    if (!vis[j]) {
                        vis[j] = true;
                        t.add(j);
                    }
                }
                idxMap.remove(arr[i]);
            }
            q = t;
            cnt++;
        }

        return cnt;
    }

}
