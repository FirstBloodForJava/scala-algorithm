package com.oycm.hot100.hash;

import java.util.*;

public class Solution_2 {

    /**
     * 49. <a href="https://leetcode.cn/problems/group-anagrams/description/">字母异位词分组</a>
     *
     * @param strs strs.length [1, 1e4]; strs[i].length [0, 100]; strs[i] 仅包含小写字母
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        /*
        字母异位词：字母异位词是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次。
        将所有的 字母异位词 组合在一起可以按任意顺序返回
         */
        /*
        字母异位词 只考虑字母出现的频率是否一样，怎么简单表示一组字母异味词？
        aab aba
        暴力的做法，先记录 strs[i] 各个字符的数量，根据这个数量及顺序生成一个字符串，
        可以之间对 strs[i] 的字符数组进行排序，生成新的字符串，相同的分为一组
         */
        Map<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {

            char[] path = str.toCharArray();
            // 排序
            Arrays.sort(path);
            groups.computeIfAbsent(new String(path), l -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(groups.values());
    }

}
