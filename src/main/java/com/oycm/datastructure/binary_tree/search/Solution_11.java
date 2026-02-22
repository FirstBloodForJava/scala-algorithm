package com.oycm.datastructure.binary_tree.search;

import com.oycm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_11 {

    /**
     * 2476. <a href="https://leetcode.cn/problems/closest-nodes-queries-in-a-binary-search-tree/description/">二叉搜索树最近节点查询</a> 1597
     *
     * @param root
     * @param queries
     * @return
     */
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        /*
        先 中序遍历得到 有序数组, 再 二分查找

        先查找 大于等于 queries[i] 的 最小值, 如果不存在, 返回 -1; 如果存在, 当前值 等于 queries[i] 则 min 也是当前值, 否则 idx-- 是最小最大值
         */
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int n = list.size();
        int[] nums = new int[n];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }

        List<List<Integer>> ans = new ArrayList<>(queries.size());

        for (Integer query : queries) {
            int maxIdx = lowerBound(nums, query);
            int max = maxIdx == n ? -1 : nums[maxIdx];
            if (maxIdx == n || nums[maxIdx] != query) {
                maxIdx--;
            }
            int min = maxIdx < 0 ? -1 : nums[maxIdx];
            ans.add(List.of(min, max));
        }

        return ans;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

    private int findMin(List<Integer> list, int target) {
        int left = -1, right = list.size();
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left == -1 ? left : list.get(left);
    }

    private int findMax(List<Integer> list, int target) {
        int left = -1, right = list.size();
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right == list.size() ? -1 : list.get(right);
    }

    private int lowerBound(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right ;
    }

}
