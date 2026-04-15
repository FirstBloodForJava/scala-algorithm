package com.oycm.algorithm.i.combination;

import java.util.ArrayList;
import java.util.List;

public class Solution_3 {

    /**
     * 1286. <a href="https://leetcode.cn/problems/iterator-for-combination/description/">字母组合迭代器</a> 1591
     */
    static class CombinationIterator {

        List<String> ans = new ArrayList<>();
        int idx = 0;
        /**
         * @param characters        有序且字符唯一 的字符串, length [1, 15]
         * @param combinationLength 整数, <= characters.length
         */
        public CombinationIterator(String characters, int combinationLength) {
            /*
            初始化所有 combinationLength 长度的字母组合
             */
            dfs(0, 0, new char[combinationLength], characters.toCharArray(), ans);
        }

        /**
         * 按 字典序 返回长度为 combinationLength 的下一个字母组合
         *
         * @return
         */
        public String next() {
            return ans.get(idx++);
        }

        public boolean hasNext() {
            return idx < ans.size();
        }

        public void dfs(int i, int size, char[] path, char[] cs, List<String> ans) {
            int need = path.length - size;
            if (need == 0) {
                ans.add(new String(path));
                return;
            }
            for (int j = i; j <= cs.length - need; j++) {
                path[size] = cs[j];
                dfs(j + 1, size + 1, path, cs, ans);
            }

        }
    }

    public static void main(String[] args) {
        CombinationIterator com1 = new CombinationIterator("abc", 2);
    }

}
