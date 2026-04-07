package com.oycm.datastructure.tree.topological_sort;

import com.oycm.utils.DataCreateUtils;

import java.util.*;

public class Solution_2 {

    /**
     * 3812. <a href="https://leetcode.cn/problems/minimum-edge-toggles-on-a-tree/description/">翻转树上最少边</a> 2179
     *
     * @param n
     * @param edges  n - 1 的二维整数数组
     * @param start  start[x] 表示节点初始颜色
     * @param target target[x] 表示节点的目标颜色
     * @return
     */
    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        /*
        在一次操作中，你可以选择下标为 i 的一条边并 翻转 它的两个端点。
        edges[i] 对应的 start[u] start[v] 进行翻转，0 -> 1; 1 -> 0
        返回一个边下标数组，执行这些边对应的操作可以将 start 转换为 target, 在所有有效序列中找出 长度最短 的序列，以 升序 返回边下标。
        如果无法得到 target, 返回一个 单个元素 -1 的集合
         */
        /*
        叶子 x 的 start[x] != target[x], 则需要翻转与 x 连接的边
         */
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            g[x].add(new int[]{y, i});
            g[y].add(new int[]{x, i});
        }

        char[] s = start.toCharArray();
        char[] t = target.toCharArray();
        boolean[] revs = new boolean[n - 1];
        if (dfs(0, -1, g, s, t, revs)) { // 只剩下一个根节点需要翻转，无法操作
            return List.of(-1);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (revs[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    // 返回是否需要翻转 cur-fa 这条边
    private boolean dfs(int cur, int fa, List<int[]>[] g, char[] s, char[] t, boolean[] revs) {
        boolean rev = s[cur] != t[cur]; // cur-fa 是否要翻转
        for (int[] nextRow : g[cur]) {
            int next = nextRow[0];
            if (next != fa && dfs(next, cur, g, s, t, revs)) {
                revs[nextRow[1]] = true; // 需要翻转 next-cur
                rev = !rev; // cur 被翻转
            }
        }
        return rev;
    }

    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();
        System.out.println(solution.minimumFlips(7, DataCreateUtils.twoDimensionInts("[[0,1],[1,2],[2,3],[3,4],[3,5],[1,6]]"),
                "0011000",
                "0010001"));
    }
}

class Solution_2_tp {
    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        /*
        要想翻转次数最少，并判断是否能够成功翻转，应该先找 度为 1 的节点进行翻转，
            如果已经正确，则移除度为 1 的节点，
            如果不正确，则翻转该边后，并移除该节点
         */
        List<Integer> ans = new ArrayList<>();
        int[] degree = new int[n];
        // g[0][0] = {next, curV, edge}
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            g[x].add(new int[]{y, i});
            g[y].add(new int[]{x, i});
            degree[x]++;
            degree[y]++;
        }
        char[] cs1 = start.toCharArray();
        // 记录 度 为 1 的节点
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        int remainNodes = n;
        // 表示是否访问
        int[] visit = new int[n];
        boolean[] rev = new boolean[n - 1];
        while (remainNodes > 0) {
            int sz = queue.size();
            remainNodes -= sz;
            for (int i = 0; i < sz; i++) {
                int u = queue.poll();
                visit[u] = 1;
                for (int[] row : g[u]) {
                    if (visit[row[0]] == 0) {
                        // 度减少
                        degree[row[0]]--;
                        if (degree[row[0]] == 1) {
                            queue.offer(row[0]);
                        }
                        if (cs1[u] != target.charAt(u)) {
                            //ans.add(row[1]);
                            cs1[row[0]] = (char) (cs1[row[0]] ^ 1);
                            cs1[u] = (char) (cs1[u] ^ 1);
                            rev[row[1]] = true;
                        }
                    }
                }
            }
        }
        if (!new String(cs1).equals(target)) {
            return List.of(-1);
        }
        for (int i = 0; i < rev.length; i++) {
            if (rev[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
