package com.oycm.week.lc2026.No518;

public class Solution_3 {

    /**
     * 统计机器人组数
     *
     * @param position
     * @param speed
     * @param distance
     * @return
     */
    public int countGroups(int[] position, int[] speed, int distance) {
        /*
        给你一个严格递增的整数数组 position，其中 position[i] 是第 i 个机器人（下标从 0 开始）在时间 t = 0 时的初始位置。
        另给你一个整数数组 speed，其中 speed[i] 是第 i 个机器人的恒定速度（单位：单位/秒），以及一个整数 distance。
        时间是连续的，以秒为单位。速度为 v 的机器人或机器人组在任意 t 秒的时间间隔内向右移动 v * t 个单位。
        每当两个机器人或组之间的距离至多为 distance 时，它们就会合并成一个机器人组。
        如果多个机器人或机器人组在同一时间满足合并条件，则所有合并同时发生。具体而言，任何相邻位置相差至多为 distance 的相连机器人或组都会合并为一个机器人组。
        合并后，生成的机器人组将继承该组中最右侧机器人的当前位置和速度。一旦合并，机器人将永不分离。
        返回在所有可能的合并发生后剩余的组数。
        如果数组中的每个元素都严格大于其前一个元素（如果存在），则该数组是严格递增的。
         */
        /*
        从右到左遍历
         */
        int n = position.length;
        int ans = 1;
        int rightPosition = position[n - 1], rightSpeed = speed[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            int dis = rightPosition - position[i];

            if (dis <= distance || speed[i] > rightSpeed) {
                // 保留速度，距离更新（方便左边的继续合并）
                rightPosition = position[i];
            } else {
                rightSpeed = speed[i];
                rightPosition = position[i];
                ans++;
            }
        }
        return ans;
    }
}
