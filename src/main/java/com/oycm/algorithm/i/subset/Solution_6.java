package com.oycm.algorithm.i.subset;

public class Solution_6 {

    /**
     * LCP 51. <a href="https://leetcode.cn/problems/UEcfPD/description/">烹饪料理</a>
     * <p>
     * 1 <= cookbooks.length == attribute.length <= 8
     *
     * @param materials materials.length == 5, materials[j] 表示第 j 种食材的数量
     * @param cookbooks cookbooks[i].length == 5, cookbooks[i][j] 表示制作第 i 种料理需要第 j 种食材的数量
     * @param attribute attribute[i].length == 2, 表示第 i 道料理的美味度 x 和饱腹感 y
     * @param limit     饱腹感 最小值
     * @return 每种料理只能制作一次, 饱腹感不小于 limit 的情况下, 可获得的最大美味度. 如果无法满足饱腹感要求，则返回 -1
     */
    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        /*
        选/不选 思路
        回溯三问：
            当前操作？枚举第 i 个 cookbooks 料理, 制作/不制作 的美味度, 饱腹感
            子问题？从下标 >= i 制作/不制作 的美味度, 饱腹感
            下一个子问题？从下标 >= i+1 构造子集
         */
        dfs(0, materials, cookbooks, attribute, limit, 0, 0);
        return ans;
    }

    int ans = -1;

    public void dfs(int i, int[] materials, int[][] cookbooks, int[][] attribute, int limit, int x, int y) {
        if (i == cookbooks.length) {
            if (y >= limit) ans = Math.max(x, ans);
            return;
        }
        // 第 i 个料理 不制作
        dfs(i + 1, materials, cookbooks, attribute, limit, x, y);
        // 是否能够 制作
        boolean enable = true;
        for (int j = 0; j < materials.length; j++) {
            if (materials[j] < cookbooks[i][j]) {
                enable = false;
                break;
            }
        }
        if (enable) {
            for (int j = 0; j < materials.length; j++) {
                materials[j] -= cookbooks[i][j];
            }
            dfs(i + 1, materials, cookbooks, attribute, limit, x + attribute[i][0], y + attribute[i][1]);
            // 回溯
            for (int j = 0; j < materials.length; j++) {
                materials[j] += cookbooks[i][j];
            }
        }
    }


}
