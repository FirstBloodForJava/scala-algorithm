package com.oycm.week.lc2024.No394;

public class Solution_2 {

    /**
     * 3121. <a href="https://leetcode.cn/problems/count-the-number-of-special-characters-ii/description/">统计特殊字母的数量 II</a> 1412
     *
     * @param word
     * @return
     */
    public int numberOfSpecialChars(String word) {
        /*
        给你一个字符串 word。如果 word 中同时出现某个字母 c 的小写形式和大写形式，并且 每个 小写形式的 c 都出现在第一个大写形式的 c 之前，则称字母 c 是一个 特殊字母。
         */
        /*
        aaAa 不合法，分类讨论：
            如果当前字符是小写字母，更新小写字母出现次数；
            如果当前字符是大写字母，查找前面字符是否存在小写字母，存在则把小写字母次数置为无穷小；否则，置为 无穷小 + 1
        也可以使用集合来做
         */
        int[] cnt = new int[2];
        int invalid = 0;
        for (char c : word.toCharArray()) {
            int i = c >> 5 & 1;
            int bit = 1 << (c & 31);
            cnt[i] |= bit;
            if (i > 0) {
                // 小写字母，判断前面大写字母是否和当前字母有交集
                if ((cnt[0] & bit) > 0) {
                    invalid |= bit;
                }
            }
            // 如果是大写字符，后面没有出现小写字母，没有交集，所有不用添加到无效中
        }

        return Integer.bitCount(cnt[0] & cnt[1] & ~invalid);
    }

}
