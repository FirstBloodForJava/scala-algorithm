package com.oycm.datastructure.tree.top_down_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2 {

    /**
     * 3528. <a href="https://leetcode.cn/problems/unit-conversion-i/description/">单位转换 I</a> 1580
     *
     * @param conversions conversions[i] = [s, t, k] s = k * t
     * @return ans[] ans[i] 表示 0 类型单位等于多少个 i 类型单位
     */
    public int[] baseUnitConversions(int[][] conversions) {
        int n = conversions.length + 1;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, l -> new ArrayList<>());
        for (int[] row : conversions) {
            g[row[0]].add(row);
        }
        int[] ans = new int[n];
        dfs(0, ans, g, 1);
        return ans;
    }

    public void dfs(int i, int[] ans, List<int[]>[] g, long pathProd) {
        ans[i] = (int) pathProd;
        for (int[] row : g[i]) {
            dfs(row[1], ans, g, pathProd * row[2] % 1000000007);
        }
    }

}
