package com.oycm.datastructure.trie.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_8 {

    /**
     * 1233. <a href="https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/description/">删除子文件夹</a> 1545
     *
     * @param folder 文件夹列表
     * @return
     */
    public List<String> removeSubfolders(String[] folder) {
        /*
        folder[j] 的子文件夹必须以 folder[j] 开头，后跟一个 "/"
            "/a/b" 是 "/a" 的一个子文件夹
            {"/a","/a/b/c","/a/b/d"} "/a/b/c","/a/b/d" 都会被删除
         */
        /*
        题解优化:
            排序后 folder[0] 不会是任何文件的子文件夹, 否则会在 folder[0] 后面
            如果 folder[i] (i > 0) 是 folder[0] 的子文件夹, 则判断下一个
            如果 folder[i] (i > 0) 不是 folder[0] 的子文件夹, 则添加到答案，下一轮判断一这个字符作为起始文件夹
         */
        /*
        字典树解法: 用 / 分割 folder[i] 构建字典树，然后再 dfs 搜索，找到第一个结尾的 字典树就不继续 搜索
         */
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        for (int i = 1; i < folder.length; i++) {
            String last = ans.get(ans.size() - 1);
            String s = folder[i];
            // cur 包含 last
            if (!last.contains(s) || s.charAt(last.length()) != '/') {
                ans.add(s);
            }
        }
        return ans;
    }

    class Solution_8_1 {
        public List<String> removeSubfolders(String[] folder) {
            /*
            字符串做法, folder 先排序, 是子文件夹标记未 空字符串
             */
            Arrays.sort(folder);
            List<String> ans = new ArrayList<>();
            int n = folder.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (folder[i].isEmpty()) {
                        break;
                    } else if (folder[j].startsWith(folder[i] + "/")) {
                        folder[j] = "";
                    }
                }
            }
            for (String s : folder) {
                if (!s.isEmpty()) {
                    ans.add(s);
                }
            }

            return ans;
        }
    }

}
