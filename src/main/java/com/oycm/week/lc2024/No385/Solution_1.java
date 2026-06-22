package com.oycm.week.lc2024.No385;

import java.util.HashSet;
import java.util.Set;

public class Solution_1 {

    /**
     * 3042. <a href="https://leetcode.cn/problems/count-prefix-and-suffix-pairs-i/description/">统计前后缀下标对 I</a> 1214
     *
     * @param words words.length [1, 50]; words[i].length [1, 10]
     * @return
     */
    public int countPrefixSuffixPairs(String[] words) {
        /*
        给你一个下标从 0 开始的字符串数组 words。
        定义一个 布尔函数 isPrefixAndSuffix ，它接受两个字符串参数 str1 和 str2：
            当 str1 同时是 str2 的前缀（prefix）和后缀（suffix）时，isPrefixAndSuffix(str1, str2) 返回 true，否则返回 false。
        以整数形式，返回满足 i < j 且 isPrefixAndSuffix(words[i], words[j]) 为 true 的下标对 (i, j) 的 数量 。
         */
        /*
        暴力解决思路：记录 i >= 1, 所有 words[i] 的 前缀，后缀 集合
        枚举 i [0, n-2]，查找 [i+1, n) 是否存在符合条件的字符
         */
        int n = words.length;
        Set<String>[] preS = new Set[n];
        Set<String>[] sufS = new Set[n];
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            Set<String> preSet = preS[i] = new HashSet<>();
            Set<String> sufSet = sufS[i] = new HashSet<>();
            for (int j = 1; j <= words[i].length(); j++) {
                preSet.add(word.substring(0, j));
                sufSet.add(word.substring(j - 1));
            }
        }
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (preS[j].contains(words[i]) && sufS[j].contains(i)) {
                    ans++;
                }
            }
        }

        return ans;
    }

}
