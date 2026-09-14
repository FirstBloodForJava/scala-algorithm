package com.oycm.dp.f.basic;

public class Solution_2 {

    /**
     * 3259. <a href="https://leetcode.cn/problems/maximum-energy-boost-from-two-drinks/description/">超级饮料的最大强化能量</a> 1484
     *
     * @param energyDrinkA
     * @param energyDrinkB
     * @return
     */
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        /*
        来自未来的体育科学家给你两个整数数组 energyDrinkA 和 energyDrinkB，数组长度都等于 n。
        这两个数组分别代表 A、B 两种不同能量饮料每小时所能提供的强化能量。
        你需要每小时饮用一种能量饮料来 最大化 你的总强化能量。然而，如果从一种能量饮料切换到另一种，你需要等待一小时来梳理身体的能量体系（在那个小时里你将不会获得任何强化能量）。
        返回在接下来的 n 小时内你能获得的 最大 总强化能量。
        注意 你可以选择从饮用任意一种能量饮料开始。
         */
        /*
        f[i+1][0], f[i+1][1] 表示第 i 个小时饮用 A, B 分别获得的最大能量
        f[i+1][0] = max(f[i][0] + a[i], f[i-1][1] + a[i]
         */
        long preA = 0, fa = 0;
        long preB = 0, fb = 0;
        for (int i = 0; i < energyDrinkA.length; i++) {
            long newFa = Math.max(fa + energyDrinkA[i], preB + energyDrinkA[i]);
            preB = fb;
            fb = Math.max(fb + energyDrinkB[i], preA + energyDrinkB[i]);
            preA = fa;
            fa = newFa;
        }


        return Math.max(fa, fb);
    }

}
