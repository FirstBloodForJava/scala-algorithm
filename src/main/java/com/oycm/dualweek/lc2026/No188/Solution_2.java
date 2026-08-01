package com.oycm.dualweek.lc2026.No188;

import java.util.*;

public class Solution_2 {

    /**
     * 栅栏的最宽宽度
     *
     * @param planks
     * @return
     */
    public static int maximumWidth(int[] planks) {
        /*
        给你一个整数数组 planks，其中 planks[i] 表示第 i 块木板的高度。
        每块木板的宽度为 1 个单位。
        你想要用木板建造一个栅栏，栅栏中的所有木板必须具有 相同 的高度。
        你可以直接使用原本的木板，或者将两块不同的原始木板组合成一块新木板，其高度 等于 这两块木板的高度之和。
        每块原始木板 最多 只能使用一次，并且不需要使用所有的原始木板。
        返回可以建造的栅栏的 最大可能宽度。
         */
        /*
        只要求高度相同，或者两块拼接后高度相同，且只能使用一次。
        和顺序没有关系。
        可以枚举固定高度，统计木板和为该高度的数量。
        还有一种情况，都使用两块木板拼接的数量
         */
        /*
        1 <= planks.length <= 1000
        1 <= planks[i] <= 109
         */
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int h : planks) {
            cnt.merge(h, 1, Integer::sum);
        }
        // 提取所有不同的高度及其数量
        int m = cnt.size();
        int[] vals = new int[m];
        int[] counts = new int[m];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            vals[idx] = entry.getKey();
            counts[idx] = entry.getValue();
            idx++;
        }
        // 枚举所有不同的两块板高度
        Map<Integer, Integer> pairCounts = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int a = vals[i];
            int ca = counts[i];

            // 两块高度相同：a + a
            int h = a + a;
            pairCounts.merge(h, ca / 2, Integer::sum);

            // 两块高度不同：a + b
            for (int j = i + 1; j < m; j++) {
                int b = vals[j];
                int cb = counts[j];
                h = a + b;
                int pairs = Math.min(ca, cb);
                pairCounts.merge(h, pairs, Integer::sum);
            }
        }
        // 所有的可能高度
        Set<Integer> th = new HashSet<>(cnt.keySet());
        th.addAll(pairCounts.keySet());
        int ans = 0;
        int n = planks.length;
        for (int h : th) {

            int direct = cnt.getOrDefault(h, 0);
            // 两块拼接可能数量
            int pairs = pairCounts.getOrDefault(h, 0);
            int width = direct + pairs;

            if (width > ans) {
                ans = width;
                if (ans == n) break;
            }
        }
        return ans;
    }

}
