package com.oycm.datastructure.heap.basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author ouyangcm
 * create 2025/12/1
 */
public class Solution_1 {

    /**
     * 1046. <a href="https://leetcode.cn/problems/last-stone-weight/description/">最后一块石头的重量</a> 1173
     * <p></p>
     * 每一回合，从中选出两块 最重的 石头, 然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y，粉碎的结果可能如下：
     * <p>- x == y, 两块石头完全粉碎</p>
     * <p>- x != y, 重量为 x 的石头完全粉碎，重量为 y 的石头新重量 y-x</p>
     * 最后至多存在一块石头。返回此石头的重量。如果没有石头剩下，则返回 0
     *
     * @param stones stones[i] range [1,1000],
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        /*
        用一个大顶堆来存数据，堆中有两个数据时，移除两个堆顶元素，按规则判断后是否入堆
         */
        int ans = 0;
        // 建大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(stones.length + 1, Comparator.reverseOrder());
        heap.addAll(Arrays.stream(stones).boxed().collect(Collectors.toList()));

        while (heap.size() > 1) {
            int y = heap.poll();
            int x = heap.poll();
            if (y > x) {
                heap.add(y - x);
            }
        }
        if (!heap.isEmpty()) {
            ans = heap.peek();
        }

        return ans;
    }

    public static void main(String[] args) {

    }

}
