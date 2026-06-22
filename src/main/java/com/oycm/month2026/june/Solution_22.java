package com.oycm.month2026.june;

public class Solution_22 {

    /**
     * 1189. <a href="https://leetcode.cn/problems/maximum-number-of-balloons/description/">“气球” 的最大数量</a> 1182
     *
     * @param text
     * @return
     */
    public int maxNumberOfBalloons(String text) {
        /*
        给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
        字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
         */
        /*
        1 <= text.length <= 1e4
        text 全部由小写英文字母组成
         */
        /*
        统计字符串中字符出现次数，b, a, l/2, o/2, n 次数出现最小值
         */
        int[] cnt = new int[128];
        for (char c : text.toCharArray()) {
            cnt[c]++;
        }
        int res1 = Math.min(cnt['a'], cnt['b']);
        int res2 = Math.min(cnt['l'], cnt['o']) / 2;
        res1 = Math.min(res1, cnt['n']);
        return Math.min(res1, res2);
    }

}
