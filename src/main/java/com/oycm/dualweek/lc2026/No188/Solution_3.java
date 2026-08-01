package com.oycm.dualweek.lc2026.No188;

public class Solution_3 {

    /**
     * 击败所有怪物的最小初始强度©leetcode
     *
     * @param monsters
     * @param boosts
     * @return
     */
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        /*
        给你一个整数数组 monsters，其中 monsters[i] 表示第 i 个怪物的强度。
        同时给你一个二维整数数组 boosts，其中 boosts[i] = [li, ri, vi] 表示与下标在 [li, ri] 范围内的任意怪物战斗时，你的 临时加成 会增加 vi。
        加成范围可能会重叠，所有适用的加成值将被相加。
        你以一个 非负 初始强度开始，并从左到右依次与怪物战斗。
        对于下标为 i 的每个怪物：
            令 bonus 为适用于怪物 i 的所有加成值之 和。
            只有你的当前强度加上 bonus 至少 为 monsters[i] 时，你才能击败该怪物。
            击败怪物后，你的当前强度会减少 monsters[i]。如果强度变为 负数，则将其设置为 0。
        返回击败所有怪物所需的 最小 初始强度。
        注意：临时加成仅用于确定是否可以击败当前怪物。它不会以其他方式改变你的当前强度。
         */
        /*
        1 <= monsters.length <= 5 * 104
        1 <= monsters[i] <= 109
        0 <= boosts.length <= 5 * 104
        boosts[i] == [li, ri, vi]
        0 <= li <= ri < monsters.length
        1 <= vi <= 109
         */
        /*
        差分 + 二分答案最小值
         */
        int n = monsters.length;

        // 差分求每个位置 临时战斗力增量
        long[] bonus = new long[n];
        for (int[] b : boosts) {
            bonus[b[0]] += b[2];
            if (b[1] + 1 < n) {
                bonus[b[1] + 1] -= b[2];
            }
        }
        for (int i = 1; i < n; i++) {
            bonus[i] += bonus[i - 1];
        }

        long left = -1;
        long right = 0;

        // 一定不会超过所有怪物强度之和
        for (int x : monsters) {
            right += x;
        }

        while (left + 1 < right) {
            long mid = left + ((right - left) >> 1);
            if (check(mid, monsters, bonus)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    private boolean check(long init, int[] monsters, long[] bonus) {
        long strength = init;

        for (int i = 0; i < monsters.length; i++) {

            // 当前无法击败
            if (strength + bonus[i] < monsters[i]) {
                return false;
            }

            strength -= monsters[i];
            if (strength < 0) {
                strength = 0;
            }
        }

        return true;
    }
}
