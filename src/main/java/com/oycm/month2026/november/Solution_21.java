package com.oycm.month2026.november;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_21 {

    /**
     * 3331. <a href="https://leetcode.cn/problems/find-subtree-sizes-after-changes/description/">修改后子树的大小</a> 2046
     *
     * @param parent
     * @param s
     * @return
     */
    public int[] findSubtreeSizes(int[] parent, String s) {
        /*
        给你一棵 n 个节点且根节点为编号 0 的树，节点编号为 0 到 n - 1 。
        这棵树用一个长度为 n 的数组 parent 表示，其中 parent[i] 是第 i 个节点的父亲节点的编号。
        由于节点 0 是根，parent[0] == -1 。
        给你一个长度为 n 的字符串 s ，其中 s[i] 是节点 i 对应的字符。
        对于节点编号从 1 到 n - 1 的每个节点 x ，我们 同时 执行以下操作 一次 ：
            找到距离节点 x 最近 的祖先节点 y ，且 s[x] == s[y] 。
            如果节点 y 不存在，那么不做任何修改。
            否则，将节点 x 与它父亲节点之间的边 删除 ，在 x 与 y 之间连接一条边，使 y 变为 x 新的父节点。
        请你返回一个长度为 n 的数组 answer ，其中 answer[i] 是 最终 树中，节点 i 为根的 子树 的 大小 。
         */
        int n = parent.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }
        int[] size = new int[n];
        int[] ancestor = new int[26];
        Arrays.fill(ancestor, -1);
        dfs(0, g, s.toCharArray(), ancestor, size);
        return size;
    }

    private void dfs(int cur, List<Integer>[] g, char[] cs, int[] ancestor, int[] size) {
        size[cur] = 1;
        int curIdx = cs[cur] - 'a';
        int old = ancestor[curIdx];
        ancestor[curIdx] = cur;
        for (int next : g[cur]) {
            dfs(next, g, cs, ancestor, size);
            int ancIdx = ancestor[cs[next] - 'a'];
            // ancIdx 小于 0，则说明 next 没有符合要求的祖先节点
            // andIdx 存在符合要求的祖先节点，子节点连接到 ancIdx 节点
            size[ancIdx < 0 ? cur : ancIdx] += size[next];
        }
        ancestor[curIdx] = old;
    }

}
