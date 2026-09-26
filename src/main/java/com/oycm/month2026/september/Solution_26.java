package com.oycm.month2026.september;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_26 {

    /**
     * 1807. <a href="https://leetcode.cn/problems/evaluate-the-bracket-pairs-of-a-string/description/">替换字符串中的括号内容</a> 1482
     *
     * @param s
     * @param knowledge
     * @return
     */
    public String evaluate(String s, List<List<String>> knowledge) {
        /*
        给你一个字符串 s ，它包含一些括号对，每个括号中包含一个 非空 的键。
        比方说，字符串 "(name)is(age)yearsold" 中，有 两个 括号对，分别包含键 "name" 和 "age" 。
        你知道许多键对应的值，这些关系由二维字符串数组 knowledge 表示，其中 knowledge[i] = [keyi, valuei] ，表示键 keyi 对应的值为 valuei 。
        你需要替换 所有 的括号对。当你替换一个括号对，且它包含的键为 keyi 时，你需要：
            将 keyi 和括号用对应的值 valuei 替换。
            如果从 knowledge 中无法得知某个键对应的值，你需要将 keyi 和括号用问号 "?" 替换（不需要引号）。
        knowledge 中每个键最多只会出现一次。s 中不会有嵌套的括号。
        请你返回替换 所有 括号对后的结果字符串。
         */
        Map<String, String> map = new HashMap<>(knowledge.size());
        for (List<String> list : knowledge) {
            map.put(list.get(0), list.get(1));
        }
        StringBuilder ans = new StringBuilder();
        int n = s.length();
        int left = -1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left = i;
            } else if (c == ')') {
                String key = s.substring(left + 1, i);
                ans.append(map.getOrDefault(key, "?"));
                left = -1;
            } else if (left < 0) {
                ans.append(c);
            }
        }
        return ans.toString();
    }

}
