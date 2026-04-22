package com.oycm.month2026.april;

import java.util.ArrayList;
import java.util.List;

public class Solution_22 {

    /**
     * 2452. <a href="https://leetcode.cn/problems/words-within-two-edits-of-dictionary/description/">距离字典两次编辑以内的单词</a> 2452
     *
     * 两个字符串数组，数组中所有单词都只包含小写英文字母，且长度都相同。
     * @param queries
     * @param dictionary
     * @return
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        /*
        一次 编辑 中，你可以从 queries 中选择一个单词，将任意一个字母修改成任何其他字母。
        从 queries 中找到所有满足以下条件的字符串：不超过 两次编辑内，字符串与 dictionary 中某个字符串相同。
         */
        /*
        queries[i] 与 dictionary 中任意字符串，相同下标字符串不相同的数量不超过 2，则符合要求
         */
        List<String> ans = new ArrayList<>();
        outer: for (String word : queries) {
            inner: for (String s : dictionary) {
                int cnt = 0;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) != s.charAt(i)) {
                        cnt++;
                    }
                    if (cnt > 2) {
                        continue inner;
                    }
                }
                ans.add(word);
               continue outer;
            }
        }

        return ans;
    }

}
