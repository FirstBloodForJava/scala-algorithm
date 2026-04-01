package com.oycm.month2026.april;

import java.util.*;

public class Solution_1 {

    /**
     * 2751. <a href="https://leetcode.cn/problems/robot-collisions/description/">机器人碰撞</a> 2092
     *
     * @param positions  机器人的位置, 位置是乱序的, 所有值互不相同
     * @param healths    表示 n 个机器人的健康值
     * @param directions 机器人运动方向, L, R
     * @return
     */
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        /*
        机器人以 相同速度 同时 沿给定方向在路线上移动。如果两个机器人移动到相同位置，则会发生 碰撞。
            两个机器人发生碰撞，则将 健康度较低 的机器人从路线中 移除 ，并且另一个机器人的健康度 减少 1。
            幸存下来的机器人将会继续沿着与之前 相同 的方向前进。
            如果两个机器人的健康度相同，则将二者都从路线中移除。
         */
        /*
        模拟这个过程
        positions[i], i 建立一个 二元组 小顶堆/大顶堆, 比较 positions[i] minHeap
            用一个栈记录 minHead.poll() i 的移动方向, 如果向左, 前面没有向右的机器人，则不用管
         */
        int n = positions.length;
        List<Integer> ans = new ArrayList<>();

        PriorityQueue<int[]> minHead = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < n; i++) {
            minHead.add(new int[]{positions[i], i});
        }
        Deque<Integer> rStack = new ArrayDeque<>();
        while (!minHead.isEmpty()) {
            int[] head = minHead.poll();
            if (directions.charAt(head[1]) == 'R') {
                rStack.push(head[1]);
            } else {
                while (!rStack.isEmpty()) {
                    Integer lastR = rStack.peek();
                    if (healths[lastR] == healths[head[1]]) {
                        healths[head[1]] = 0;
                        healths[lastR] = 0;
                        rStack.pop();
                        break;
                    } else if (healths[lastR] < healths[head[1]]) {
                        // R 移除, L --
                        rStack.pop();
                        healths[lastR] = 0;
                        healths[head[1]]--;
                    } else {
                        healths[lastR]--;
                        healths[head[1]] = 0;
                        break;
                    }
                }
            }

        }


        for (int health : healths) {
            if (health != 0) {
                ans.add(health);
            }
        }
        return ans;
    }

    static class Solution_1_1 {
        public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
            int n = positions.length;
            List<Integer> ans = new ArrayList<>();
            Integer[] idx = new Integer[n];
            for (int i = 0; i < positions.length; i++) {
                idx[i] = i;
            }
            Arrays.sort(idx, Comparator.comparingInt(a -> positions[a]));

            int[] stack = new int[n];
            int top = -1;
            for (Integer i : idx) {
                if (directions.charAt(i) == 'R') {
                    stack[++top] = i;
                } else {
                    while (top >= 0) {
                        int lastR = stack[top];
                        if (healths[lastR] == healths[i]) {
                            healths[i] = 0;
                            healths[lastR] = 0;
                            top--;
                            break;
                        } else if (healths[lastR] < healths[i]) {
                            // R 移除, L --
                            top--;
                            healths[lastR] = 0;
                            healths[i]--;
                        } else {
                            healths[lastR]--;
                            healths[i] = 0;
                            break;
                        }
                    }
                }
            }

            for (int health : healths) {
                if (health != 0) {
                    ans.add(health);
                }
            }
            return ans;
        }
    }

}
