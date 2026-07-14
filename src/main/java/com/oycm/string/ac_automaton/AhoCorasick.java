package com.oycm.string.ac_automaton;

import java.util.ArrayDeque;
import java.util.Queue;

public class AhoCorasick {

    Node root = new Node();

    public void put(String pattern) {
        Node cur = root;
        for (char c : pattern.toCharArray()) {
            c -= 'a';
            if (cur.son[c] == null) {
                cur.son[c] = new Node();
            }
            cur = cur.son[c];
        }
        cur.cnt++;
    }

    public void buildFail() {
        root.fail = root.last = root;
        Queue<Node> q = new ArrayDeque<>();
        for (int i = 0; i < root.son.length; i++) {
            Node son = root.son[i];
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
            Node cur = q.poll();
            for (int i = 0; i < 26; i++) {
                Node son = cur.son[i];
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
                son.last = son.fail.cnt > 0 ? son.fail : son.fail.last;
                q.add(son);
            }
        }

    }


}

class Node {

    Node[] son = new Node[26];
    // 当 node.son[i] 匹配失败时，node.fail[i] 表示下一个待匹配节点（等于 root 则表示匹配结束）
    Node fail;
    // 用来快速跳到一定是某个模式串末尾的节点（等于 root 则表示匹配结束）
    Node last;
    // 当前字符结尾的模式串数量，没有相同字符串，至多 1
    int cnt;
}
