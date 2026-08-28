package com.oycm.month2026.august;

public class Solution_28 {

    /**
     * 3734. <a href="https://leetcode.cn/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/description/">大于目标字符串的最小字典序回文排列</a> 2330
     *
     * @param s
     * @param target
     * @return
     */
    public String lexPalindromicPermutation(String s, String target) {
        /*
        给你两个长度均为 n 的字符串 s 和目标字符串 target，它们都由小写英文字母组成。
        返回 字典序 最小的字符串 ，该字符串 既 是 s 的一个 回文 排列 ，又是字典序 严格 大于 target 的。
        如果不存在这样的排列，则返回一个空字符串。
        如果字符串 a 和字符串 b 长度相同，在它们首次出现不同的位置上，字符串 a 处的字母在字母表中的顺序晚于字符串 b 处的对应字母，则字符串 a 在 字典序上严格大于 字符串 b。
        排列 是指对字符串中所有字符的重新排列。
        如果一个字符串从前向后读和从后向前读都一样，则该字符串是 回文 的。
         */
        /*
        回文串，奇数字母不能出现 两次
         */
        int[] left = new int[26];
        for (char b : s.toCharArray()) {
            left[b - 'a']++;
        }

        String midCh = "";
        for (int i = 0; i < 26; i++) {
            int c = left[i];
            if (c % 2 == 0) {
                continue;
            }
            // s 不能有超过一个字母出现奇数次，不可能构成回文排列
            if (!midCh.isEmpty()) {
                return "";
            }
            // 记录填在正中间的字母
            midCh = String.valueOf((char) ('a' + i));
            left[i]--;
        }

        int n = s.length();
        // 先假设答案左半与 target 的左半（不含正中间）相同
        for (int i = 0; i < n / 2; i++) {
            left[target.charAt(i) - 'a'] -= 2;
        }
        // 记录 left 中负数的情况
        int neg = 0;
        int maxEnable = 0;
        for (int i = 0; i < 26; i++) {
            if (left[i] < 0) {
                neg++;
            } else if (left[i] > 0) {
                maxEnable = Math.max(maxEnable, i);
            }
        }


        if (neg == 0) {
            // 特殊情况：把 target 左半翻转到右半，能否比 target 大？
            String leftS = target.substring(0, n / 2);
            String rightS = midCh + new StringBuilder(leftS).reverse();
            if (rightS.compareTo(target.substring(n / 2)) > 0) { // 由于左半是一样的，所以只需比右半
                return leftS + rightS;
            }
        }
        // 枚举 [0, i] i n/2 - 1 位置是否能增加
        for (int i = n / 2 - 1; i >= 0; i--) {
            int b = target.charAt(i) - 'a';
            // 撤销消耗
            left[b] += 2;
            if (left[b] == 0) {
                neg--;
            } else if (left[b] == 2) {
                // 新增可用字符
                maxEnable = Math.max(maxEnable, b);
            }
            if (neg > 0 || maxEnable <= b) {
                // [0, i-1] 不匹配，i 位置不可变大
                continue;
            }

            // 把 target[i] 增大到 j，只会执行一次
            int j = b + 1;
            while (left[j] == 0) {
                j++;
            }
            // 找到答案
            left[j] -= 2;
            StringBuilder ans = new StringBuilder(target.substring(0, i + 1));
            ans.setCharAt(i, (char) ('a' + j));

            // 中间可以随便填
            for (int k = 0; k < 26; k++) {
                if (left[k] > 0) {
                    String c = String.valueOf((char) ('a' + k));
                    ans.append(c.repeat(left[k] / 2));
                }
            }

            // 镜像翻转
            StringBuilder rightS = new StringBuilder(ans).reverse();
            return ans.append(midCh).append(rightS).toString();
        }
        return "";
    }


}
