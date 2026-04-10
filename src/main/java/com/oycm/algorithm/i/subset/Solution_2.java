package com.oycm.algorithm.i.subset;

import java.util.ArrayList;
import java.util.List;

public class Solution_2 {

    /**
     * 1863. <a href="https://leetcode.cn/problems/sum-of-all-subset-xor-totals/description/">找出所有子集的异或总和再求和</a> 1372
     *
     * @param nums [1, 12]
     * @return
     */
    public int subsetXORSum(int[] nums) {
        /*
        异或总和: 为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0
        元素 相同 的不同子集应 多次 计数, 3,3
         */
        /*
        找到子集后, 再计算异或和, 再相加
        枚举子集答案
         */
        dfs(0, nums, new ArrayList<>());
        return sum;
    }

    int sum = 0;

    public void dfs(int i, int[] nums, List<Integer> path) {
        // 选好之后，就是答案
        int xor = 0;
        for (int x : path) {
            xor ^= x;
        }
        sum += xor;
        for (int j = i; j < nums.length; j++) {
            path.add(nums[j]);
            // 下一个子问题
            dfs(j + 1, nums, path);
            // 选完之后还原, 变成不选它
            path.remove(path.size() - 1);
        }
    }

}

class Solution_2_InputPerspective {

    public int subsetXORSum(int[] nums) {
        /*
        异或总和: 为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0
        元素 相同 的不同子集应 多次 计数, 3,3
         */
        /*
        找到子集后, 再计算异或和, 再相加
         */
        /*
        选/不选 思路
        回溯三问：
            当前操作？枚举第 i 个 选/不选
            子问题？从下标 >= i 构造子集
            下一个子问题？从下标 >= i+1 构造子集
         */
        dfs(0, nums, new ArrayList<>());
        return sum;
    }

    int sum = 0;

    public void dfs(int i, int[] nums, List<Integer> path) {
        if (i == nums.length) {
            int xor = 0;
            for (int x : path) {
                xor ^= x;
            }
            sum += xor;
            return;
        }
        // 不选
        dfs(i + 1, nums, path);
        // 选
        path.add(nums[i]);
        dfs(i + 1, nums, path);
        // 选完之后还原
        path.remove(path.size() - 1);
    }

}