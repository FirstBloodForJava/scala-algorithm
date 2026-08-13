package com.oycm.datastructure.segment_tree.binary;

public class Solution_1 {

    /**
     * 3479. <a href="https://leetcode.cn/problems/fruits-into-baskets-iii/description/">水果成篮 III</a>
     *
     * @param fruits
     * @param baskets
     * @return
     */
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        /*
        给你两个长度为 n 的整数数组，fruits 和 baskets，其中 fruits[i] 表示第 i 种水果的 数量，baskets[j] 表示第 j 个篮子的 容量。
        你需要对 fruits 数组从左到右按照以下规则放置水果：
            每种水果必须放入第一个 容量大于等于 该水果数量的 最左侧可用篮子 中。
            每个篮子只能装 一种 水果。
            如果一种水果 无法放入 任何篮子，它将保持 未放置。
        返回所有可能分配完成后，剩余未放置的水果种类的数量。
         */
        /*
        n == fruits.length == baskets.length
        1 <= n <= 1e5
        1 <= fruits[i], baskets[i] <= 1e9
         */
        /*
        暴力做法：如果 fruits[i] 在 baskets 中找到，将找到的值修改为 -1（比 baskets 最小值小即可）。
        问题关键，如何加快 fruits[i] 在 baskets 数组的查询。
         */
        int n = baskets.length;
        SegmentTree st = new SegmentTree(baskets);
        int ans = 0;
        for (int x : fruits) {
            if (st.findFirstAndUpdate(1, 0, n - 1, x) < 0) {
                ans++;
            }
        }

        return ans;
    }

}

class SegmentTree {
    private int[] max;

    public SegmentTree(int[] a) {
        int n = a.length;
        /*
        叶子节点数量为 n, n = a.length，分类讨论：
            如果 n 不是 2 的幂，把叶子节点补齐为 2 的幂，及 1 << (32 - n 的二进制长度)；总节点数 2 << (32 - length(log n)) 再减 1；
            如果 n 是 2 的幂，总节点为 2 * n 再减 1；n = 1 << (32 - length(log n) - 1)，二进制长度再减 1，等价 n-1 二进制长度；
        综述所诉，如果 n 不是 2 的幂，那么 log(n-1) = log(n)；
        构建的二叉数节点个数为 (1 << (33 - Integer.numberOfLeadingZeros(n - 1))) - 1
         */
        max = new int[1 << (33 - Integer.numberOfLeadingZeros(n - 1))];
        build(a, 1, 0, n - 1);
    }

    public void build(int[] a, int o, int l, int r) {
        if (l == r) {
            max[o] = a[l];
            return;
        }
        int m = (l + r) / 2;
        build(a, 2 * o, l, m);
        build(a, 2 * o + 1, m + 1, r);
        maintain(o);
    }

    public void maintain(int o) {
        max[o] = Math.max(max[2 * o], max[2 * o + 1]);
    }

    public int findFirstAndUpdate(int o, int l, int r, int x) {
        // 区间没有大于 x 的数
        if (max[o] < x) {
            return -1;
        }
        // 找到最左边的叶子节点
        if (l == r) {
            max[o] = -1;// 标记已使用
            return l;
        }
        int m = (l + r) / 2;
        int i = findFirstAndUpdate(o * 2, l, m, x);
        if (i < 0) {
            // 左区间未找到
            i = findFirstAndUpdate(o * 2 + 1, m + 1, r, x);
        }
        // 找到后，更新区间的最大值
        maintain(o);
        return i;
    }
}
