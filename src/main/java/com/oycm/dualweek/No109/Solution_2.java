package com.oycm.dualweek.No109;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_2 {

    /**
     * 2785. <a href="https://leetcode.cn/problems/sort-vowels-in-a-string/description/">将字符串中的元音字母排序</a> 1267
     *
     * @param s s 只包含英语字母表中的 大写 和 小写 字母。
     * @return
     */
    public String sortVowels(String s) {
        /*
        给你一个下标从 0 开始的字符串 s ，将 s 中的元素重新 排列 得到新的字符串 t ，它满足：
            所有辅音字母都在原来的位置上。更正式的，如果满足 0 <= i < s.length 的下标 i 处的 s[i] 是个辅音字母，那么 t[i] = s[i] 。
            元音字母都必须以他们的 ASCII 值按 非递减 顺序排列。更正式的，对于满足 0 <= i < j < s.length 的下标 i 和 j  ，如果 s[i] 和 s[j] 都是元音字母，那么 t[i] 的 ASCII 值不能大于 t[j] 的 ASCII 值。
         */
        /*
        可以把元音字母单独取出来排序，
        再次遍历 s 字符数组，如果是元音字母，则取排序后的元音字符
         */
        String vowelStr = "aeiouAEIOU";
        char[] cs = s.toCharArray();
        List<Character> vowels = new ArrayList<>();
        for (char c : cs) {
            if (vowelStr.indexOf(c) > -1) vowels.add(c);
        }
        if (vowels.isEmpty()) {
            return s;
        }
        Collections.sort(vowels);
        int i = 0;
        for (int j = 0; j < cs.length; j++) {
            if (vowelStr.indexOf(cs[j]) > -1) cs[j] = vowels.get(i++);
        }

        return new String(cs);
    }

}
