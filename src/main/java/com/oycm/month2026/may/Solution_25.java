package com.oycm.month2026.may;

public class Solution_25 {

    /**
     * 1871. <a href="https://leetcode.cn/problems/jump-game-vii/description/">跳跃游戏 VII</a> 1896
     *
     * @param s       s.length [1, 1e5]
     * @param minJump >= 1
     * @param maxJump [mimJump, s.length]
     * @return
     */
    public boolean canReach(String s, int minJump, int maxJump) {
        /*
        给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump。
        一开始，你在下标 0 处，且该位置的值一定为 '0'。
        当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
            i + minJump <= j <= min(i + maxJump, s.length - 1) 且 s[j] == '0'。
         */
        /*
        枚举 i 能跳跃的所有情况
            j = i + minJump, i + minJump + 1, ... , min(i + maxJump, s.length - 1) 当 s[j] = 0 时，进行跳跃，否则标记不可访问 -1
        当 maxJump = n-1，时间复杂度是 O(n^2)
         */
        /*
        通过 i 跳到 j 的时间复杂度是 n^2，能跳过 j，小于 j 的 i 要满足哪些条件呢？
        [max(j - maxJump, 0), j - minJump] 里面要存在字符 '0'，f[i] = true，用数值 1 表示，f[i] = false，用数值 0 表示，
        只有 s[j] = '0' && [max(j - maxJump, 0), j - minJump] 区间的前缀和 > 0，就能 O(1) 判断是否能到达 j
         */
        int n = s.length();
        char[] cs = s.toCharArray();
        /*boolean[] f = new boolean[n];
        f[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = i + minJump; j <= Math.min(i + maxJump, n - 1) && f[i]; j++) {
                f[j] = f[i] && cs[j] == '0';
            }
        }*/

        int[] sums = new int[n + 1];
        sums[1] = 1;
        for (int j = 1; j < n; j++) {
            sums[j + 1] += sums[j];
            if (j >= minJump && cs[j] == '0' && sums[j - minJump + 1] > sums[Math.max(j - maxJump, 0)]) {
                sums[j + 1]++;
            }

        }

        return sums[n] > sums[n - 1];
    }


}
