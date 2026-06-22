package com.oycm.week.lc2021.No270;

import java.util.*;

public class Solution_1 {

    /**
     * 2094. <a href="https://leetcode.cn/problems/finding-3-digit-even-numbers/description/">找出 3 位偶数</a> 1455
     *
     * @param digits
     * @return
     */
    public int[] findEvenNumbers(int[] digits) {
        /*
        给你一个整数数组 digits ，其中每个元素是一个数字（0 - 9）。数组中可能存在重复元素。
        你需要找出 所有 满足下述条件且 互不相同 的整数：
            该整数由 digits 中的三个元素按 任意 顺序 依次连接 组成。
            该整数不含 前导零。
            该整数是一个 偶数。
        例如，给定的 digits 是 [1, 2, 3] ，整数 132 和 312 满足上面列出的全部条件。
        将找出的所有互不相同的整数按 递增顺序 排列，并以数组形式返回。
         */
        /*
        3 <= digits.length <= 100
        0 <= digits[i] <= 9
         */
        /*
        三重循环写法，把 digits 排序，按以下几点循环：
            最外层循环不能以数字 0 开始；
            内层循环要知道前面选了哪些下标（要三个数）；
            当前循环选了哪些数（不能重复）；
         */
        int n = digits.length;
        Arrays.sort(digits);
        List<Integer> list = new ArrayList<>();
        Set<Integer> first = new HashSet<>(10, 1);

        for (int i = 0; i < n; i++) {
            if (digits[i] == 0 || first.contains(digits[i])) {
                continue;
            }
            first.add(digits[i]);
            Set<Integer> second = new HashSet<>(10, 1);
            for (int j = 0; j < n; j++) {
                int x = digits[i];
                Set<Integer> third = new HashSet<>(10, 1);
                if (j == i || second.contains(digits[j])) {
                    continue;
                }
                second.add(digits[j]);
                x = x * 10 + digits[j];
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j && !third.contains(digits[k]) && digits[k] % 2 == 0) {
                        third.add(digits[k]);
                        list.add(x * 10 + digits[k]);
                    }
                }
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public int[] findEvenNumbers_1(int[] digits) {
        /*
        答案的视角
         */
        int[] cnt = new int[10];
        for (int i : digits) {
            cnt[i]++;
        }
        List<Integer> path = new ArrayList<>();
        out:
        for (int i = 100; i < 1000; i += 2) {
            int[] c = new int[10];
            for (int x = i; x > 0; x /= 10) {
                int d = x % 10;
                if (++c[d] > cnt[d]) continue out;
            }
            path.add(i);
        }

        return path.stream().mapToInt(i -> i).toArray();
    }

    public void dfs(int i, int x, int[] cnt, List<Integer> path) {
        if (i == 3) {
            path.add(x);
            return;
        }
        for (int d = 0; d < cnt.length; d++) {
            if (cnt[d] > 0 && (i == 0 && d > 0 || (i == 1) || i == 2 && d % 2 == 0)) {
                cnt[d]--;
                dfs(i + 1, x * 10 + d, cnt, path);
                cnt[d]++;
            }
        }

    }

}
