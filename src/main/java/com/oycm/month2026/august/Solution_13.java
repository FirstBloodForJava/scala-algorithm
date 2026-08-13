package com.oycm.month2026.august;

public class Solution_13 {

    /**
     * 2213. <a href="https://leetcode.cn/problems/longest-substring-of-one-repeating-character/description/">由单个字符重复的最长子字符串</a> 2629
     *
     * @param s
     * @param queryCharacters
     * @param queryIndices
     * @return
     */
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        /*
        给你一个下标从 0 开始的字符串 s。
        另给你一个下标从 0 开始、长度为 k 的字符串 queryCharacters，一个下标从 0 开始、长度也是 k 的整数 下标 数组 queryIndices ，这两个都用来描述 k 个查询。
        第 i 个查询会将 s 中位于下标 queryIndices[i] 的字符更新为 queryCharacters[i] 。
        返回一个长度为 k 的数组 lengths ，其中 lengths[i] 是在执行第 i 个查询 之后 s 中仅由 单个字符重复 组成的 最长子字符串 的 长度 。
         */
        SegmentTree st = new SegmentTree(s.toCharArray());
        int[] ans = new int[queryCharacters.length()];
        for (int i = 0; i < queryIndices.length; i++) {
            st.update(queryIndices[i], queryCharacters.charAt(i));
            ans[i] = st.queryAll();
        }
        return ans;
    }

}

class SegmentTree {
    private record Data(int mx, int pre, int suf) {
    }

    private final int n;
    private final char[] s;
    private final Data[] tree;

    public SegmentTree(char[] s) {
        n = s.length;
        this.s = s;
        tree = new Data[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
        build(1, 0, n - 1);
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            // 初始化叶节点的值
            tree[node] = new Data(1, 1, 1);
            return;
        }
        int m = (l + r) >>> 1;
        // 左子树
        build(node * 2, l, m);
        // 右子树
        build(node * 2 + 1, m + 1, r);
        // 左右子树父节点更新
        maintain(node, l, m, r);
    }

    private void maintain(int node, int l, int m, int r) {
        Data left = tree[node * 2];
        Data right = tree[node * 2 + 1];
        /*
        区间 [l, m] [m+1, r] 计算 [l, r] 区间的 单个字符重复最长字符串的长度
        cs[m] != cs[m+1] 时，mx = max(left.mx, right.mx); pre = left.pre; suf = right.suf;
        cs[m] = cs[m+1] 时，分类讨论：
            如果 [l, m].length = left.pre; 则 pre = left.pre + right.pre;
            如果 [m+1, r].length = right.suf; 则 suf = right.suf + left.suf;
            mx = max(mx, left.suf + right.pre) 前缀+后缀 和 当个最大值比较
         */
        int mx = Math.max(left.mx, right.mx);
        int pre = left.pre;
        int suf = right.suf;
        if (s[m] == s[m + 1]) { // 左区间的最后一个字符 == 右区间的第一个字符
            mx = Math.max(mx, left.suf + right.pre);
            if (left.pre == m - l + 1) {
                pre += right.pre;
            }
            if (right.suf == r - m) {
                suf += left.suf;
            }
        }
        tree[node] = new Data(mx, pre, suf);
    }

    public void update(int i, char val) {
        update(1, 0, n - 1, i, val);
    }

    private void update(int node, int l, int r, int i, char val) {
        if (l == r) {
            /*
            是不是可以在方法入口更新这个值，可以的。
            m 和 i 的判断，决定递归只会执行 log n 次，再归的过程，更新受影响的区间
             */
            s[i] = val;
            return;
        }
        int m = (l + r) >>> 1;
        if (i <= m) { // i 在左子树
            update(node * 2, l, m, i, val);
        } else { // i 在右子树
            update(node * 2 + 1, m + 1, r, i, val);
        }
        maintain(node, l, m, r);
    }

    public int queryAll() {
        return tree[1].mx;
    }
}
