package com.leetcode.interview_question.e;

public class Solution_3 {

    /**
     * 面试题 05.03. <a href="https://leetcode.cn/problems/reverse-bits-lcci/description/">翻转数位</a>
     *
     * @param num
     * @return
     */
    public int reverseBits(int num) {
        /*
        给定一个 32 位整数 num，你可以将一个数位从 0 变为 1。请编写一个程序，找出你能够获得的最长的一串 1 的长度。
         */
        /*
        可以枚举每个 bit 判断是否能修改，以及修改后能获得的最大长度。
        需要一个标记，是否已修改，修改前连续 1 长度
         */
        /*
        把 num 反转。问题转换成求 连续 0 的最大长度，最多修改一次最长的连续 0 长度。
        没有什么可优化的
         */
        if (num == 0) return 1;
        int ans = 0;
        // 出现 0 之前连续 1 长度
        int pre = 0;
        // 表示连续 1 长度，包含修改后的长度
        int cur = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & 1) > 0) {
                cur++;
                pre++;
            } else {
                cur = pre + 1;
                pre = 0;
            }
            ans = Math.max(ans, cur);
            // 去掉低位
            num >>= 1;
        }

        return ans;
    }

}
