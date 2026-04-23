package com.oycm.algorithm.i.search;

import java.util.ArrayList;
import java.util.List;

public class Solution_3 {

    /**
     * 2094. <a href="https://leetcode.cn/problems/finding-3-digit-even-numbers/description/">找出 3 位偶数</a> 1455
     *
     * @param digits digits[i] [0,9]; digits.length [3, 100]
     * @return
     */
    public int[] findEvenNumbers(int[] digits) {
        /*
        找出 所有 满足下述条件且 互不相同 的整数：
            该整数由 digits 中的三个元素按 任意 顺序 依次连接 组成。
            该整数不含 前导零
            该整数是一个 偶数
         */
        /*
        偶数，要求末尾元素必须是 [0, 2, 4, 6, 8] 之一
        前面两位最多 9 * 10 的组合，答案最多是 9 * 10 * 5
        可以暴力枚举所有的答案
        也可以从 100, 999 枚举，看数字数量是否符合要求
         */
        // 记录所有数字的数量
        int[] cnt = new int[10];
        for (int i : digits) {
            cnt[i]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;
                for (int j = 0; j < 10; j++) {
                    if (cnt[j] > 0) {
                        int second = i * 10 + j;
                        cnt[j]--;
                        for (int k = 0; k < 10; k += 2) {
                            if (cnt[k] > 0) {
                                ans.add(second * 10 + k);
                            }

                        }
                        cnt[j]++;
                    }

                }
                cnt[i]++;
            }
        }

        return ans.stream().mapToInt(o -> o).toArray();
    }

    public void dfs(int i, int x, int[] cnt, List<Integer> ans) {
        if (i == 3) {
            ans.add(x);
            return;
        }
        /*
        百位，低位开始，不能选 0
        十位，低位开始，随便选
        个位，偶数
         */
        for (int d = 0; d < 10; d++) {
            if (cnt[d] > 0 && (i == 0 && d > 0 || i == 1 || i == 2 && d % 2 == 0)) {
                cnt[d]--;
                dfs(i + 1, x * 10 + d, cnt, ans);
                cnt[d]++;
            }
        }
    }

}
