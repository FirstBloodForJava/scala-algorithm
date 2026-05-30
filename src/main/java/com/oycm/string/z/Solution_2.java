package com.oycm.string.z;

public class Solution_2 {

    /**
     * 3031. <a href="https://leetcode.cn/problems/minimum-time-to-revert-word-to-initial-state-ii/description/">将单词恢复初始状态所需的最短时间 II</a> 2278
     *
     * @param word
     * @param k
     * @return
     */
    public int minimumTimeToInitialState(String word, int k) {
        /*
        给你一个下标从 0 开始的字符串 word 和一个整数 k。
        在每一秒，你必须执行以下操作：
            移除 word 的前 k 个字符。
            在 word 的末尾添加 k 个任意字符。
        注意 添加的字符不必和移除的字符相同。但是，必须在每一秒钟都执行 两种 操作。
         */
        /*
        计算 z 函数，如果 i % k = 0，且 z[i] 后缀字符串和前缀匹配最大长度 = n-i，则把前面删除直接在后面补上即可
        如果没有以上匹配条件，则删除所以字符串 n / k 上取整，按顺序添加即可
        word = abede, k = 3 第一次添加 [0, 2); 第二次 [2, 5) 不足 k 的前面添加任意字符即可
         */
        char[] cs = word.toCharArray();
        int n = cs.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && cs[z[i]] == cs[i + z[i]]) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
            if (i % k == 0 && z[i] == n - i) {
                return i / k;
            }
        }
        return (n - 1) / k + 1;
    }

}
