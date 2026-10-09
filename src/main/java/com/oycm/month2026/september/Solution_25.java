package com.oycm.month2026.september;

import java.util.*;

public class Solution_25 {

    /**
     * 1096. <a href="https://leetcode.cn/problems/brace-expansion-ii/description/">花括号展开 II</a> 2349
     *
     * @param expression
     * @return
     */
    public List<String> braceExpansionII(String expression) {
        /*
        如果你熟悉 Shell 编程，那么一定了解过花括号展开，它可以用来生成任意字符串。
        花括号展开的表达式可以看作一个由 花括号、逗号 和 小写英文字母 组成的字符串，定义下面几条语法规则：
            如果只给出单一的元素 x，那么表达式表示的字符串就只有 "x"。R(x) = {x}
                例如，表达式 "a" 表示字符串 "a"。
                而表达式 "w" 就表示字符串 "w"。
            当两个或多个表达式并列，以逗号分隔，我们取这些表达式中元素的并集。R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
                例如，表达式 "{a,b,c}" 表示字符串 "a","b","c"。
                而表达式 "{{a,b},{b,c}}" 也可以表示字符串 "a","b","c"。
            要是两个或多个表达式相接，中间没有隔开时，我们从这些表达式中各取一个元素依次连接形成字符串。R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}
                例如，表达式 "{a,b}{c,d}" 表示字符串 "ac","ad","bc","bd"。
                表达式之间允许嵌套，单一元素与表达式的连接也是允许的。
                例如，表达式 "a{b,c,d}" 表示字符串 "ab","ac","ad"。
                例如，表达式 "a{b,c}{d,e}f{g,h}" 可以表示字符串 "abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"。
        给出表示基于给定语法规则的表达式 expression，返回它所表示的所有字符串组成的有序列表。
        假如你希望以「集合」的概念了解此题，也可以通过点击 “显示英文描述” 获取详情。
         */
        List<String> ans = new ArrayList<>(dfs(expression.toCharArray()));
        Collections.sort(ans);
        return ans;
    }

    private int i = 0;

    public Set<String> dfs(char[] es) {
        Set<String> res = new HashSet<>();
        Set<String> cur = new HashSet<>();
        cur.add("");
        while (i < es.length) {
            char c = es[i];
            i++;
            if (c == '}') {
                break;
            }
            if (c == ',') {
                // 取并集：加法
                res.addAll(cur);
                cur.clear();
                cur.add("");
            } else if (c == '{') {
                // 笛卡儿积：乘法
                Set<String> subRes = dfs(es);
                Set<String> newSet = new HashSet<>();
                for (String s : cur) {
                    for (String t : subRes) {
                        newSet.add(s + t);
                    }
                }
                cur = newSet;
            } else {
                Set<String> newSet = new HashSet<>();
                for (String s : cur) {
                    newSet.add(s + c);
                }
                cur = newSet;
            }
        }
        res.addAll(cur);
        return res;
    }

}
