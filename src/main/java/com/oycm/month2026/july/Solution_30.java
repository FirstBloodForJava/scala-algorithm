package com.oycm.month2026.july;

public class Solution_30 {

    /**
     * 3014. <a href="https://leetcode.cn/problems/minimum-number-of-pushes-to-type-word-i/description/">输入单词需要的最少按键次数 I</a>
     *
     * @param word
     * @return
     */
    public int minimumPushes(String word) {
        /*
        给你一个字符串 word，由 不同 小写英文字母组成。
        电话键盘上的按键与 不同 小写英文字母集合相映射，可以通过按压按键来组成单词。
        例如，按键 2 对应 ["a","b","c"]，我们需要按一次键来输入 "a"，按两次键来输入 "b"，按三次键来输入 "c"。
        现在允许你将编号为 2 到 9 的按键重新映射到 不同 字母集合。每个按键可以映射到 任意数量 的字母，但每个字母 必须 恰好 映射到 一个 按键上。你需要找到输入字符串 word 所需的 最少 按键次数。
        返回重新映射按键后输入 word 所需的 最少 按键次数。
        下面给出了一种电话键盘上字母到按键的映射作为示例。注意 1，*，# 和 0 不 对应任何字母。
         */
        /*
        统计 word 中字符数量，按数量排序升序排序，8 个一组映射到一个按键。
         */
        /*
        由于各个字符互不相同，所以可以均匀分配字符到 8 个按键。
        设字符长度为 n，k = n / 8，n % 8 需要按 k+1 次
        总次数为 8 * (1 + 2 + ... + k) + (n % 8) * (k+1)
        8 * (1 + k)k/2 + (n % 8) * (k+1) = 4k(k+1) + (n % 8) * (k+1)
         */
        int n = word.length();
        int k = n / 8;
        return (k + 1) * (4 * k + n % 8);
    }

}
