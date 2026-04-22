package com.oycm.algorithm.i.backtracking_with_duplicate_elements;

import java.util.*;

public class Solution_6 {

    /**
     * 3646. <a href="https://leetcode.cn/problems/next-special-palindrome-number/description/">下一个特殊回文数</a> 2445
     *
     * @param n [0, 1e15]
     * @return 严格 大于 n 的 最小 特殊数
     */
    public long specialPalindrome(long n) {
        /*
        特殊数条件：
            它是一个 回文数 。
            数字中每个数字 k 出现 恰好 k 次。不能有数字 0
         */
        /*
        长为 m 的 n 是否存在一个符合要求的字符串？
        1 出现 1 次
        2 出现 2 次
        ...
        要满足回文条件，
            m 是偶数的情况下，里面的数字只能都出现偶数次，数字只能为 [8, 6, 4, 2]; 最大长度 20
            m 是奇数的情况下，里面的数字必须出现一次奇数，其余可为任意偶数 [1, 3, 5, 7, 9] 和多个 [8, 6, 4, 2] 任意组合，最大长度 9 + 20
        相当于从 1-9 中找出和为 m 及 m+1 的数字组合，数字只能出现一次，且奇数也只能出现一次
        可以生成 [1, 1e15] 所有特殊数，然后二分查找答案
        怎么生成答案？
        题解思路：回文数是 [8, 6, 4, 2] 加上至多一个奇数组成，有 2^4 * 6 - 1 种非空组合
            偶数，选/不选有 2^4 选法；有 5 个奇数可选，以及奇数不选；都不选时的空集不考虑
        {1,2,3,4,5,6,7,8,9} 集合至多只能有一个奇数的子集，可以用二进制表示集合
        全集 U = {1,2,3,4,5,6,7,8,9} = 111111111
            8 7 6 5 4 3 2 1 0 偶数下标数量不能大于 1, 与 oddMask = 101010101 交集不能有 2 个及以上，(u & oddMask) & ((u & oddMask) - 1) > 0，则有至少 2 个 1
        预处理所有符合要求子集长度，n 对应长度 m 或 m + 1 中大于 n 的最小值就是答案
         */
        int m = String.valueOf(n).length();
        for (int mask = 1; mask < size.length; mask++) {
            // 长度符合要符合要求
            int length = size[mask];
            if (length != m && length != m + 1) {
                continue;
            }
            // 构造符合条件的集合， 回文串的前半段
            int[] perm = new int[length / 2];
            int idx = 0;
            int odd = 0; // 表示集合中的奇数
            for (int i = 1; i <= u; i++) {
                if ((mask >> (i - 1) & 1) > 0) {
                    for (int k = 0; k < i / 2; k++) {
                        perm[idx++] = i;
                    }
                    if (i % 2 != 0) {
                        odd = i;
                    }
                }
            }
            dfs(0, 0, new boolean[perm.length], perm, odd, n);
        }

        return ans;
    }

    private long ans = Long.MAX_VALUE;

    public boolean dfs(int i, long res, boolean[] enable, int[] perm, int odd, long n) {
        if (i == perm.length) {
            long v = res;
            if (odd > 0) {
                res = res * 10 + odd;
            }
            // 反转得到后半段回文串
            while (v > 0) {
                res = res * 10 + v % 10;
                v /= 10;
            }
            // 剪枝
            if (res > ans) {
                return true;
            }
            if (res > n) {
                ans = res;
                return true;
            }
            return false;
        }
        for (int j = 0; j < perm.length; j++) {
            // 含有重复元素，且有序的全排列
            if (enable[j] || (j > 0 && perm[j] == perm[j - 1] && !enable[j - 1])) {
                continue;
            }
            enable[j] = true;
            dfs(i + 1, res * 10 + perm[j], enable, perm, odd, n);
            enable[j] = true;
        }

        return false;
    }

    // 101010101
    public static final int oddMask = 0x155;
    public static final int u = 9;
    public static final int[] size = new int[1 << 9];

    static {
        for (int mask = 0; mask < size.length; mask++) {
            int t = mask & oddMask;
            if ((t & (t - 1)) > 0) {
                continue;
            }
            // 计算子集的对应的字符串长度
            for (int i = 0; i < u; i++) {
                if ((mask >> i & 1) != 0) {
                    // 下标从 0 开始，0 表示元素 1
                    size[mask] += i + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution_6 solution = new Solution_6();
        solution.specialPalindrome(23670);
        System.out.println(Collections.binarySearch(Solution_3646.ans, 1L));
        System.out.println(Solution_3646.ans.size());
    }


}

class Solution_3646 {

    public long specialPalindrome(long n) {
        /*
        枚举 [1, 16] 长度子集的所有全排列答案，再二分查找
         */
        int idx = Collections.binarySearch(ans, n);
        if (idx > 0) {
            return ans.get(idx + 1);
        }
        return ans.get(~idx);
    }

    public static final int oddMask = 0x155;
    public static final int u = 9;
    public static final int[] size = new int[1 << 9];

    public static final List<Long> ans = new ArrayList<>();

    static {
        for (int mask = 1; mask < size.length; mask++) {
            int t = mask & oddMask;
            if ((t & (t - 1)) > 0) {
                continue;
            }
            // 计算子集的对应的字符串长度
            for (int i = 0; i < u; i++) {
                if ((mask >> i & 1) != 0) {
                    // 下标从 0 开始，0 表示元素 1
                    size[mask] += i + 1;
                }
            }
            int length = size[mask];
            if (length > 16) {
                continue;
            }

            int[] perm = new int[length / 2];
            int idx = 0;
            int odd = 0; // 表示集合中的奇数
            for (int i = 1; i <= u; i++) {
                if ((mask >> (i - 1) & 1) > 0) {
                    for (int k = 0; k < i / 2; k++) {
                        perm[idx++] = i;
                    }
                    if (i % 2 != 0) {
                        odd = i;
                    }
                }
            }
            dfs(0, 0, perm, odd);
        }

        Collections.sort(ans);
    }

    public static void dfs(int start, long res, int[] perm, int odd) {
        if (start == perm.length) {
            long v = res;
            // 奇数居中
            if (odd > 0) {
                res = res * 10 + odd;
            }
            // 反转得到后半段回文串
            while (v > 0) {
                res = res * 10 + v % 10;
                v /= 10;
            }
            ans.add(res);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = start; i < perm.length; i++) {
            // 重复元素，跳过
            if (!set.add(perm[i])) {
                continue;
            }
            swap(perm, i, start);
            dfs(start + 1, res * 10 + perm[start], perm, odd);
            swap(perm, i, start);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}