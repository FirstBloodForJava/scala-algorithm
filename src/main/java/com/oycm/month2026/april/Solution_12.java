package com.oycm.month2026.april;

import java.util.Arrays;

public class Solution_12 {

    /**
     * 1320. <a href="https://leetcode.cn/problems/minimum-distance-to-type-a-word-using-two-fingers/description/">二指输入的的最小距离</a> 2028
     * <p>
     * 坐标 (x1,y1) 和 (x2,y2) 之间的 距离 是 |x1 - x2| + |y1 - y2|
     *
     * @param word word.length [2, 300]
     * @return
     */
    public int minimumDistance(String word) {
        /*
        A B C D E F
        G H I J K L
        M N O P Q R
        S T U V W X
        Y Z
        键盘布局如上，计算使用两根手指（两只手一指禅输入）输入 word 的最小移动总距离。两根手指的初始位置是 0 代价。
         */
        char[] s = word.toCharArray();
        int n = s.length;

        int[][][] memo = new int[n][26][26];
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1); // -1 表示没有计算过
            }
        }

        int ans = Integer.MAX_VALUE;
        // 最后一定有一根手指在 s[n-1]，另一根手指的位置不确定，枚举
        for (int finger2 = 0; finger2 < 26; finger2++) {
            ans = Math.min(ans, dfs(n - 2, s[n - 1] - 'A', finger2, s, memo));
        }
        return ans;
    }

    private static final int[][] dis = new int[26][26];

    static {
        // 预处理两个字母的距离
        final int COLUMN = 6;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dis[i][j] = Math.abs(i / COLUMN - j / COLUMN) + Math.abs(i % COLUMN - j % COLUMN);
            }
        }
    }


    private int dfs(int i, int finger1, int finger2, char[] word, int[][][] memo) {
        if (i < 0) {
            return 0;
        }

        if (memo[i][finger1][finger2] != -1) { // 之前计算过
            return memo[i][finger1][finger2];
        }

        // 手指 1 移到 word[i]
        int w = word[i] - 'A';
        int res1 = dfs(i - 1, w, finger2, word, memo) + dis[finger1][w];

        // 手指 2 移到 word[i]
        int res2 = dfs(i - 1, finger1, w, word, memo) + dis[finger2][w];

        int res = Math.min(res1, res2);
        memo[i][finger1][finger2] = res; // 记忆化
        return res;
    }

}
