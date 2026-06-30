package com.oycm.string.hash;

import java.util.*;

public class Solution_8 {

    /**
     * 3213. 最小代价构造字符串
     * <br>
     * 3213. <a href="https://leetcode.cn/problems/construct-string-with-minimum-cost/description/">最小代价构造字符串</a> 2171
     *
     * @param target
     * @param words
     * @param costs
     * @return
     */
    public int minimumCost(String target, String[] words, int[] costs) {
        /*
        给你一个字符串 target、一个字符串数组 words 以及一个整数数组 costs，这两个数组长度相同。
        设想一个空字符串 s。
        你可以执行以下操作任意次数（包括 零 次）：
            选择一个在范围  [0, words.length - 1] 的索引 i。
            将 words[i] 追加到 s。
            该操作的成本是 costs[i]。
        返回使 s 等于 target 的 最小 成本。如果不可能，返回 -1。
         */
        char[] t = target.toCharArray();
        int n = t.length;
        /*
        t 长度为 n，字符串 hash 计算表达式
        s[0] * base^(n-1) + s[1] * base^(n-2) + ... + s[n-1]*base^0
         */
        final int mod = 1_070_777_777;
        final int base = (int) 8e8 + new Random().nextInt((int) 1e8); // 随机 base，防止 hack
        int[] powBase = new int[n + 1];
        int[] preHash = new int[n + 1];
        powBase[0] = 1;
        for (int i = 0; i < n; i++) {
            powBase[i + 1] = (int) ((long) powBase[i] * base % mod);
            preHash[i + 1] = (int) (((long) preHash[i] * base + t[i]) % mod);
        }
        Map<Integer, Integer> minCost = new HashMap<>();
        // 不同长度字符串数量
        Set<Integer> lenSet = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            long h = 0;
            for (char c : words[i].toCharArray()) {
                h = (h * base + c) % mod;
            }
            // 这里可能产生 hash 碰撞
            minCost.merge((int) h, costs[i], Integer::min);
            lenSet.add(words[i].length());
        }
        // k 个不同长度的字符串
        int[] sortedLens = new int[lenSet.size()];
        int k = 0;
        for (int len : lenSet) {
            sortedLens[k++] = len;
        }
        Arrays.sort(sortedLens);
        // f[i] 表示使得字符串长为 i 的最小成本
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int len : sortedLens) {
                if (len > i) {
                    break;
                }
                /*
                f[i-len] + 后面拼接一个长为 len 的花费
                target[i-len:i-1] 子串 hash，preHash[i] - preHash[i-len] * powBase[len]
                [0, i-len) 的字符 hash 值要去掉，其前缀在 preHash[i] 中扩大了 base^len
                 */
                int subHash = (int) (((preHash[i] - (long) preHash[i - len] * powBase[len]) % mod + mod) % mod);
                f[i] = Math.min(f[i], f[i - len] + minCost.getOrDefault(subHash, Integer.MAX_VALUE / 2));
            }
        }
        return f[n] == Integer.MAX_VALUE / 2 ? -1 : f[n];
    }

    public int minimumCost_dual_hash(String target, String[] words, int[] costs) {
        char[] t = target.toCharArray();
        int n = t.length;
        /*
        t 长度为 n，字符串 hash 计算表达式
        s[0] * base^(n-1) + s[1] * base^(n-2) + ... + s[n-1]*base^0
         */
        final int mod1 = 1_070_777_777;
        final int mod2 = 1_000_000_007;
        final int base1 = (int) 8e8 + new Random().nextInt((int) 1e8);
        final int base2 = (int) 8e8 + new Random().nextInt((int) 1e8);
        int[] powBase1 = new int[n + 1];
        int[] powBase2 = new int[n + 1];
        int[] preHash1 = new int[n + 1];
        int[] preHash2 = new int[n + 1];
        powBase1[0] = powBase2[0] = 1;
        for (int i = 0; i < n; i++) {
            powBase1[i + 1] = (int) ((long) powBase1[i] * base1 % mod1);
            powBase2[i + 1] = (int) ((long) powBase2[i] * base2 % mod2);
            preHash1[i + 1] = (int) (((long) preHash1[i] * base1 + t[i]) % mod1);
            preHash2[i + 1] = (int) (((long) preHash2[i] * base2 + t[i]) % mod2);
        }
        Map<Long, Integer> minCost = new HashMap<>();
        // 不同长度字符串数量
        Set<Integer> lenSet = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            long h1 = 0;
            long h2 = 0;
            for (char c : words[i].toCharArray()) {
                h1 = (h1 * base1 + c) % mod1;
                h2 = (h2 * base2 + c) % mod2;
            }
            // 这里可能产生 hash 碰撞，两个 int hash 值压缩成 一个 long
            minCost.merge(h1 << 32 | h2, costs[i], Integer::min);
            lenSet.add(words[i].length());
        }
        // k 个不同长度的字符串
        int[] sortedLens = new int[lenSet.size()];
        int k = 0;
        for (int len : lenSet) {
            sortedLens[k++] = len;
        }
        Arrays.sort(sortedLens);
        // f[i] 表示使得字符串长为 i 的最小成本
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int len : sortedLens) {
                if (len > i) {
                    break;
                }
                /*
                f[i-len] + 后面拼接一个长为 len 的花费
                target[i-len:i-1] 子串 hash，preHash[i] - preHash[i-len] * powBase[len]
                [0, i-len) 的字符 hash 值要去掉，其前缀在 preHash[i] 中扩大了 base^len
                 */
                long subHash1 = ((preHash1[i] - (long) preHash1[i - len] * powBase1[len]) % mod1 + mod1) % mod1;
                long subHash2 = ((preHash2[i] - (long) preHash2[i - len] * powBase2[len]) % mod2 + mod2) % mod2;
                long subHash = subHash1 << 32 | subHash2;
                f[i] = Math.min(f[i], f[i - len] + minCost.getOrDefault(subHash, Integer.MAX_VALUE / 2));
            }
        }
        return f[n] == Integer.MAX_VALUE / 2 ? -1 : f[n];
    }

}
