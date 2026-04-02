package com.oycm.algorithm.h.basic;


import java.util.*;
import java.util.stream.Collectors;

public class Solution_21 {

    /**
     * 3690. <a href="https://leetcode.cn/problems/split-and-merge-array-transformation/description/">拆分合并数组</a> 1982
     *
     * @param nums1 长度为 n 的整数数组 [2, 6]
     * @param nums2 长度为 n 的整数数组 [2, 6]
     * @return 求 nums1 转换成 nums2 的最少拆分合并操作次数
     */
    public int minSplitMerge(int[] nums1, int[] nums2) {
        if (Arrays.equals(nums1, nums2)) return 0;

        int[] sorted = nums1.clone();
        Arrays.sort(sorted);
        int n = nums1.length;
        int val1 = encode(nums1, sorted);
        int val2 = encode(nums2, sorted);
        Set<Integer> vis = new HashSet<>();
        vis.add(val1);
        List<Integer> q = List.of(val1);
        for (int ans = 1; ; ans++) {
            List<Integer> temp = q;
            q = new ArrayList<>();
            for (Integer cur : temp) {
                for (int r = 1; r <= n; r++) {
                    int t = cur & ((1 << (r * 3)) - 1);
                    for (int l = 0; l < r; l++) {
                        int sub = t >> (l * 3);
                        // cur 中删除 sub
                        int ps = (cur & ((1 << (l * 3)) - 1)) | (cur >> (r * 3) << (l * 3));
                        // 插入
                        for (int i = 0; i <= n - r + l; i++) {
                            int next = (ps & ((1 << (i * 3)) - 1)) | (sub << (i * 3)) | (ps >> (i * 3) << ((i + r - l) * 3));
                            if (next == val2) {
                                return ans;
                            }
                            if (vis.add(next)) {
                                q.add(next);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 数组最长为 6, 下标为 [0, 5] 使用 排序后的数组下标表示原数据值,
     * 3 个 bit 位表示一个数, 这样一个数组就可以用整数表示
     *
     * @param nums
     * @param sorted
     * @return
     */
    public int encode(int[] nums, int[] sorted) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = Arrays.binarySearch(sorted, nums[i]);
            res |= x << (i * 3);
        }
        return res;
    }

    static class Solution_21_1 {
        public int minSplitMerge(int[] nums1, int[] nums2) {
        /*
        nums1 执行任意次下述的 拆分合并操作:
            选择一个子数组 nums1 [L, R];
            移除该子数组，留下前缀 nums1[0..L-1]（如果 L=0 则为空）和后缀 nums1[R+1..n-1]（如果 R=n-1 则为空）;
            将移除的子数组（原顺序）重新插入到剩余数组的任意位置（最开始或最后面）
         */
        /*
        题解思路: BFS 暴力搜索
         */
            int n = nums1.length;
            List<Integer> num2List = toList(nums2);
            // 表示前面已经拆分过的点
            Set<List<Integer>> vis = new HashSet<>();
            List<List<Integer>> q = List.of(toList(nums1));
            vis.add(q.get(0));
            for (int ans = 0; ; ans++) {
                List<List<Integer>> temp = q;
                q = new ArrayList<>();
                for (List<Integer> cur : temp) {
                    if (cur.equals(num2List)) {
                        return ans;
                    }
                    for (int l = 0; l < n; l++) {
                        for (int r = l + 1; r <= n; r++) {
                            List<Integer> sub = cur.subList(l, r);
                            List<Integer> ps = new ArrayList<>(cur);
                            // 剩余的前后缀数组
                            ps.subList(l, r).clear();
                            // 模拟插入位置
                            for (int i = 0; i <= ps.size(); i++) {
                                List<Integer> next = new ArrayList<>(ps);
                                next.addAll(i, sub);
                                if (vis.add(next)) {
                                    q.add(next);
                                }
                            }
                        }
                    }

                }
            }
        }

        public List<Integer> toList(int[] nums) {
            return Arrays.stream(nums).boxed().collect(Collectors.toList());
        }
    }

}
