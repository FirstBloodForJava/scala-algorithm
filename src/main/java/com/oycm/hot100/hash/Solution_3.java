package com.oycm.hot100.hash;

import java.util.HashSet;
import java.util.Set;

public class Solution_3 {

    /**
     * 128. <a href="https://leetcode.cn/problems/longest-consecutive-sequence/description/">最长连续序列</a>
     *
     * @param nums 未排序的整数数组; nums[i] [-1e9, 1e9]; nums.length [0, 1e5]
     * @return
     */
    public int longestConsecutive(int[] nums) {
        /*
        找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度
        nums = [100,4,200,1,3,2], 最长数字连续序列是 [1, 2, 3, 4]
         */
        /*
        map key 为 nums[i]; value 为二元组，表示 nums[i] 连续数字区间最小值，最大值
        对于 nums[i]，存在以下几种情况：
            map 中存在 nums[i]，跳过处理；
            nums[i] 的区间计算，d = {l, r}
            查找 map 中是否存在 nums[i]-1，存在 value，则 d[0] = min(value[0], nums[i] - 1)，value[1] 要判断是否更新
            查找 map 中是否存在 nums[i]+1，存在 value，则 d[1] = max(value[1], nums[i] + 1)，value[0] 要判断是否更新
            更新 ans = max(ans, r - l + 1);
        上面的思路，中间有断开的 [0,3,7,2,5,8,4,6,0,1] 计算 1 时，2 的区间没有更新为 2, 8 导致最后的结果为 7
         */
        int ans = 0;
        /*Map<Integer, int[]> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int l = num, r = num;

            int[] left = map.get(num - 1);
            if (left != null) {
                l = Math.min(num - 1, left[0]);
            }

            int[] right = map.get(num + 1);
            if (right != null) {
                r = Math.max(num + 1, right[1]);
            }

            if (left != null) {
                left[1] = Math.max(left[1], r);
            }
            if (right != null) {
                right[0] = Math.min(right[0], l);
            }

            ans = Math.max(ans, r - l + 1);
            map.put(num, new int[]{l, r});
        }*/
        /*
        题解思路：对于 nums 中的元素 x，以 x 为起点，不断查找 x+1, x+2, ... 是否在 nums 中，并统计序列的长度。
        解决过程：
            先把 nums 添加到 hash 表中，这样可以 O(1) 的判断数字是否在 nums 中；
            如果 x - 1 在 nums 中，则不以 x 作为起点计算序列长度。
        如果 x-1 在序列中，x-1 作为计算的长度肯定 > x 作为起点的长度。这样能避免大量的计算。
         */
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        /*
        优化点：不同元素的个数记为 m，不同连续数字链是独立的，如果一条链的长度至少为 m/2↑，不可能存在一条大于 m/2↑ 链
         */

        for (Integer x : set) {
            if (set.contains(x - 1)) {
                continue;
            }
            // 作为起点的 x 之后的元素至多遍历 2 次，x+1 不会进入内层循环
            int y = x + 1;
            while (set.contains(y)) {
                y++;
            }
            ans = Math.max(ans, y - x);
            if (ans * 2 >= set.size()) {
                break;
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        Solution_3 solution = new Solution_3();
        System.out.println(solution.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

}
