package com.oycm.month2026.march;

public class Solution_6 {

    /**
     * 1784. <a href="https://leetcode.cn/problems/check-if-binary-string-has-at-most-one-segment-of-ones/description/">检查二进制字符串字段</a> 1206
     *
     * @param s s[0] = '1'
     * @return 零个或一个由连续的 '1' 组成的字段, 返回 true; 否则 false
     */
    public boolean checkOnesSegment(String s) {
        /*
        最后一个 1 出现之前，前面没有 0 则符合要求
        简化 不包含 "01" 字符串
         */
        int lastOne = s.lastIndexOf('1');
        int firstZero = s.indexOf('0');

        return firstZero == -1 || lastOne < firstZero;
    }

}
