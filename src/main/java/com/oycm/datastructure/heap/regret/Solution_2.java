package com.oycm.datastructure.heap.regret;


import java.util.PriorityQueue;

public class Solution_2 {

    /**
     * 1642. <a href="https://leetcode.cn/problems/furthest-building-you-can-reach/description/">可以到达的最远建筑</a> 1962
     *
     * @param heights 表示建筑物的高度
     * @param bricks  砖块数量
     * @param ladders 梯子可使用数量
     * @return 求最佳使用给定梯子和砖头, 能到达的最远建筑物的下标
     */
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        /*
        全部使用砖块能达到的最远距离, 将里面需要最多的砖块换成 梯子, 才能最大提高梯子的利用率
        使用大顶堆, 记录能被梯子替换的间距, 怎么退出循环
        还要考虑梯子和砖块使用完后, heights[i] >= heights[i+1] 不需要梯子的情况

        注意 bricks 返回砖块是否会越界?
         */
        int ans = 0;
        long enable = bricks;
        PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 1; i < heights.length; i++) {
            if (heights[i - 1] < heights[i]) {
                // 需要使用梯子和砖块
                int diff = heights[i] - heights[i - 1];
                max.add(diff);
                enable -= diff;
                if (enable >= 0) {
                    ans = i;
                } else if (ladders > 0) {
                    // 梯子替换砖块
                    ladders--;
                    enable += max.poll();
                    ans = i;
                }
                if (enable < 0 && ladders <= 0) {
                    break;
                }
            } else if (ans + 1 == i) {
                ans = i;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(furthestBuilding(new int[]{4, 5, 7, 8, 1}, 1, 1));
    }
}
