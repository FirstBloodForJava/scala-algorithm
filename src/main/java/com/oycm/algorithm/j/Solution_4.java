package com.oycm.algorithm.j;

public class Solution_4 {

    /**
     * 880. <a href="https://leetcode.cn/problems/decoded-string-at-index/description/">索引处的解码字符串</a> 2011
     *
     * @param s 编码字符串
     * @param k
     * @return
     */
    public String decodeAtIndex(String s, int k) {
        /*
        找出 解码字符串 并将其写入磁带。
        解码时，从编码字符串中 每次读取一个字符 ，并采取以下步骤：
            如果所读的字符是字母，则将该字母写在磁带上。
            如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写 d-1 次。
        查找并返回解码字符串中的第 k 个字母。
         */
        /*
        题解思路：逆向工作法
            先计算字符串的总长度；
            倒叙遍历字符串
         */
        int n = s.length();
        long size = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                size *= c - '0';
            } else {
                size++;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            k %= size;
            char c = s.charAt(i);
            if (k == 0 && Character.isLetter(c)) {
                return Character.toString(c);
            }
            if (Character.isDigit(c)) {
                size /= c - '0';
            } else {
                size--;
            }
        }
        return null;
    }


}
