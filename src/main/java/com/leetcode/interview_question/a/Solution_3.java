package com.leetcode.interview_question.a;

public class Solution_3 {

    /**
     * 面试题 01.03. <a href="https://leetcode.cn/problems/string-to-url-lcci/description/">URL 化</a>
     *
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces(String S, int length) {
        /*
        URL化。编写一种方法，将字符串中的空格全部替换为 %20。
        假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
        （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
         */
        /*
        S [length, S.length()) 区间是 空格，前面的位置填了空额，空额后面的字符需要向右平移 2 位。
        如果正序遍历，遇到一个空格，则填 %20，右边字符向右平移 2，时间复杂度是 n^2。
        改成倒序遍历，原字符从 l = length - 1 开始，待填位置从 r = S.length - 1，开始，
            如果 S[l] 不为空格，S[r--] = S[l--]；
            如果 S[l] 空格， 后面填 三位字符
            当 l = r 时，说明有空额位置处，都已经填好了，可以结束循环
        S 长度大于替换后的长度，需要先计算 ' ' 数量，确定起始位置
        S length 填到 0 之后，截取，不用知道 空格字符数量
         */
        char[] cs = S.toCharArray();
        int r = cs.length - 1;
        for (int i = length - 1; i >= 0 ; i--) {
            if (cs[i] != ' ') {
                cs[r] = cs[i];
            } else {
                // 空格
                cs[r] = '0';
                cs[--r] = '2';
                cs[--r] = '%';
            }
            r--;
        }
        // [r+1, cs.length)
        return new String(cs, r + 1, cs.length - r - 1);
    }



}
