package com.oycm.datastructure.tree.top_down_dfs;

import com.oycm.utils.DataCreateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_3 {

    /**
     * 1443. <a href="https://leetcode.cn/problems/minimum-time-to-collect-all-apples-in-a-tree/description/">收集树上所有苹果的最少时间</a> 1683
     *
     * @param n        n 个节点的无向树，节点编号为 0 到 n-1
     * @param edges    {from, to}, 表示有一条边连接 from 和 to
     * @param hasApple hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果
     * @return 通过树上的一条边，需要花费 1 秒钟。你从 节点 0 出发，请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        /*

         */
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] row : edges) {
            g[row[0]].add(row[1]);
            g[row[1]].add(row[0]);

        }
        return dfs(0, -1, g, hasApple);
    }

    private int dfs(int x, int parent, List<Integer>[] g, List<Boolean> hasApple) {
        int res = 0;
        for (Integer next : g[x]) {
            if (next == parent) continue;
            int child = dfs(next, x, g, hasApple);
            if (child > 0 || hasApple.get(next)) {
                res += child + 2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        /*System.out.println(new Solution_3().minTime(7,
                DataCreateUtils.twoDimensionInts("[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]"),
                DataCreateUtils.toListBoolean("[false,false,true,false,true,true,false]")));

        System.out.println(new Solution_3().minTime(7,
                DataCreateUtils.twoDimensionInts("[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]"),
                DataCreateUtils.toListBoolean("[false,false,true,false,false,true,false]")));*/

        System.out.println(new Solution_3().minTime(4,
                DataCreateUtils.twoDimensionInts("[[0,2],[0,3],[1,2]]"),
                DataCreateUtils.toListBoolean("[false,true,false,false]")));
    }


}
