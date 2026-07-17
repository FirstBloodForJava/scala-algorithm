package com.oycm.string.ac_automaton;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_6 {

    /**
     * 2781. 最长合法子字符串的长度
     * <br>
     * 2781. <a href="https://leetcode.cn/problems/length-of-the-longest-valid-substring/description/">最长合法子字符串的长度</a> 2204
     *
     * @param word
     * @param forbidden
     * @return
     */
    public int longestValidSubstring(String word, List<String> forbidden) {
        /*
        给你一个字符串 word 和一个字符串数组 forbidden 。
        如果一个字符串不包含 forbidden 中的任何字符串，我们称这个字符串是 合法 的。
        请你返回字符串 word 的一个 最长合法子字符串 的长度。
        子字符串 指的是一个字符串中一段连续的字符，它可以为空。
         */
        /*
        题解思路：hash 表 + 滑动窗口
        forbidden[i] 长度较短
        初始化子串左端点 left = 0，枚举子串有右端点，判断子串 [left, right] 是否符合要求。
        假设 [left, right] 子串符合要求，随着 right 右移，子串越可能包含 forbidden[i] 字符串，当包含时，right 再往右移动是无效的，需要找到匹配 forbidden[i] 的下标，将 left 移动到该下标后。
        此时 left 之后随着 right 一起往右移动。
        [left, right] 添加字符 word[right+1]，由于 forbidden[i] 长度至多 10，只需要判断新加的字符与前面 9 个字符组成的子串是否在 forbidden 中。
        可以从 right 到 left 枚举子串，判断子串是否存在。
        如果从 left 到 right 枚举子串，需要 left 和 right-8 取最大值开始枚举
         */
        Set<String> set = new HashSet<>(forbidden);
        int ans = 0, left = 0, n = word.length();
        for (int right = 0; right < n; right++) {
            for (int i = right; i >= left && i > right - 10; i--) {
                if (set.contains(word.substring(i, right + 1))) {
                    left = i + 1;
                    break;
                }
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

}
