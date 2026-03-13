package com.oycm.month2026.march;

public class Solution_13 {

    /**
     * 3296. <a href="https://leetcode.cn/problems/minimum-number-of-seconds-to-make-mountain-height-zero/description/">移山所需的最少秒数</a> 1695
     * <p>
     * 工人们需要 同时 进行工作以 降低 山的高度, 对于工人 i
     * 山的高度降低 x，需要花费 workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x 秒
     *
     * @param mountainHeight 山的高度
     * @param workerTimes    工人们的工作时间
     * @return
     */
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        /*
        二分答案-求最小
         */
        int maxWorkerTime = 0;
        for (int workerTime : workerTimes) {
            maxWorkerTime = Math.max(workerTime, maxWorkerTime);
        }

        long left = 0, right = (long) maxWorkerTime * mountainHeight * (mountainHeight + 1) / 2;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (check(mountainHeight, workerTimes, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    public boolean check(int mountainHeight, int[] workerTimes, long time) {
        long totalHeight = 0;
        for (int workerTime : workerTimes) {
            /*
            计算每个工人 time 时间，降低山的最高高度
            h * (h + 1) / 2 * workerTime <= time
            h * (h + 1) / 2 <= time / workerTime
            h * (h + 1) = 2*k
            h = (-b +- sqrt(b^2 - 4ac) ) / 2a
             */
            long k = time / workerTime;
            totalHeight += (long) (Math.sqrt(1 + 8 * k) - 1) / 2;
            if (totalHeight >= mountainHeight) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_13().minNumberOfSeconds(1, new int[]{2, 1}));
    }
}
