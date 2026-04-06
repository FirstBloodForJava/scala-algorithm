package com.oycm.month2026.april;

import com.oycm.utils.DataCreateUtils;

import java.util.*;

public class Solution_6 {

    /**
     * 874. <a href="https://leetcode.cn/problems/walking-robot-simulation/description/">模拟行走机器人</a> 1846
     *
     * @param commands
     * @param obstacles obstacles[i] = (x, y) 表示第 i 个障碍物的坐标
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        /*
        机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向 北方。
        机器人可以接收以下三种类型的命令 commands：
            -2 ：向左转 90 度
            -1 ：向右转 90 度
            1 <= x <= 9 ：向前移动 x 个单位长度
        机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，并继续执行下一个命令。原点可能有障碍物
         */
        /*
        1. 怎么表示移动的方向, 当 commands[i] > 0 时， 坐标 x,y 的值怎么变化
            [0, 1], [-1, 0], [0, -1], [1, 0], [0, 1]
            dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
            k = 0;
            commands[i] == -1, dir = dirs[++k];
            commands[i] == -2, dir = dirs[(commands[i] + 3) % 4]
        2. 怎么看当前移动时是否有障碍物
            obstacles 构建成两个 的 map
                key = x, value = y 的有序集合
                key = y, value = x 的有序集合
            dir[0] == 0 时, map.get(x); dir[1] > 0, 查找 >= y + 1 的第一个下标; dir[1] < 0, 查找 <= y-1 的最后一个下标( >= y 第一个下标减少 1)
            dir[0] != 0 时, map.get(y);
         */
        int x = 0, y = 0;
        int ans = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int k = 0;
        int[] dir = dirs[k];
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, List<Integer>> yMap = new HashMap<>();
        for (int[] obstacle : obstacles) {
            xMap.computeIfAbsent(obstacle[0], l -> new ArrayList<>()).add(obstacle[1]);
            yMap.computeIfAbsent(obstacle[1], l -> new ArrayList<>()).add(obstacle[0]);
        }
        for (Map.Entry<Integer, List<Integer>> kv : xMap.entrySet()) {
            Collections.sort(kv.getValue());
        }
        for (Map.Entry<Integer, List<Integer>> kv : yMap.entrySet()) {
            Collections.sort(kv.getValue());
        }
        for (int command : commands) {
            if (command > 0) {
                // x 不变, y 移动
                if (dir[0] == 0) {
                    y = getPosition(xMap, x, y, y + dir[1] * command);
                } else {
                    // y 不变, x 移动
                    x = getPosition(yMap, y, x, x + dir[0] * command);
                }
                ans = Math.max(ans, x * x + y * y);
            } else if (command == -1) {
                k++;
                dir = dirs[k % 4];
            } else {
                k = (k + 3) % 4;
                dir = dirs[k];
            }
        }

        return ans;
    }

    public int getPosition(Map<Integer, List<Integer>> map, int key, int value, int newValue) {
        List<Integer> list = map.get(key);
        if (list == null) return newValue;
        if (value < newValue) {
            value++;
        }
        int l = -1, r = list.size();
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) >= value) {
                r = mid;
            } else {
                l = mid;
            }
        }
        // 移动到更小的
        if (value > newValue) {
            return r == 0 ? newValue : Math.max(newValue, list.get(r - 1) + 1);
        }
        // 对起始点障碍物特殊处理
        return r == list.size() ? newValue : Math.min(newValue, list.get(r) - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution_6().robotSim(new int[]{6, -1, -1, 6},
                DataCreateUtils.twoDimensionInts("[[0,0]]")));
    }

}
