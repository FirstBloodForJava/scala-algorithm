package com.oycm.algorithm.i.divide;

import java.util.HashSet;
import java.util.Set;

public class Solution_3 {

    /**
     * 1593. <a href="https://leetcode.cn/problems/split-a-string-into-the-max-number-of-unique-substrings/description/">拆分字符串使唯一子字符串的数目最大</a> 1740
     *
     * @param s
     * @return 拆分后唯一子字符串的最大数目
     */
    public int maxUniqueSplit(String s) {
        /*
        字符串 s 拆分后可以得到若干 非空子字符串 ，这些子字符串连接后应当能够还原为原字符串。
        要求：拆分出来的每个子字符串都必须是 唯一的。
         */
        dfs(0, 0, s, new HashSet<>());
        return ans;
    }

    int ans = 0;

    public void dfs(int i, int start, String s, Set<String> path) {
        /*
        选/不选
         */
        if (i == s.length()) {
            ans = Math.max(ans, path.size());
            return;
        }
        if (i < s.length() - 1) {
            dfs(i + 1, start, s, path);
        }
        // 子串不存在
        String cs = s.substring(start, i + 1);
        if (path.add(cs)) {
            dfs(i + 1, i + 1, s, path);
            // 回溯
            path.remove(cs);
        }
    }

    public void dfs(int i, String s, Set<String> path) {
        // 分割完毕
        if (i == s.length()) {
            ans = Math.max(ans, path.size());
            return;
        }
        for (int j = i; j < s.length(); j++) {
            String cs = s.substring(i, j + 1);
            // 选
            if (path.add(cs)) {
                dfs(j + 1, s, path);
                // 回溯
                path.remove(cs);
            }
        }

    }

}
