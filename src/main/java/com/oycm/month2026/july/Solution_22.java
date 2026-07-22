package com.oycm.month2026.july;

import java.util.List;

public class Solution_22 {

    /**
     * 3501. <a href="https://leetcode.cn/problems/maximize-active-section-with-trade-ii/description/">操作后最大活跃区段数 II</a>
     *
     * @param s
     * @param queries
     * @return
     */
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        /*
        给你一个长度为 n 的二进制字符串 s ，其中：
            '1' 表示一个 活跃 区段。
            '0' 表示一个 非活跃 区段。
        你最多可以进行一次 操作 来最大化 s 中活跃区段的数量。在一次操作中，你可以：
            将一个被 '0' 包围的连续 '1' 区块转换为全 '0'。
            然后，将一个被 '1' 包围的连续 '0' 区块转换为全 '1'。
        此外，你还有一个 二维数组 queries，其中 queries[i] = [li, ri] 表示子字符串 s[li...ri]。
        对于每个查询，确定在对子字符串 s[li...ri] 进行最优交换后，字符串 s 中 可能的最大 活跃区段数。
        返回一个数组 answer，其中 answer[i] 是 queries[i] 的结果。
         */
        // todo 线段树
        return null;
    }

}
