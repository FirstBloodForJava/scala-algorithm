package com.oycm.datastructure.trie.basic;

import java.util.Arrays;
import java.util.Comparator;

public class Solution_9 {

    /**
     * 820. <a href="https://leetcode.cn/problems/short-encoding-of-words/description/">单词的压缩编码</a> 1632
     *
     * @param words 单词数组
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        /*
        words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，满足以下条件：
            words.length == indices.length
            助记字符串 s 以 '#' 字符结尾
            对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等
        {"time", "me", "bell"}
        indices[0] = 0; s = time#; words[0] = time;
        indices[1] = 2; s = time#; words[1] = me;
        indices[2] = 5; s = time#bell#; words[2] = bell;

        成功对 words 进行编码的最小助记字符串 s 的长度
         */
        /*
        words[0] + # + words[1] + # + ... + words[n-1] # 这样能构建最长的助记字符串 s
        要想字符串够短，短的字符串需要在 words[i] 中找到满足
            words[j].lastIndexOf(words[i]) + words[i].length() == words[j].length(), 则不需要在助记字符串中添加该字符
        可以对 words 进行排序，最后一个字符一定需要在助记字符串中完全存在

        注意点:
            排序规则要改，默认 atime < time
            查找字符
         */
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int n = words.length;
        StringBuilder ans = new StringBuilder(words[n - 1]).append("#");
        for (int i = n - 2; i >= 0; i--) {
            if (ans.lastIndexOf(words[i] + "#") < 0) {
                ans.append(words[i]).append('#');
            }
        }
        return ans.length();
    }

    public static void main(String[] args) {
        System.out.println("a".compareTo("b"));
        System.out.println(new Solution_9().minimumLengthEncoding(new String[]{"time","atime","btime"}));
//        System.out.println(new Solution_9().minimumLengthEncoding(new String[]{"time", "bell", "mee", "me"}));

    }

}
