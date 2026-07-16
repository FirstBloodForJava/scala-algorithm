package com.oycm.string.ac_automaton;

import java.util.*;

public class Solution_3 {

    /**
     * 面试题 17.17. <a href="https://leetcode.cn/problems/multi-search-lcci/">多次搜索</a>
     *
     * @param big
     * @param smalls
     * @return
     */
    public int[][] multiSearch(String big, String[] smalls) {
        /*
        给定一个较长字符串 big 和一个包含较短字符串的数组 smalls，设计一个方法，根据 smalls 中的每一个较短字符串，对big进行搜索。
        输出 smalls 中的字符串在 big 里出现的所有位置 positions，其中 positions[i] 为 smalls[i] 出现的所有位置。
         */
        AhoCorasickIdx ac = new AhoCorasickIdx();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < smalls.length; i++) {
            map.put(i, new ArrayList<>());
            ac.put(smalls[i], i);
        }
        // 构建 fail 指针
        ac.buildFail();

        NodeIdx cur = ac.root;
        for (int i = 0; i < big.length(); i++) {
            char c = big.charAt(i);
            cur = cur.son[c - 'a'];
            for (NodeIdx match = cur; match != ac.root; match = match.last) {
                // smalls[match.cnt] 字符串匹配，设置在 big 中的位置
                if (match.cnt >= 0) {
                    map.get(match.cnt).add(i - smalls[match.cnt].length() + 1);
                }
            }

        }

        // 返回答案
        int[][] ans = new int[smalls.length][];
        for (int i = 0; i < ans.length; i++) {
            List<Integer> list = map.get(i);
            ans[i] = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                ans[i][j] = list.get(j);
            }
        }

        return ans;
    }

}

class AhoCorasickIdx {

    NodeIdx root = new NodeIdx();

    public void put(String pattern, int idx) {
        NodeIdx cur = root;
        for (char c : pattern.toCharArray()) {
            c -= 'a';
            if (cur.son[c] == null) {
                cur.son[c] = new NodeIdx();
            }
            cur = cur.son[c];
        }
        cur.cnt = idx;
    }

    public void buildFail() {
        root.fail = root.last = root;
        Queue<NodeIdx> q = new ArrayDeque<>();
        for (int i = 0; i < root.son.length; i++) {
            NodeIdx son = root.son[i];
            if (son == null) {
                /*
                空节点（虚拟子节点） 设置为 root
                 */
                root.son[i] = root;
                continue;
            }
            // 第一层的 fail 都指向 root 根节点
            son.fail = son.last = root;
            q.add(son);
        }
        while (!q.isEmpty()) {
            // cur 的失配节点已确定，更具 cur 失配节点计算 cur.son[i] 的失配节点
            NodeIdx cur = q.poll();
            for (int i = 0; i < 26; i++) {
                NodeIdx son = cur.son[i];
                if (son == null) {
                    /*
                    把虚拟子节点（空节点） cur.son[i] 设置为 cur.fail.son[i]
                    方便失配时直接跳到下一个可能匹配的位置（但不一定是某个模式串的末尾）
                     */
                    cur.son[i] = cur.fail.son[i];
                    continue;
                }
                // 设置 fail 节点
                son.fail = cur.fail.son[i];
                /*
                沿着 last 往上走，可以直接跳到一定是某个模式串末尾的节点（如果跳到 root 表示匹配结束）
                 */
                son.last = son.fail.cnt >= 0 ? son.fail : son.fail.last;
                q.add(son);
            }
        }

    }


}

class NodeIdx {

    NodeIdx[] son = new NodeIdx[26];
    // 当 node.son[i] 匹配失败时，node.fail[i] 表示下一个待匹配节点（等于 root 则表示没有匹配）
    NodeIdx fail;
    // 后缀链接（suffix link），用来快速跳到一定是某个模式串末尾的节点（等于 root 则表示匹配结束）
    NodeIdx last;
    // 当前字符结尾的模式串数量，没有相同字符串，至多 1
    int cnt = -1;
}
