package com.oycm.month2026.may;

import java.util.ArrayList;
import java.util.List;

public class Solution_17 {

    /**
     * 1306. <a href="https://leetcode.cn/problems/jump-game-iii/description/">跳跃游戏 III</a> 1397
     *
     * @param arr
     * @param start
     * @return
     */
    public boolean canReach(int[] arr, int start) {
        /*
        这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
        请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
        注意，不管是什么情况下，你都无法跳到数组之外。
         */
        /*
        dfs 思路：根据 i 建一个有向图 i -> i+arr[i], i -> i-arr[i]
            i+arr[i] <= n-1;
            i-arr[i] >= 0;
            arr[i] != 0;
         */
        boolean[] vis = new boolean[arr.length];
        return dfs(start, arr, vis);
    }

    public boolean dfs(int i, int[] arr, boolean[] vis) {
        if (i < 0 || i > arr.length || vis[i]) return false;

        if (arr[i] == 0) return true;
        vis[i] = true;
        return dfs(i + arr[i], arr, vis) || dfs(i - arr[i], arr, vis);
    }

    public boolean bfs(int[] arr, int start) {
        /*
        bfs 思路，初始化 q = [start]，一个数组标记 i 是否已经访问过
            arr[i] 如果等于 0，则返回 true；
            arr[i] != 0，标记 i 访问，将 i+arr[i] 和 i-arr[i] 加入下一个待访问的点
         */
        List<Integer> q = new ArrayList<>();
        int n = arr.length;
        int[] visited = new int[n];
        q.add(start);
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int i : q) {
                if (arr[i] == 0) {
                    return true;
                }
                // 标记访问了，避免环无法结束循环
                visited[i] = 1;
                int next = i + arr[i];
                int pre = i - arr[i];
                if (next < n && visited[next] == 0) {
                    t.add(next);
                }
                if (pre >= 0 && visited[pre] == 0) {
                    t.add(pre);
                }
            }
            q = t;
        }

        return false;
    }
}
