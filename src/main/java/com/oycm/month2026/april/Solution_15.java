package com.oycm.month2026.april;

public class Solution_15 {

    /**
     * 2515. <a href="https://leetcode.cn/problems/shortest-distance-to-target-string-in-a-circular-array/description/">到目标字符串的最短距离</a> 1367
     *
     * @param words      环形字符串数组，words[i] 的下一个元素是 words[(i + 1) % n]；words[i] 的前一个元素是 words[(i - 1 + n) % n]
     * @param target     目标字符串
     * @param startIndex 环形数组开始下标
     * @return 从 startIndex 开始，你一次可以用 1 步移动到下一个或者前一个单词，返回到达目标字符串 target 所需的最短距离。words 中不存在 target 则返回 -1
     */
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = n;
        /*
        从 startIndex 开始，一直玩后移动，直到移动到 startIndex
            如果出现相等的，pre 计算分为两种情况:
                i < startIndex, step = startIndex - i;
                i > startIndex, step = startIndex + n - i;
         */
        /*
        直接从 0 遍历到 n-1, 如果 word[i] == target, 计算 i 到 startIndex 不走循环的距离，n - d 就是循环的距离，两者取最小
         */
        /*
        枚举答案 [0, 1, 2 ... n/2] 是否存在
         */
        for (int k = 0; k <= n / 2; k++) {
            if (target.equals(words[(startIndex + k) % n]) || target.equals(words[(startIndex - k + n) % n])) {
                return k;
            }
        }

        return ans == n ? -1 : ans;
    }

}
