package com.oycm.month2026.may;

public class Solution_27 {

    /**
     * 3121. <a href="https://leetcode.cn/problems/count-the-number-of-special-characters-ii/description/">统计特殊字母的数量 II</a> 1412
     *
     * @param word
     * @return
     */
    public int numberOfSpecialChars(String word) {
        /*
        给你一个字符串 word。如果 word 中同时出现某个字母 c 的小写形式和大写形式，并且 每个 小写形式的 c 都出现在第一个大写形式的 c 之前，则称字母 c 是一个 特殊字母 。
        返回 word 中 特殊字母 的数量。
         */
        /*
        定义 -1, 0, 1, 2 四个状态
            0：表示初始状态；
            -1：0 状态出现大写字母，2 状态出现小写字母；已经是 -1；
            1：0 状态出现小写字母，1 状态出现小写字母
            2：1 状态出现大写字母，2 状态出现大写字母
         */
        int[] state = new int[27];
        int ans = 0;

        for (char c : word.toCharArray()) {
            int i = c & 31;
            if ((c & 32) > 0) {
                // 小写字母
                if (state[i] == 0) {
                    state[i] = 1;
                } else if (state[i] == 2) {
                    state[i] = -1;
                    ans--;
                }
                // -1, 1 不变
            } else {
                // 大写字母
                if (state[i] == 0) {
                    state[i] = -1;
                } else if (state[i] == 1){
                    state[i] = 2;
                    ans++;
                }
                // -1, 2 不变
            }
        }

        return ans;
    }

}
