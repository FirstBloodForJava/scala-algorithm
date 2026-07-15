package com.oycm.week.lc2018.No98;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_890 {

    /**
     * 890. 查找和替换模式
     * <br>
     * 890. <a href="https://leetcode.cn/problems/find-and-replace-pattern/description/">查找和替换模式</a> 1414
     *
     * @param words
     * @param pattern
     * @return
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        /*
        你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
        如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
        （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
        返回 words 中与给定模式匹配的单词列表。
        你可以按任何顺序返回答案。
         */
        List<String> ans = new ArrayList<>();
        char[] ps = pattern.toCharArray();
        for (String word : words) {
            if (isWordPattern(word, ps)) ans.add(word);
        }

        return ans;
    }

    public boolean isWordPattern(String word, char[] ps) {
        Map<Character, Character> wp = new HashMap<>();
        Map<Character, Character> pw = new HashMap<>();
        for (int i = 0; i < ps.length; i++) {
            char w = word.charAt(i);
            char p = ps[i];
            if (wp.containsKey(w) && wp.get(w) != p) {
                return false;
            }
            if (pw.containsKey(p) && pw.get(p) != w) {
                return false;
            }
            wp.put(w, p);
            pw.put(p, w);
        }

        return true;
    }

}
