package com.oycm.month2026.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_5 {

    /**
     * 3310. <a href="https://leetcode.cn/problems/remove-methods-from-project/description/">移除可疑的方法</a> 1711
     *
     * @param n
     * @param k
     * @param invocations
     * @return
     */
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        /*
        你正在维护一个项目，该项目有 n 个方法，编号从 0 到 n - 1。
        给你两个整数 n 和 k，以及一个二维整数数组 invocations，其中 invocations[i] = [ai, bi] 表示方法 ai 调用了方法 bi。
        已知如果方法 k 存在一个已知的 bug。那么方法 k 以及它直接或间接调用的任何方法都被视为 可疑方法 ，我们需要从项目中移除这些方法。
        只有当一组方法没有被这组之外的任何方法调用时，这组方法才能被移除。
        返回一个数组，包含移除所有 可疑方法 后剩下的所有方法。你可以以任意顺序返回答案。如果无法移除 所有 可疑方法，则 不 移除任何方法。
         */
        /*
        怎么确定 dfs 的入口？
        根据题意，可疑方法是 方法 k 直接调用或间接调用的方法。
        所以从 k 开始 dfs 搜索，标记所以 可疑方法。

        再次遍历，如果非可疑方法有调用 可疑方法，那么无法删除可疑方法，返回所以方法。
        否则，可疑删除可疑方法，把所以非可疑方法加入答案。
         */
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] e : invocations) {
            g[e[0]].add(e[1]);
        }
        // dfs 标记可疑方法，同时 避免 dfs 环递归无法退出
        boolean[] isSuspicious = new boolean[n];
        dfs(k, g, isSuspicious);
        List<Integer> ans = new ArrayList<>();
        for (int[] e : invocations) {
            if (!isSuspicious[e[0]] && isSuspicious[e[1]]) {
                // 非可疑 调用 可疑，无法移除

                for (int i = 0; i < n; i++) {
                   ans.add(i);
                }
                return ans;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) ans.add(i);
        }

        return ans;
    }

    public void dfs(int cur, List<Integer>[] g, boolean[] isSuspicious) {
        isSuspicious[cur] = true;
        for (Integer next : g[cur]) {
            if (isSuspicious[next]) continue;
            dfs(next, g, isSuspicious);
        }
    }

}
