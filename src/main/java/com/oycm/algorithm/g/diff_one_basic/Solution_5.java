package com.oycm.algorithm.g.diff_one_basic;

public class Solution_5 {

    /**
     * 2960. <a href="https://leetcode.cn/problems/count-tested-devices-after-test-operations/description/">统计已测试设备</a> 1169
     *
     * @param batteryPercentages 长度为 n, 整数数组
     * @return
     */
    public int countTestedDevices(int[] batteryPercentages) {
        /*
        按照顺序测试每个设备 i，执行以下测试操作:
            如果 batteryPercentages[i] 大于 0
                增加 已测试设备的计数;
                将下标 j 在 [i + 1, n - 1] 的所有设备的电池百分比减少 1，确保它们的电池百分比 不会低于 0 ，即 batteryPercentages[j] = max(0, batteryPercentages[j] - 1);
                移动到下一个设备;
            否则，移动到下一个设备而不执行任何测试
         */
        /*
        累计差分, 记 需要减少的差 为 diff
            如果 nums[i] > 0 && nums[i] > diff (只看这个就可以), ans++ diff++
            否则 跳过
         */
        int ans = 0;
        for (int i = 0; i < batteryPercentages.length; i++) {
            if (batteryPercentages[i] > ans) {
                ans++;
            }
        }

        return ans;
    }

}
