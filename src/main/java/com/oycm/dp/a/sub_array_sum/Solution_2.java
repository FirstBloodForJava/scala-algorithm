package com.oycm.dp.a.sub_array_sum;

public class Solution_2 {

    /**
     * 2606. <a href="https://leetcode.cn/problems/find-the-substring-with-maximum-cost/description/">找到最大开销的子字符串</a> 1422
     *
     * @param s
     * @param chars 只包含小写英文字母，且 互不相同 。
     * @param vals  vals[i] [-1000, 1000]
     * @return
     */
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        /*
        一个字符串 s ，一个字符 互不相同 的字符串 chars 和一个长度与 chars 相同的整数数组 vals 。
        子字符串的开销 是一个子字符串中所有字符对应价值之和。空字符串的开销是 0 。
        字符的价值 定义如下：
            如果字符不在字符串 chars 中，那么它的价值是它在字母表中的位置（下标从 1 开始）。'a' 的价值为 1 ，'b' 的价值为 2 ，以此类推，'z' 的价值为 26 。
            否则，如果这个字符在 chars 中的位置为 i ，那么它的价值就是 vals[i] 。
        返回字符串 s 的所有子字符串中的最大开销。
         */
        /*
        创建一个长为 26 的数组，记录所有字符的价值
        本质是求 买卖股票的最佳操作，且可以不操作
         */
        int[] cs = new int[26];
        for (int i = 0; i < cs.length; i++) {
            // 先填默认值，后遍历 chars 时覆盖默认值
            cs[i] = i + 1;
        }
        for (int i = 0; i < vals.length; i++) {
            cs[chars.charAt(i) - 'a'] = vals[i];
        }

        // 空字符为 0，可以不操作
        int ans = 0;
        int sum = 0;
        int minSum = 0;
        for (char c : s.toCharArray()) {
            c -= 'a';
            sum += cs[c];
            ans = Math.max(ans, sum - minSum);
            minSum = Math.min(sum, minSum);
        }

        return ans;
    }

}
