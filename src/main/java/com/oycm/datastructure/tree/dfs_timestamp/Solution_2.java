package com.oycm.datastructure.tree.dfs_timestamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2 {

    /**
     * LCP 05. <a href="https://leetcode.cn/problems/coin-bonus/description/">发 LeetCoin</a>
     *
     * @param n          表示团队成员的个数
     * @param leadership [n-1][2] 的二维数组, 每个元素 [a, b] 代表 b 是 a 的下属
     * @param operations
     * @return
     */
    public int[] bonus(int n, int[][] leadership, int[][] operations) {
        /*
        operations[i][0] == 1, 给团队的一个成员（也可以是负责人）发一定数量的 LeetCoin
        operations[i][0] == 2, 给团队的一个成员（也可以是负责人），以及他/她管理的所有人（即他/她的下属、他/她下属的下属，……），发一定数量的 LeetCoin
        operations[i][0] == 3, 查询某一个成员（也可以是负责人），以及他/她管理的所有人被发到的LeetCoin之和
         */
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : leadership) {
            g[e[0]].add(e[1]);
        }
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        dfs(1, 0, g, in, out);


        List<Integer> ans = new ArrayList<>();

        FenwickTree diff = new FenwickTree(n + 2);
        for (int[] o : operations) {
            int x = o[1];
            if (o[0] == 1) {
                int coin = o[2];
                diff.rangeAdd(in[x], in[x], coin);
            } else if (o[0] == 2) {
                int coin = o[2];
                diff.rangeAdd(in[x], out[x], coin);
            } else {
                ans.add((int) (diff.rangeSum(in[x], out[x]) % mod));
            }
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }

    private static final int mod = 1000000007;
    private int clock = 0;

    private void dfs(int cur, int fa, List<Integer>[] g, int[] in, int[] out) {
        in[cur] = ++clock; // 进来的时间
        for (int next : g[cur]) {
            if (next != fa) {
                dfs(next, cur, g, in, out);
            }
        }
        out[cur] = clock; // 离开的时间
    }

    static class FenwickTree {
        long[] tree1;
        long[] tree2;

        FenwickTree(int n) {
            tree1 = new long[n];
            tree2 = new long[n];
        }

        public void update(long[] tree, int i, long val) {
            while (i < tree.length) {
                tree[i] += val;
                i += i & -i;
            }
        }

        public long pre(long[] tree, int i) {
            long res = 0;
            while (i > 0) {
                res += tree[i];
                i &= i - 1;
            }
            return res;
        }

        public void rangeAdd(int l, int r, long w) {

            update(tree1, l, w);
            update(tree1, r + 1, -w);

            update(tree2, l, w * l);
            update(tree2, r + 1, -w * (r + 1));
        }

        public long preSum(int x) {
            return (long) (x + 1) * pre(tree1, x) - pre(tree2, x);
        }

        public long rangeSum(int l, int r) {
            return (preSum(r) - preSum(l - 1)) % mod;
        }
    }

}
