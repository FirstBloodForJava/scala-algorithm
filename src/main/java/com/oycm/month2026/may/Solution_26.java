package com.oycm.month2026.may;

public class Solution_26 {

    /**
     * 3120. <a href="https://leetcode.cn/problems/count-the-number-of-special-characters-i/description/">统计特殊字母的数量 I</a> 1206
     *
     * @param word
     * @return
     */
    public int numberOfSpecialChars(String word) {
        /*
        给你一个字符串 word。如果 word 中同时存在某个字母的小写形式和大写形式，则称这个字母为 特殊字母。
        返回 word 中 特殊字母 的数量。
         */
        /*
        aaA 只算一次，分类讨论：
            如果当前字符时小写字母，查找前面字符是否存在大写字母，存在则答案更新（两个字母出现次数更新无穷小），更新小写字母出现次数；
            如果当前字符时大写字母，查找前面字符是否存在小写字母，存在则答案更新（两个字母出现次数更新无穷小），更新大写字母出现次数；
        不更新，遍历完所有字符后，遍历 'A' - 'Z' 如果对应 'a' - 'z' 的出现次数都大于 0，出现次数增加
         */
        int[] cnt = new int[128];
        int ans = 0;
        for (char c : word.toCharArray()) {
            cnt[c]++;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            if (cnt[i] > 0 && cnt[i + 32] > 0) ans++;
        }

        return ans;
    }

}
