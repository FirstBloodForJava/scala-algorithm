package com.oycm.algorithm.g.diff_one_basic;

public class Solution_4 {

    /**
     * <a href="https://leetcode.cn/problems/living-people-lcci/description/">面试题 16.10. 生存人数</a>
     *
     * @param birth 第 i 个人的出生年份为 birth[i]
     * @param death 死亡年份为 death[i]
     * @return
     */
    public int maxAliveYear(int[] birth, int[] death) {
        int[] diff = new int[2001];
        for (int i = 0; i < birth.length; i++) {
            diff[birth[i]]++;
            diff[death[i] + 1]--;
        }
        int cnt = 0;
        int ans = 0;
        for (int i = 1900; i < diff.length; i++) {
            diff[i] += diff[i - 1];
            if (diff[i] > cnt) {
                ans = i;
                cnt = diff[i];
            }
        }
        return ans;

    }

}
