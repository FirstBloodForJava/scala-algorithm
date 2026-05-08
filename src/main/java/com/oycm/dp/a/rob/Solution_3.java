package com.oycm.dp.a.rob;

public class Solution_3 {

    /**
     * 2320. <a href="https://leetcode.cn/problems/count-number-of-ways-to-place-houses/description/">统计放置房子的方式数</a> 1608
     *
     * @param n
     * @return
     */
    public int countHousePlacements(int n) {
        /*
        一条街道上共有 n * 2 个 地块 ，街道的两侧各有 n 个地块。每一边的地块都按从 1 到 n 编号。每个地块上都可以放置一所房子。
        现要求街道同一侧不能存在两所房子相邻的情况，请你计算并返回放置房屋的方式数目。
        由于答案可能很大，需要对 1e9 + 7 取余后再返回。
        注意，如果一所房子放置在这条街某一侧上的第 i 个地块，不影响在另一侧的第 i 个地块放置房子。
         */
        /*
        本质是求 n 个地块，同一侧街道不能存在两所贩子相邻的方案数
        dfs(i) 表示 i 个地块，同一侧街道不能存在两所贩子相邻的方案数
            第 i 个地块放房子，dfs(i - 2)
            第 i 个地块不放房子，dfs(i - 1)
            dfs(i) = dfs(i - 2) + dfs(i - 1)
        递归边界
            n 入口，i <= 0 return 1
            n-1 入口，i < 0 return 1
        递推 f(i) = f(i-1) + f(i-2) 可以预处理 1e4 所有的结果
         */
        init();
        return (int) (f[n] * f[n] % mod);
    }

    public static int max = 10001;
    public static long[] f = new long[max];
    public static int mod = 1000000007;
    private static boolean initialized = false;

    public void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < f.length; i++) {
            f[i] = (f[i - 1] + f[i - 2]) % mod;
        }
    }

}
