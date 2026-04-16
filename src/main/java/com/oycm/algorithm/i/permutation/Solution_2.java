package com.oycm.algorithm.i.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2 {

    /**
     * 3799. <a href="https://leetcode.cn/problems/word-squares-ii/description/">单词方块 II</a> 1606
     *
     * @param words 字符串数组, 一组 互不相同 且由小写英文字母组成的四字母字符串; words[i].length == 4; words.length [4, 15]
     * @return
     */
    public List<List<String>> wordSquares(String[] words) {
        /*
        单词方块 由 4 个 互不相同 的单词组成：top, left, right 和 bottom，它们按如下方式排列：
            top 形成 顶部行 。
            bottom 形成 底部行 。
            left 形成 左侧列（从上到下）。
            right 形成 右侧列（从上到下）。
        必须满足以下条件：
            top[0] == left[0], top[3] == right[0]
            bottom[0] == left[3], bottom[3] == right[3]
        按 4 元组 (top, left, right, bottom) 的 字典序升序 排序 返回
         */
        Arrays.sort(words);
        List<List<String>> ans = new ArrayList<>();
        int n = words.length;
        dfs(0, words, Arrays.asList(new String[4]), ans, new boolean[n]);
        return ans;
    }

    public void dfs(int i, String[] words, List<String> path, List<List<String>> ans, boolean[] enable) {
        /*
        枚举选 words 中有哪些可选
         */
        if (i == 4) {
            if (isValid(path)) {
                ans.add(new ArrayList<>(path));
            }

            return;
        }
        for (int j = 0; j < words.length; j++) {
            if (!enable[j]) {
                enable[j] = true;
                path.set(i, words[j]);
                // 先选后判断
                dfs(i + 1, words, path, ans, enable);
                /*if (i == 1) {
                    // 选 left
                    String top = path.get(0);
                    if (top.charAt(0) == words[j].charAt(0)) {
                        path.set(i, words[j]);
                        dfs(i + 1, words, path, ans, enable);
                    }
                } else if (i == 2) {
                    String top = path.get(0);
                    if (top.charAt(3) == words[j].charAt(0)) {
                        path.set(i, words[j]);
                        dfs(i + 1, words, path, ans, enable);
                    }
                } else if (i == 3) {
                    String left = path.get(1);
                    String right = path.get(2);
                    if (words[j].charAt(0) == left.charAt(3) && words[j].charAt(0) == right.charAt(3)) {
                        path.set(i, words[j]);

                    }
                } else {
                    path.set(i, words[j]);
                    dfs(i + 1, words, path, ans, enable);
                }*/
                enable[j] = false;
            }
        }
    }

    public boolean isValid(List<String> path) {
        String top = path.get(0);
        String left = path.get(1);
        String right = path.get(2);
        String bottom = path.get(3);
        return top.charAt(0) == left.charAt(0) &&
                top.charAt(3) == right.charAt(0) &&
                bottom.charAt(0) == left.charAt(3) &&
                bottom.charAt(3) == right.charAt(3);
    }

}
