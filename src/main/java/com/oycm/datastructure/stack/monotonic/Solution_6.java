package com.oycm.datastructure.stack.monotonic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution_6 {

    /**
     * 853. <a href="https://leetcode.cn/problems/car-fleet/description/">车队</a> 1678
     * <p>
     * 一辆车永远不会超过前面的另一辆车，但它可以追上去，并以较慢车的速度在另一辆车旁边行驶。
     * <p>
     * 车队: 是指并排行驶的一辆或几辆汽车。车队的速度是车队中 最慢 的车的速度。
     *
     * @param target   目的地距离
     * @param position 第 i 辆车的位置
     * @param speed    第 i 辆车的速度
     * @return 求车队的数量
     */
    public int carFleet(int target, int[] position, int[] speed) {
        /*
        速度快的车不会超过前面的车，所以要优先看位置在前面的车速度怎么样, 同时计算到达终点需要多少个小时
        维护一个三月组[i, j], i 表示车辆的位置, j 表示速度 按 i 从大到小排序, 遍历新数组, 如果 相同的时间，能走的距离 >= target, 则能追上排在后面组成车队
        [i1,j1] [i2,j2], (target - i1) / j1 >= (target - i2) / j2, (target - i1) * j2 >= (target - i2) * j1, 则组成一个车队

         */
        int n = position.length;
        int[][] ps = new int[n][2];
        for (int i = 0; i < n; i++) {
            ps[i][0] = position[i];
            ps[i][1] = speed[i];
        }
        Arrays.sort(ps, (a, b) -> b[0] - a[0]);
        Deque<int[]> stack = new ArrayDeque<>();
        for (int[] p : ps) {
            if (stack.isEmpty()) {
                stack.push(new int[]{p[0], p[1]});
            } else {
                // (target - i1) * j2 < (target - i2) * j1 组成新的车队
                int[] last = stack.peek();
                int i1 = last[0], j1 = last[1];
                if ((long)(target - p[0]) * j1 > (long) (target - i1) * p[1]) {
                    stack.push(new int[]{p[0], p[1]});
                }
            }
        }
        return stack.size();
    }

}
