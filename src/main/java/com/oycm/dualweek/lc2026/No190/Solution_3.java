package com.oycm.dualweek.lc2026.No190;

public class Solution_3 {

    /**
     * 字符对转换后字典序最大的字符串©leetcode
     *
     * @param nums
     * @return
     */
    public String[] largestString(int[] nums) {
        /*
        给你一个整数数组 nums。
        对于 nums 中的每个整数 x，首先生成一个由 x 个小写字母 'a' 组成的字符串。
        你可以执行以下操作任意次（包括零次）：
            选择两个 相邻且相同 的字母，并将它们替换为字母表中的下一个字母。
        例如，"aa" 可以替换为 "b"，"bb" 可以替换为 "c"。对 "zz" 则无法进行替换。
        对于每个 x，请你确定可以获得的 字典序最大 的字符串。
        返回一个字符串数组，其中第 i 个字符串是 nums[i] 的答案。
        在两个字符串不同处的第一个位置，如果字符串 a 包含的字母在字母表中的顺序晚于 b 中的相应字母，则字符串 a 字典序大于 字符串 b。
        如果前 min(a.length, b.length) 个字符相同，则较长的字符串字典序更大。
         */
        /*
        2 a => b
        4 a => c
        8 a => d
        最大字符为 x 最高 bit 位决定
        1 << 25 和 1e8 关系 小于
        x / 1 << 25 个 z
         */
        int n = nums.length;
        String[] ans = new String[n];
        int z = 1 << 25;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            StringBuilder sb = new StringBuilder();
            int zc = x / z;
            if (zc > 0) {
                sb.append("z".repeat(zc));
                x = x % z;
            }
            for (int j = 24; j >= 0; j--) {
                if ((x & (1 << j)) > 0) {
                    sb.append((char) ('a' + j));
                }
            }
            ans[i] = sb.toString();

        }

        return ans;
    }
}
