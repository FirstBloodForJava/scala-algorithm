package com.oycm.hot150;

public class Solution101 {

    /**
     * 274. <a href="https://leetcode.cn/problems/h-index/description/">H 指数</a>
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        /*
        给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
        根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且 至少 有 h 篇论文被引用次数大于等于 h 。
        如果 h 有多种可能的值，h 指数 是其中最大的那个。
         */
        /*
        第一感觉，排序后，二分查找 nums[mid] >= r - mid
        citations[i] < n，可以使用计数排序，先找出 最大值 citations 数组最大值，创建一个计数数组 cnt
        从大到小遍历 cnt，cnt[i] 及 cnt[i+1] 后的引用次数都是 大于等于 cnt[i]
        */
        int mx = 0;
        for (int x : citations) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int i : citations) {
            cnt[i]++;
        }
        int h = 0;
        int c = 0;
        for (int i = mx; i > 0; i--) {
            c += cnt[i];
            if (c >= i) {
                return i;
            }
        }

        return h;
    }

}
