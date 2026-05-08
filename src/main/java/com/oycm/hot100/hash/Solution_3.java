package com.oycm.hot100.hash;

import java.util.*;

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

class Solution_2007 {

    /**
     * 2007. <a href="https://leetcode.cn/problems/find-original-array-from-doubled-array/description/">从双倍数组中还原原数组</a> 1557
     *
     * @param changed
     * @return
     */
    public int[] findOriginalArray(int[] changed) {
        /*
        一个整数数组 original 可以转变成一个 双倍数组 changed ，转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱。
        给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以 任意 顺序返回。
         */
        /*
        changed.length % 2 == 0 长度要为偶数；
        记录 nums 中元素 x 及次数
        排序后，从左到右遍历 nums，x 可用次数 -1, 查找是否存在可用的 2x，不存在则返回空数组；存在则减少次数更新 ans
        如果 x % 2 == 1，这个数只能作为 original 元素，看是否存在 2x 元素，不存在则返回 空数组
         */
        int n = changed.length;
        if (n % 2 != 0) return new int[0];
        Arrays.sort(changed);
        int[] ans = new int[n / 2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : changed) {
            map.merge(x, 1, Integer::sum);
        }
        int i = 0;
        for (int x : changed) {
            if (map.getOrDefault(x, 0) == 0) {
                continue;
            }
            map.merge(x, -1, Integer::sum);
            if (map.getOrDefault(2 * x, 0) == 0) {
                return new int[0];
            }
            ans[i++] = x;
            map.merge(2 * x, -1, Integer::sum);

        }

        return ans;
    }

    public int[] findOriginalArray_2(int[] changed) {
        /*
        changed.length % 2 == 0 长度要为偶数；
        map 记录 nums 中元素 x 及次数

        如果 x 为奇数，则必须作为 original 元素；
        如果 x 为偶数，map 中 存在 x/2，不应该以 x 作为起点，寻找 original 元素
        x, 2x 数量匹配 4x 存在则查看 8x 是否存在数量匹配
         */

        int n = changed.length;
        if (n % 2 != 0) return new int[0];
        int m = n / 2;
        int[] ans = new int[m];
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : changed) {
            map.merge(x, 1, Integer::sum);
        }
        int i = 0;
        for (Map.Entry<Integer, Integer> kv : map.entrySet()) {
            // x 已配对
            if (kv.getValue() == 0) {
                continue;
            }
            int x = kv.getKey();
            if (x % 2 == 1 || !map.containsKey(x / 2)) {
                int next = x * 2;
                // 数量不匹配
                if (kv.getValue() > map.getOrDefault(next, 0)) {
                    return new int[0];
                }
                // 数量清零
                int cnt = kv.getValue();
                kv.setValue(0);
                map.merge(next, -cnt, Integer::sum);


            }
        }

        return ans;
    }

}
