package com.oycm.dualweek.lc2021.No44;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_2 {

    /**
     * 1733. <a href="https://leetcode.cn/problems/minimum-number-of-people-to-teach/description/">需要教语言的最少人数</a>
     *
     * @param n
     * @param languages
     * @param friendships
     * @return
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        /*
        在一个由 m 个用户组成的社交网络里，我们获取到一些用户之间的好友关系。两个用户之间可以相互沟通的条件是他们都掌握同一门语言。
        给你一个整数 n ，数组 languages 和数组 friendships ，它们的含义如下：
            总共有 n 种语言，编号从 1 到 n 。
            languages[i] 是第 i 位用户掌握的语言集合。
            friendships[i] = [ui, vi] 表示 ui 和 vi 为好友关系。
        你可以选择 一门 语言并教会一些用户，使得所有好友之间都可以相互沟通。请返回你 最少 需要教会多少名用户。
        请注意，好友关系没有传递性，也就是说如果 x 和 y 是好友，且 y 和 z 是好友， x 和 z 不一定是好友。
         */
        /*
        暴力思路：枚举所有用户之间用 [1, n] 语言沟通，需要教会的人数，求最小值。
         */
        int m = languages.length;
        boolean[][] learned = new boolean[m][n + 1];
        for (int i = 0; i < languages.length; i++) {
            for (int x : languages[i]) {
                learned[i][x] = true;
            }
        }
        // 标记哪些人需要学习
        List<int[]> todoLearn = new ArrayList<>();
        outer:
        for (int[] fs : friendships) {
            int u = fs[0] - 1, v = fs[1] - 1;
            for (int x : languages[u]) {
                if (learned[v][x]) continue outer;
            }
            todoLearn.add(fs);
        }

        int ans = m;
        for (int k = 1; k <= n; k++) {
            // 枚举学 k 语言的人数
            Set<Integer> set = new HashSet<>();
            for (int[] fs : todoLearn) {
                int u = fs[0] - 1, v = fs[1] - 1;
                if (!learned[u][k]) set.add(u);
                if (!learned[v][k]) set.add(v);
            }
            ans = Math.min(ans, set.size());
        }

        return ans;
    }

}
