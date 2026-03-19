package com.oycm.datastructure.trie.basic;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 3597. <a href="https://leetcode.cn/problems/partition-string/description/">分割字符串</a> 1347
     * 给你一个字符串 s，按照以下步骤将其分割为 互不相同的段:
     * <p>
     * - 从下标 0 开始构建一个段;
     * <p>
     * - 逐字符扩展当前段，直到该段之前未曾出现过;
     * <p>
     * - 只要当前段是唯一的，就将其加入段列表，标记为已经出现过，并从下一个下标开始构建新的段;
     * <p>
     * - 重复上述步骤，直到处理完整个字符串 s
     *
     * @param s
     * @return
     */
    public List<String> partitionString(String s) {
        /*
        字典树
         */
        Trie root = new Trie();
        Trie cur = root;
        List<String> ans = new ArrayList<>();
        char[] cs = s.toCharArray();
        int start = 0;
        for (int i = 0; i < cs.length; i++) {
            int idx = cs[i] - 'a';
            if (cur.son[idx] == null) {
                cur.son[idx] = new Trie();
                ans.add(s.substring(start, i + 1));
                // 从新开始查找
                cur = root;
                start = i + 1;
            } else {
                cur = cur.son[idx];
            }
        }

        return ans;
    }

    static class Trie {
        Trie[] son = new Trie[26];
    }

}
