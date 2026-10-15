package com.oycm.month2026.october;

public class Solution_14 {

    /**
     * 3723. <a href="https://leetcode.cn/problems/maximize-sum-of-squares-of-digits/description/">数位平方和的最大值</a> 1537
     *
     * @param num
     * @param sum
     * @return
     */
    public String maxSumOfSquares(int num, int sum) {
        /*
        给你两个 正整数 num 和 sum。
        如果一个正整数 n 满足以下两个条件，则称其为 好整数 ：
            n 的位数 恰好 为 num。
            n 的各位数字之和 恰好 为 sum。
        一个 好整数 n 的 分数 定义为 n 的各位数字的平方和。
        返回一个 字符串 ，表示能获得 最大分数 的 好整数 n。如果有多个可能的整数，返回 最大 的那个。如果不存在这样的整数，返回一个空字符串。
         */
        // 最大的数位和小于 sum，不存在符合要求的数
        if (num * 9 < sum) {
            return "";
        }
        int[] ans = new int[num];
        // 最高位从就9 开始填
        for (int i = 0; sum > 0; i++) {
            if (sum > 9) {
                ans[i] = 9;
                sum -= 9;
            } else {
                ans[i] = sum;
                sum = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int x : ans) {
            sb.append(x);
        }
        return sb.toString();
    }

}
