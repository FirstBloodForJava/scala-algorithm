package com.oycm.string.hash;

import java.util.*;

public class Solution_2 {

    /**
     * 187. 重复的DNA序列
     * <br>
     * 187. <a href="https://leetcode.cn/problems/repeated-dna-sequences/description/">重复的DNA序列</a>
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        /*
        DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'。
        例如，"ACGAATTCCG" 是一个 DNA序列 。
        在研究 DNA 时，识别 DNA 中的重复序列非常有用。
        给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
         */
        /*
        暴力做法：枚举所有长为 10 的子串，判断其是否在 s 中出现至少 2 次。
        怎么判断是否至少出现 2 次？
        字符串 hash，map 的 key 表示每个长度为 10 字符串 hash 值，如果出现的次数大于 2 次，则记录该答案
         */
        char[] t = s.toCharArray();
        int n = t.length;
        /*
        t 长度为 n，字符串 hash 计算表达式
        s[0] * base^(n-1) + s[1] * base^(n-2) + ... + s[n-1]*base^0
         */
        //final int mod = 1_070_777_777;
        final int base = 131313; // 随机 base，防止 hack
        int pow10 = 1;
        for (int i = 0; i < 10; i++) {
            pow10 = pow10 * base;
        }
        int[] preHash = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preHash[i + 1] = preHash[i] * base + t[i];
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < n - 9; i++) {
            int r = i + 10;
            int subHash = preHash[r] - preHash[i] * pow10;
            int c = cnt.getOrDefault(subHash, 0);
            if (c == 1) ans.add(s.substring(i, i + 10));
            cnt.put(subHash, c + 1);
        }

        return ans;
    }

    public List<String> findRepeatedDnaSequences_2(String s) {
        /*
        双模 hash
         */
        char[] t = s.toCharArray();
        int n = t.length;
        /*
        t 长度为 n，字符串 hash 计算表达式
        s[0] * base^(n-1) + s[1] * base^(n-2) + ... + s[n-1]*base^0
         */
        final int mod1 = 1_070_777_777;
        final int mod2 = 1_000_000_007;
        final int base1 = (int) 8e8 + new Random().nextInt((int) 1e8);
        final int base2 = (int) 8e8 + new Random().nextInt((int) 1e8);
        int pow101 = 1;
        int pow102 = 1;
        for (int i = 0; i < 10; i++) {
            pow101 = (int) ((long) pow101 * base1 % mod1);
            pow102 = (int) ((long) pow102 * base2 % mod2);
        }
        int[] preHash1 = new int[n + 1];
        int[] preHash2 = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preHash1[i + 1] = (int) (((long) preHash1[i] * base1 + t[i]) % mod1);
            preHash2[i + 1] = (int) (((long) preHash2[i] * base2 + t[i]) % mod2);
        }
        Map<Long, Integer> cnt = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n - 9; i++) {
            int r = i + 10;
            long subHash1 = ((preHash1[r] - (long) preHash1[i] * pow101) % mod1 + mod1) % mod1;
            long subHash2 = ((preHash2[r] - (long) preHash2[i] * pow102) % mod2 + mod2) % mod2;
            long hash = subHash1 << 32 | subHash2;
            int c = cnt.getOrDefault(hash, 0);
            // 当且第二次出现时记录
            if (c == 1) ans.add(s.substring(i, i + 10));
            cnt.put(hash, c + 1);
        }

        return ans;
    }

}
