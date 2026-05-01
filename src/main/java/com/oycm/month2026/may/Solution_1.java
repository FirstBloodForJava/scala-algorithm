package com.oycm.month2026.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_1 {

    /**
     * 396. <a href="https://leetcode.cn/problems/rotate-function/description/">旋转函数</a>
     *
     * @param nums 长度为 n 的整数数组 , n [1, 1e5]
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        /*
        假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
            F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
            arr1 = nums 是循环数组，向前移动一步
         */
        /*
        设 n = nums.length = 4
        F(0) = 0 * nums[0] + 1 * nums[1] + 2 * nums[2] + 3 * nums[3]
        F(1) = 0 * nums[3] + 1 * nums[0] + 2 * nums[1] + 3 * nums[2]
        观察组成变化：
            0, 1, 2 往右移动，都增加了 nums[i]
            3 移动到前面，减少了 3 * nums[3]
        F(1) = F[0] + nums[0] + nums[1] + nums[2] - 3 * nums[3]
             = F[0] + nums[0] + nums[1] + nums[2] + nums[3] - 4 * nums[3]
             = F[1] + S + 4 * nums[3]
        S 表示数组和
        F(2) = 0 * nums[2] + 1 * nums[3] + 2 * nums[0] + 3 * nums[1]
             = F(1) + nums[3] + nums[0] + nums[1] - 3 * nums[2]
             = F(1) + S - 4 * nums[2]
        先初始化 F[0]
        F[i] = F[i-1] + S - n * nums[n-i]
         */
        int n = nums.length;
        int f = 0;
        int s = 0;
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
            s += nums[i];
        }
        int ans = f;
        for (int i = 1; i < n; i++) {
            f += s - n * nums[n - i];
            ans = Math.max(ans, f);
        }

        return ans;
    }

}

class Solution_2121 {

    /**
     * 2121. <a href="https://leetcode.cn/problems/intervals-between-identical-elements/description/">相同元素的间隔之和</a> 1761
     * 同 2615
     * @param arr 长度为 n 的整数数组 , n [1, 1e5]
     * @return
     */
    public long[] getDistances(int[] arr) {
        /*
        arr 中两个元素的 间隔 定义为它们下标之间的 绝对差。
        返回一个长度为 n 的数组 intervals ，其中 intervals[i] 是 arr[i] 和 arr 中每个相同元素（与 arr[i] 的值相同）的 间隔之和 。
         */
        /*
        考虑前一个点和下一个点的距离变化量
        1, 3, 5, 7
        a0 = arr[1] = 2 + 4 + 6 = 12
        a1 = arr[3] = 2 + 2 + 4 = 8
        a1 = a0 + (3-1) + (-2*3)
            (3-1) 表示新加的 1, 3 之间的距离
            (-2*3) 表示 (1,3), (1,5), (1,7) 变成 (3,3), (3,5), (3,7) 都变少了 -2
        a1 和 a0 相比较，左边多个一个数 1，增加距离 3-1; 右边距离[(1,3), (1,5), (1,7)] 变成了 [(3,3), (3,5), (3,7)] 距离变短了 -2 * 3

        a1 = a0 + i * (diff) - diff * (m - i);
        a1 = a0 + (2 * i - m) * diff
         */
        int n = arr.length;
        long[] intervals = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], l -> new ArrayList<>()).add(i);
        }
        for (List<Integer> value : map.values()) {
            long s = 0;
            int start = value.get(0);
            for (int x : value) {
                s += x - start;
            }
            int m = value.size();
            intervals[start] = s;
            for (int i = 1; i < m; i++) {
                intervals[value.get(i)] = s += (2L * i - m) * (value.get(i) - value.get(i - 1));
            }
        }

        return intervals;
    }
}


