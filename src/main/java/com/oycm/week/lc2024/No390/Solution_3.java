package com.oycm.week.lc2024.No390;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_3 {

    /**
     * 3092. <a href="https://leetcode.cn/problems/most-frequent-ids/description/">最高频率的 ID</a> 1793
     *
     * @param nums
     * @param freq
     * @return
     */
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        /*
        你需要在一个集合里动态记录 ID 的出现频率。
        给你两个长度都为 n 的整数数组 nums 和 freq，nums 中每一个元素表示一个 ID，对应的 freq 中的元素表示这个 ID 在集合中此次操作后需要增加或者减少的数目。
            增加 ID 的数目：如果 freq[i] 是正数，那么 freq[i] 个 ID 为 nums[i] 的元素在第 i 步操作后会添加到集合中。
            减少 ID 的数目：如果 freq[i] 是负数，那么 -freq[i] 个 ID 为 nums[i] 的元素在第 i 步操作后会从集合中删除。
        请你返回一个长度为 n 的数组 ans ，其中 ans[i] 表示第 i 步操作后出现频率最高的 ID 数目 ，如果在某次操作后集合为空，那么 ans[i] 为 0。
            1 <= n <= 1e5
            -1e5 <= freq[i] <= 1e5
            输入保证任何操作后，集合中的元素出现次数不会为负数。
         */
        /*
        频率可以用 map 维护，怎么维护最大频率对应的 key？
        懒删除堆 维护一个 pair 对，key 表示 id，cnt 表示次数
         */
        int n = nums.length;
        long[] ans = new long[n];
        Map<Integer, Long> map = new HashMap<>();
        PriorityQueue<Pair> mx = new PriorityQueue<>((a, b) -> Long.compare(b.cnt, a.cnt));
        for (int i = 0; i < nums.length; i++) {
            long x = map.merge(nums[i], (long) freq[i], Long::sum);
            mx.add(new Pair(nums[i], x));
            while (mx.peek().cnt != map.get(mx.peek().key)) {
                mx.poll();
            }
            ans[i] = mx.peek().cnt;
        }

        return ans;
    }

    class Pair {
        Pair(int key, long cnt) {
            this.key = key;
            this.cnt = cnt;
        }
        int key;
        long cnt;
    }

}
