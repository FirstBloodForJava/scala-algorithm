package com.leetcode.interview_question.a;

public class Solution_6 {

    /**
     * 面试题 01.06. <a href="https://leetcode.cn/problems/compress-string-lcci/description/">字符串压缩</a>
     *
     * @param S
     * @return
     */
    public String compressString(String S) {
        /*
        字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
        比如，字符串 aabcccccaaa 会变为 a2b1c5a3。 若“压缩”后的字符串没有变短，则返回原先的字符串。
        你可以假设字符串中只包含大小写英文字母（a至z）。
         */
        if (S.length() == 0) return S;
        StringBuilder ans = new StringBuilder();
        char pre = S.charAt(0);
        int cnt = 0;
        for (char c : S.toCharArray()) {
            if (pre == c) {
                cnt++;
            } else {
                ans.append(pre).append(cnt);
                pre = c;
                cnt = 1;
            }
        }
        ans.append(pre).append(cnt);

        return ans.length() < S.length() ? ans.toString() : S;
    }

}
