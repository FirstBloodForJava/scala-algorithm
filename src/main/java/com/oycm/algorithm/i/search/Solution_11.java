package com.oycm.algorithm.i.search;

import java.util.*;

public class Solution_11 {

    /**
     * 756. <a href="https://leetcode.cn/problems/pyramid-transition-matrix/description/">金字塔转换矩阵</a> 1990
     *
     * @param bottom 金字塔的底部
     * @param allowed
     * @return
     */
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        /*
        积木堆成金字塔，每个块都有一个颜色，用一个字母表示。每一行的块比它下面的行 少一个块 ，并且居中。
        一个三角形的图案由 两个块 和叠在上面的 单个块 组成。
        模式是以三个字母字符串的列表形式 allowed 给出的，其中模式的前两个字符分别表示左右底部块，第三个字符表示顶部块。
         */
        /*
        bottom.length = n;
        第几行记为 i，第几列记为 j
        i,0 填什么取决于 (i+1, 0), (i+1, 1) allowed 前缀是否存在
        */

        List<Character>[][] groups = new ArrayList[6][6];
        for (List<Character>[] row : groups) {
            Arrays.setAll(row, l -> new ArrayList<>());
        }
        for (String S : allowed) {
            char[] s = S.toCharArray();
            groups[s[0] - 'A'][s[1] - 'A'].add(s[2]);
        }

        int n = bottom.length();
        char[][] pyramid = new char[n][];
        for (int i = 0; i < n - 1; i++) {
            pyramid[i] = new char[i + 1];
        }
        pyramid[n - 1] = bottom.toCharArray();

        Set<String> vis = new HashSet<>(); // 访问标记
        // n 行, n-1 是底部，从第 n-2 行 开始填
        return dfs(n - 2, 0, pyramid, vis, groups);
    }

    /**
     *
     * @param i 行 index
     * @param j 列 index
     * @param pyramid 金字塔，初始哈玩左对其
     * @param vis
     * @param groups
     * @return
     */
    private boolean dfs(int i, int j, char[][] pyramid, Set<String> vis, List<Character>[][] groups) {
        if (i < 0) {
            return true;
        }

        if (j == i + 1) {
            String row = new String(pyramid[i]);
            if (!vis.add(row)) { // 这一行之前填过一模一样的，继续填，没能填到塔顶
                return false; // 直接返回
            }
            return dfs(i - 1, 0, pyramid, vis, groups);
        }

        for (char top : groups[pyramid[i + 1][j] - 'A'][pyramid[i + 1][j + 1] - 'A']) {
            pyramid[i][j] = top;
            // 填第 i 行的 j+1 列
            if (dfs(i, j + 1, pyramid, vis, groups)) {
                return true;
            }
        }
        return false;
    }

}

class Solution_756 {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        /*
        位运算映射 A-F, 映射为数字 1-6
         */
        List<Integer>[][] groups = new ArrayList[7][7];
        for (List<Integer>[] row : groups) {
            Arrays.setAll(row, l -> new ArrayList<>());
        }
        for (String S : allowed) {
            char[] s = S.toCharArray();
            // A~F -> 1~6
            groups[s[0] & 31][s[1] & 31].add(s[2] & 31);
        }

        char[] cs = bottom.toCharArray();
        int n = bottom.length();
        int[] pyramid = new int[n];
        for (int i = 0; i < n; i++) {
            // ABC -> (1 << 0) | (2 << 3) | (3 << 6) 来表示一行字母
            pyramid[n - 1] |= (cs[i] & 31) << (i * 3);
        }

        boolean[] vis = new boolean[1 << ((n - 1) * 3)];

        return dfs(n - 2, 0, pyramid, vis, groups);
    }

    private boolean dfs(int i, int j, int[] pyramid, boolean[] vis, List<Integer>[][] groups) {
        if (i < 0) {
            return true;
        }

        if (vis[pyramid[i]]) {
            return false;
        }

        if (j == i + 1) {
            vis[pyramid[i]] = true;
            return dfs(i - 1, 0, pyramid, vis, groups);
        }

        for (int top : groups[pyramid[i + 1] >> (j * 3) & 7][pyramid[i + 1] >> ((j + 1) * 3) & 7]) {
            /*
            再优化，当前字符和左侧字符无法得到下一层，提前跳过
             */
            if (j > 0 && groups[pyramid[i] >> ((j - 1) * 3) & 7][top].isEmpty()) {
                continue;
            }
            pyramid[i] &= ~(7 << (j * 3)); // 清除之前填的字母，等价于 pyramid[i][j] = 0
            pyramid[i] |= top << (j * 3); // 等价于 pyramid[i][j] = top
            if (dfs(i, j + 1, pyramid, vis, groups)) {
                return true;
            }
        }
        return false;
    }

}
