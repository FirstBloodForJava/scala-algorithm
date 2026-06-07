package com.oycm.dualweek2025.No162;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_4 {

    /**
     * 3636. <a href="https://leetcode.cn/problems/threshold-majority-queries/description/">查询超过阈值频率最高元素</a> 2451
     *
     * @param nums
     * @param queries
     * @return
     */
    public int[] subarrayMajority(int[] nums, int[][] queries) {
        /*
        给你一个长度为 n 的整数数组 nums 和一个查询数组 queries，其中 queries[i] = [li, ri, thresholdi]。
        返回一个整数数组 ans，其中 ans[i] 等于子数组 nums[li...ri] 中出现 至少 thresholdi 次的元素，
        选择频率 最高 的元素（如果频率相同则选择 最小 的元素），如果不存在这样的元素则返回 -1。
         */
        /*
        1 <= nums.length == n <= 1e4
        1 <= nums[i] <= 1e9
        1 <= queries.length <= 5 * 1e4
        queries[i] = [li, ri, thresholdi]
        0 <= li <= ri < n
        1 <= thresholdi <= ri - li + 1
         */
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];
        /*
        块的大小设为 B，把询问按照左端点所在块分组，左端点在同一个块的询问分到同一组。
            对于每个块，把右端点 从小到大 排序，使得右端点一直向右移动，而左端点只在 块内抖动。
            对于每个块，右端点平均总移动次数 n/2 （为什么），有 n/B 个块，对于所有询问，则需要移动约 n^2/2B；
        对于每个询问，左端点只在块内移动，平均移动次数为 B，左端点以从次数 qB；
        相加得 n^2/2B + qB，求这里的最小值
        当 n^2/2B = qB 时，取最小值，B = n/sqrt(2q)
         */
        /*
        对于右端点，各个块的移动次数为 n, n-B, n-2B, ... B，累加得
            n + (n-B) + (n-2B) + ... + B = (n+B)k/2 = (n+B)/2 * n/B = n^2/2B + n/2
            对于每个块，平均移动次数 = 总次数 / n/B = n/2 + B/2
         */
        int blockSize = (int) Math.ceil(n / Math.sqrt(m * 2));

        record Query(int bid, int l, int r, int threshold, int qid) { // [l,r) 左闭右开
        }

        List<Query> qs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int l = q[0];
            int r = q[1] + 1; // 左闭右开
            int threshold = q[2];

            // 大区间离线（保证 l 和 r 不在同一个块中）
            if (r - l > blockSize) {
                qs.add(new Query(l / blockSize, l, r, threshold, i));
                continue;
            }

            // 小区间暴力
            for (int j = l; j < r; j++) {
                add(nums[j]);
            }
            ans[i] = maxCnt >= threshold ? minVal : -1;

            // 重置数据
            cnt.clear();
            maxCnt = 0;
        }
        /*
        a.bid == b.bid 表示在同一个块，需要按右端点从小到大排序
         */
        qs.sort((a, b) -> a.bid != b.bid ? a.bid - b.bid : a.r - b.r);

        int r = 0;
        for (int i = 0; i < qs.size(); i++) {
            Query q = qs.get(i);
            int l0 = (q.bid + 1) * blockSize;
            if (i == 0 || q.bid > qs.get(i - 1).bid) { // 遍历到一个新的块
                r = l0; // 右端点移动的起点
                // 重置数据
                cnt.clear();
                maxCnt = 0;
            }

            /*
            同一个块，从当前块 r 移动到 q.r
            cnt 当前块右边到右端点的出现最大次数以及最小值
             */
            for (; r < q.r; r++) {
                add(nums[r]);
            }

            int tmpMaxCnt = maxCnt;
            int tmpMinVal = minVal;
            /*
            上面计算的是当前块右边的 最大出现次数和最小值
            左端点单独计算，从 [q.l, l0) l0 = k * block
             */
            for (int j = q.l; j < l0; j++) {
                add(nums[j]);
            }
            ans[q.qid] = maxCnt >= q.threshold ? minVal : -1;

            // 回滚
            maxCnt = tmpMaxCnt;
            minVal = tmpMinVal;
            for (int j = q.l; j < l0; j++) {
                cnt.merge(nums[j], -1, Integer::sum);
            }
        }

        return ans;
    }

    private final Map<Integer, Integer> cnt = new HashMap<>();
    private int maxCnt = 0;
    private int minVal = 0;

    private void add(int x) {
        int c = cnt.merge(x, 1, Integer::sum);
        if (c > maxCnt) {
            maxCnt = c;
            minVal = x;
        } else if (c == maxCnt) {
            minVal = Math.min(minVal, x);
        }
    }

}
