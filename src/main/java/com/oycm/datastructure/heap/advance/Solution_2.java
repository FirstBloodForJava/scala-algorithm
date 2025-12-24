package com.oycm.datastructure.heap.advance;


import com.oycm.DataCreateUtils;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_2 {

    /**
     * 2931. <a href="https://leetcode.cn/problems/maximum-spending-after-buying-items/description/">购买物品的最大开销</a> 1822
     * <p>
     * values[i][j] 表示 第 i 个商店的第 j 件物品的价值
     * <p>
     * 第 i 个商店已经按照非递增排好序了, 0 <= j < n-1, 则 values[i][j] >= values[i][j+1]
     * <p>
     * 每一天只能在 一个 商店购买最右边的一件物品, 开销为 values[i][j] * d
     *
     * @param values m * n 的矩阵,
     * @return 求购买所有物品的最大开销
     */
    public static long maxSpending(int[][] values) {
        /*
        要想购买物品的开销最大, 最开始的天数较小, 购买价值较小的物品 value = [2, 3]; d = [1, 2]
        values[i][j] >= values[i][j+1], 建一个小顶堆, 根据 values[j] j 初始化为 n-1 进行排序

         */
        PriorityQueue<Map.Entry<int[], Integer>> min = new PriorityQueue<>(Comparator.comparingInt(a -> a.getKey()[a.getValue()]));
        int n = values[0].length;
        for (int[] value : values) {
            min.add(new AbstractMap.SimpleEntry<>(value, n - 1));
        }
        long ans = 0;
        int d = 1;
        while (!min.isEmpty()) {
            Map.Entry<int[], Integer> head = min.poll();
            ans += (long) head.getKey()[head.getValue()] * d;
            // 还有元素可购买
            if (head.getValue() > 0) {
                min.add(new AbstractMap.SimpleEntry<>(head.getKey(), head.getValue() - 1));
            }
            d++;
        }

        return ans;
    }

    public static long answerOptimize(int[][] values) {
        PriorityQueue<int[]> min = new PriorityQueue<>(Comparator.comparingInt(a -> values[a[0]][a[1]]));
        int n = values[0].length;
        for (int i = 0; i < values.length; i++) {
            min.add(new int[]{i, n - 1});
        }
        long ans = 0;
        int d = 1;
        while (!min.isEmpty()) {
            int[] head = min.poll();
            ans += (long) values[head[0]][head[1]] * d;
            // 还有元素可购买
            if (head[1] > 0) {
                min.add(new int[]{head[0], head[1] - 1});
            }
            d++;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxSpending(DataCreateUtils.twoDimensionInts("[[8,5,2],[6,4,1],[9,7,3]]")));
    }

}
