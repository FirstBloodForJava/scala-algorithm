package com.oycm.month2026.may;

import java.util.*;

public class Solution_8 {

    /**
     * 3629. <a href="https://leetcode.cn/problems/minimum-jumps-to-reach-end-via-prime-teleportation/">通过质数传送到达终点的最少跳跃次数</a> 2139
     *
     * @param nums
     * @return
     */
    public int minJumps(int[] nums) {
        /*
        你从下标 0 开始，目标是到达下标 n - 1。
        在任何下标 i 处，你可以执行以下操作之一：
            移动到相邻格子：跳到下标 i + 1 或 i - 1，如果该下标在边界内。
            质数传送：如果 nums[i] 是一个质数 p，你可以立即跳到任何满足 nums[j] % p == 0 的下标 j 处，且下标 j != i 。
        返回到达下标 n - 1 所需的 最少 跳跃次数。
         */
        /*
        0 -> n-1, 没有任何质数的情况下，最少需要 n-1 跳跃(0 -> 1 -> 2 -> ... -> n-1)
        应该是能构建一个图，求 0 -> n-1 的最短路径
        题解思路：
            1. 预处理每个数的质因子列表，思路同埃氏筛；
            2. 预处理质数 p，能到达的下标列表；
            3.
         */
        init();

        int n = nums.length;
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 对于 素数 nums[i] 且存在质因子 p, 则 nums[j] == p 可以跳到 i
            for (int p : primeFactors[nums[i]]) {
                // 对于质数 p，可以跳到下标 i
                groups.computeIfAbsent(p, l -> new ArrayList<>()).add(i);
            }
        }

        int ans = 0;
        boolean[] vis = new boolean[n];
        vis[0] = true;
        List<Integer> q = List.of(0);

        while (true) {
            List<Integer> tmp = q;
            q = new ArrayList<>();
            for (int i : tmp) {
                if (i == n - 1) {
                    return ans;
                }
                List<Integer> idx = groups.computeIfAbsent(nums[i], l -> new ArrayList<>());
                idx.add(i + 1);
                if (i > 0) {
                    idx.add(i - 1);
                }
                for (int j : idx) {
                    /*
                    i 跳到 j
                    前后跳跃/质数跳跃
                     */
                    if (!vis[j]) {
                        vis[j] = true;
                        q.add(j);
                    }
                }
                /*
                避免重复访问下标列表
                如果前面访问过的下标能到达 n-1，再通过该点访问肯定不是最优解
                 */
                idx.clear();
            }
            ans++;
        }
    }

    private static final int MX = 1_000_001;
    private static final List<Integer>[] primeFactors = new ArrayList[MX];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        Arrays.setAll(primeFactors, l -> new ArrayList<>());
        // 预处理每个数的质因子列表，思路同埃氏筛
        for (int i = 2; i < MX; i++) {
            if (primeFactors[i].isEmpty()) {
                // i 是质数
                for (int j = i; j < MX; j += i) {
                    // j 有质因子 i
                    primeFactors[j].add(i);
                }
            }
        }
    }

}
