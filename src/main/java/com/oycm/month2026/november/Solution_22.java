package com.oycm.month2026.november;

public class Solution_22 {

    /**
     * 2124.<a href="https://leetcode.cn/problems/check-if-all-as-appears-before-all-bs/description/">检查是否所有 A 都在 B 之前</a> 1202
     *
     * @param s
     * @return
     */
    public boolean checkString(String s) {
        /*
        给你一个 仅 由字符 'a' 和 'b' 组成的字符串  s 。如果字符串中 每个 'a' 都出现在 每个 'b' 之前，返回 true ；否则，返回 false 。
         */
        /*
        不出现 ba 即可
         */
        return !s.contains("ba");
    }

}
