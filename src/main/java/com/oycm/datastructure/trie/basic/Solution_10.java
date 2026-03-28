package com.oycm.datastructure.trie.basic;

import java.util.HashMap;
import java.util.Map;

public class Solution_10 {

    /**
     * 2261. <a href="https://leetcode.cn/problems/k-divisible-elements-subarrays/description/">含最多 K 个可整除元素的子数组</a> 1724
     *
     * @param nums 整数数组
     * @param k
     * @param p
     * @return
     */
    public int countDistinct(int[] nums, int k, int p) {
        /*
        找出并返回满足要求的不同的子数组数，要求子数组中最多 k 个可被 p 整除的元素
        不同数组要求：
            两数组长度 不同 ，或者
            存在 至少 一个下标 i 满足 nums1[i] != nums2[i]
         */
        /*
        nums = {2,3,3,2,2,2} k = 2, p = 2
        前面的 2,2 和 后面的 2,2 算相同子数组
        nums[i] 从 i = 0 遍历到 n-1, 当 nums[i] % p == 0 的次数小于等于 k 时构建一个字典树，固定 i 为左端点的字典树数目是节点的数目
        如果前面字典树存在相同的路径，前面的节点数是相同数组，不能记录答案
         */
        int ans = 0, n = nums.length;
        Trie root = new Trie();
        for (int i = 0; i < n; i++) {
            int j = i, cnt = 0, start = i;
            Trie cur = root;
            while (j < n && cnt <= k) {
                if (nums[j] % p == 0) {
                    cnt++;
                    if (cnt > k) break;
                }
                Trie son = cur.son.get(nums[j]);
                if (son == null) {
                    son = new Trie();
                    cur.son.put(nums[j], son);
                } else {
                    // 和前面的前缀相同，是相同子数组
                    start++;
                }
                cur = son;
                j++;
            }
            ans += j - start;

        }

        return ans;
    }

    static class Trie {
        Map<Integer, Trie> son = new HashMap<>();
    }

}
