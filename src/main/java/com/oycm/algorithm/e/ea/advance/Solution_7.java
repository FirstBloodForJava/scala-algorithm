package com.oycm.algorithm.e.ea.advance;

public class Solution_7 {

    /**
     * 1031. <a href="https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays/description/">两个无重叠子数组的最大和</a> 1680
     * 找出长为 firstLen, secondLen 不重叠子数组的最大和
     *
     * @param nums
     * @param firstLen
     * @param secondLen
     * @return
     */
    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        /*
        暴力计算 [0, n - firstLen] 其对应可取 不重叠的 secondLen 的最大值，不断更新
         */
        int ans = 0;
        int n = nums.length;
        int first = 0;
        for (int i = 0; i < n; i++) {
            first += nums[i];
            int left = i - firstLen + 1;
            if (left >= 0) {
                // 计算 [0, left) 和 (i, n) 长为 secondLen 的最大值
                int sum = 0;
                int maxSum = 0;
                if (left >= secondLen) {
                    for (int j = 0; j < left; j++) {
                        sum += nums[j];
                        if (j - secondLen + 1 >= 0) {
                            maxSum = Math.max(sum, maxSum);
                            sum -= nums[j - secondLen + 1];
                        }
                    }
                }
                if (n - i - 1 >= secondLen) {
                    sum = 0;
                    for (int j = i + 1; j < n; j++) {
                        sum += nums[j];
                        // j - (i+1) + 1 >= secondLen
                        if (j - i >= secondLen) {
                            maxSum = Math.max(sum, maxSum);
                            sum -= nums[j - secondLen + 1];
                        }
                    }
                }
                ans = Math.max(ans, first + maxSum);
                first -= nums[left];
            }
        }


        return ans;
    }


    public static int answer(int[] nums, int firstLen, int secondLen) {
        /*
        题解思路: 前缀和
        firstLen 表示数组 a, 假设在左边, secondLen 表示数组在 右边
        a => [0, firstLen-1], b => [firstLen, firstLen + secondLen - 1], 每次移动，同时更新 a, b 数组的最大值
        i 在前缀和数组移动的范围 [firstLen, n - secondLen], 这里的 n 是 nums.length
        sa = s[i] - s[firstLen]
        sb = s[i+secondLen] - s[i]
        可以定义一个方法计算 左 a 右 b, 也就能计算 左 b 右 a 了
         */
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        return Math.max(sum(s, firstLen, secondLen), sum(s, secondLen, firstLen));
    }

    public static int sum(int[] s, int firstLen, int secondLen) {
//        int a = 0;
//        int b = 0;
//        for (int i = firstLen; i < s.length - secondLen; i++) {
//            a = Math.max(a, s[i] - s[i - firstLen]);
//            b = Math.max(b, s[i + secondLen] - s[i]);
//        }
//        return a + b;
        // 注意 不能按上面的方式计算答案，上面的最大值数组会有交集 必须每次计算过程中更新答案
        int ans = 0;
        int maxA = 0;
        for (int i = firstLen; i < s.length - secondLen; i++) {
            maxA = Math.max(maxA, s[i] - s[i - firstLen]);
            ans = Math.max(ans, maxA + s[i + secondLen] - s[i]);
        }

        return ans;
    }


    public int optimize(int[] nums, int firstLen, int secondLen) {
        /*
        一次循环解决
         */
        int ans = 0;
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }
        int maxA = 0;
        int maxB = 0;
        for (int i = firstLen + secondLen; i < n + 1; i++) {
            maxA = Math.max(maxA, s[i - secondLen] - s[i - secondLen - firstLen]);
            maxB = Math.max(maxB, s[i - firstLen] - s[i - secondLen - firstLen]);
            ans = Math.max(ans, Math.max(maxA + s[i] - s[i - secondLen], maxB + s[i] - s[i - firstLen]));
        }

        return ans;
    }

    public static void main(String[] args) {
        maxSumTwoNoOverlap(new int[]{8, 20, 6, 2, 20, 17, 6, 3, 20, 8, 12}, 5, 4);
        answer(new int[]{2, 1, 5, 6, 0, 9, 5, 0, 3, 8}, 4, 3);
    }
}
