package com.oycm.dualweek.No109;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_2 {

    /**
     * 2785. <a href="https://leetcode.cn/problems/sort-vowels-in-a-string/description/">将字符串中的元音字母排序</a> 1267
     *
     * @param s s 只包含英语字母表中的 大写 和 小写 字母。
     * @return
     */
    public String sortVowels(String s) {
        /*
        给你一个下标从 0 开始的字符串 s ，将 s 中的元素重新 排列 得到新的字符串 t ，它满足：
            所有辅音字母都在原来的位置上。更正式的，如果满足 0 <= i < s.length 的下标 i 处的 s[i] 是个辅音字母，那么 t[i] = s[i] 。
            元音字母都必须以他们的 ASCII 值按 非递减 顺序排列。更正式的，对于满足 0 <= i < j < s.length 的下标 i 和 j  ，如果 s[i] 和 s[j] 都是元音字母，那么 t[i] 的 ASCII 值不能大于 t[j] 的 ASCII 值。
         */
        /*
        可以把元音字母单独取出来排序，
        再次遍历 s 字符数组，如果是元音字母，则取排序后的元音字符
         */
        String vowelStr = "aeiouAEIOU";
        char[] cs = s.toCharArray();
        List<Character> vowels = new ArrayList<>();
        for (char c : cs) {
            if (vowelStr.indexOf(c) > -1) vowels.add(c);
        }
        if (vowels.isEmpty()) {
            return s;
        }
        Collections.sort(vowels);
        int i = 0;
        for (int j = 0; j < cs.length; j++) {
            if (vowelStr.indexOf(cs[j]) > -1) cs[j] = vowels.get(i++);
        }

        return new String(cs);
    }

    public String sortVowels_optimize(String s) {
        /*
        a: 1100001  A: 1000001
        b: 1100010  B: 1000010
        c: 1100011  C: 1000011
        d: 1100100  D: 1000100
        e: 1100101  E: 1000101
        f: 1100110  F: 1000110
        g: 1100111  G: 1000111
        h: 1101000  H: 1001000
        i: 1101001  I: 1001001
        j: 1101010  J: 1001010
        k: 1101011  K: 1001011
        l: 1101100  L: 1001100
        m: 1101101  M: 1001101
        n: 1101110  N: 1001110
        o: 1101111  O: 1001111
        p: 1110000  P: 1010000
        q: 1110001  Q: 1010001
        r: 1110010  R: 1010010
        s: 1110011  S: 1010011
        t: 1110100  T: 1010100
        u: 1110101  U: 1010101
        v: 1110110  V: 1010110
        w: 1110111  W: 1010111
        x: 1111000  X: 1011000
        y: 1111001  Y: 1011001
        z: 1111010  Z: 1011010
         */
        /*
        题解思路：观察 a -> z, A -> Z 的低五位二进制代码都是 [1, 26]
        可以用集合 + 二进制来判断字符是否为元音字符
        a e i o u => 二进制集合 {1, 5, 9, 15, 21}
        00000000
        00100000
        10000010
        00100010
        转成 16 进制，
         */
        int vowelMask = 0x208222;
        byte[] vowels = new byte[s.length()];
        char[] cs = s.toCharArray();
        int k = 0;
        for (char c : cs) {
            if ((vowelMask >> (c & 31) & 1) > 0) {
                vowels[k++] = (byte) c;
            }
        }
        Arrays.sort(vowels, 0, k);
        if (k == 0) {
            return s;
        }
        k = 0;
        for (int i = 0; i < cs.length; i++) {
            if ((vowelMask >> (cs[i] & 31) & 1) > 0) {
                cs[i] = (char) vowels[k++];
            }
        }
        return new String(cs);
    }

    public String countSort(String s) {
        /*
        计数排序
         */
        int vowelMask = 0x208222;
        int[] cnt = new int['u' + 1];
        char[] cs = s.toCharArray();

        for (char c : cs) {
            if ((vowelMask >> (c & 31) & 1) > 0) {
                cnt[c]++;
            }
        }

        int j = 'A';
        for (int i = 0; i < cs.length; i++) {
            // 非元音字符跳过
            if ((vowelMask >> (cs[i] & 31) & 1) == 0) {
                continue;
            }
            while (cnt[j] == 0) {
                j = 'Z' == j ? 'a' : j + 1;
            }
            cs[i] = (char) j;
            cnt[j]--;
        }

        return new String(cs);
    }

}
