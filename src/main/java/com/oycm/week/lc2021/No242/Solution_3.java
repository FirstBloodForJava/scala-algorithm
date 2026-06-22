package com.oycm.week.lc2021.No242;

public class Solution_3 {

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
        差分数组：
        如果 s[i] = '0' 对 可跳跃区间范围增加 1，如果差分数组还原后 f[i] > 0 且 s[i] = '0'，则可以跳跃到
         */
        int n = s.length();
        int[] d = new int[n + 1];
        d[0] = 1;
        d[1] = -1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += d[i];
            if (sum > 0 && s.charAt(i) == '0') {
                // 这里不能和 n-1 取最小值 010 minJump = 3，这里加到了 d[n-1] ，会导致计算错误
                d[Math.min(i + minJump, n)]++;
                d[Math.min(i + maxJump + 1, n)]--;
            }
        }
        return sum > 0 && s.charAt(n - 1) == '0';
    }

}
