package com.oycm.datastructure.tree.diameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_1 {

    /**
     * 2246. <a href="https://leetcode.cn/problems/longest-path-with-different-adjacent-characters/description/">相邻字符不同的最长路径</a> 2126
     * <p>
     * 一棵 树（即一个连通、无向、无环图），根节点是节点 0 ，这棵树由编号从 0 到 n - 1 的 n 个节点组成
     *
     * @param parent 长度为 n, parent[i] 是节点 i 的父节点, 所以 parent[0] == -1
     * @param s      长度为 n, 其中 s[i] 表示分配给节点 i 的字符
     * @return 找出路径上任意 一对相邻节点 都没有分配到相同字符的 最长路径 长度（节点个数）
     */
    public int longestPath(int[] parent, String s) {
        /*
        题解思路: 枚举节点 cur 所有子树 next, 获取每个 next 的长度;
        如果 s[cur] != s[next] 更新 ans = max(ans, len + maxLen), 以及 cur 子树最长路径 maxLen
        当前子树遍历完成后，返回 maxLen
         */
        char[] cs = s.toCharArray();
        int n = parent.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }
        dfs(0, g, cs);
        return ans + 1;
    }

    int ans = 0;

    public int dfs(int cur, List<Integer>[] g, char[] cs) {
        int maxLen = 0;
        for (int next : g[cur]) {
            // 遍历每个节点的最长路径
            int len = dfs(next, g, cs) + 1;
            // 相邻节点是不同字符, cur != next
            if (cs[cur] != cs[next]) {
                ans = Math.max(ans, maxLen + len);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
}
