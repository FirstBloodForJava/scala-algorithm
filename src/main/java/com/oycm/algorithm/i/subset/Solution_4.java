package com.oycm.algorithm.i.subset;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution_4 {

    /**
     * 3566. <a href="https://leetcode.cn/problems/partition-array-into-two-equal-product-subsets/description/">等积子集的划分方案</a> 1459
     *
     * @param nums   整数数组, 正整数 互不相同, nums.length [3, 12]; nums[i] [1, 100]
     * @param target 整数 [1, 1e15]
     * @return
     */
    public boolean checkEqualPartitions(int[] nums, long target) {
        /*
        判断是否可以将 nums 分成两个 非空、互不相交 的 子集, 使得这两个子集中元素的乘积都等于 target
         */
        /*
        题解思路: 折半枚举
        两个子集乘积 不等于 target, 则返回 false
        把 nums 分组成两份, 记为数组 A, B
            在 数组 A 中, 选中一些数, 放入第一个集合, 乘积记为 a1; 其余记为第二个集合, 乘积记为 b1
            在 数组 B 中, 选中一些数, 放入第一个集合, 乘积记为 a2; 其余记为第二个集合, 乘积记为 b2
        有前提条件 a1 * b1 * a 2 * b2 == target ^ 2
        若 a1 * a2 == b1 * b2 则 存在符合条件的子集
        等式转换 a1 / b1 == b2 / a2
        前一半元素, 用 set 记录 a1/b1 的最简约分; 虽然 b2/a2 看似是 第二个集合/第一个集合, 其实是等价的,
        数组 A, B 执行同样的代码, 选中的元素记为第一个集合中的元素, 到执行 B, 可以当作 选中元素为进入第二个集合
         */
        BigInteger prodAll = Arrays.stream(nums)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
        if (!prodAll.equals(BigInteger.valueOf(target).pow(2))) return false;
        int m = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, m);
        int[] right = Arrays.copyOfRange(nums, m, nums.length);

        Set<Pair> set1 = getSet(left, target);
        Set<Pair> set2 = getSet(right, target);
        for (Pair pair : set2) {
            if (set1.contains(pair)) {
                return true;
            }
        }

        return false;
    }

    private record Pair(long a, long b) {
    }

    public Set<Pair> getSet(int[] nums, long target) {
        Set<Pair> set = new HashSet<>();
        dfs(0, 1, 1, nums, target, set);
        return set;
    }

    public void dfs(int i, long mul1, long mul2, int[] nums, long target, Set<Pair> set) {
        if (mul1 > target || mul2 > target) return;
        if (i == nums.length) {
            long g = gcd(mul1, mul2);
            set.add(new Pair(mul1 / g, mul2 / g));
            return;
        }
        dfs(i + 1, mul1 * nums[i], mul2, nums, target, set);
        dfs(i + 1, mul1, mul2 * nums[i], nums, target, set);
    }

    public long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


}

class Solution_4_InputPerspective {
    public boolean checkEqualPartitions(int[] nums, long target) {
        /*
        两个子集乘积 都等于 target, 等价于 整个数组的乘积要是 target 的平方, 用 long 计算已经溢出了
            这是错判的, [1, 2, 8] 4, 答案 false
        用 选/不选 思路, 选的乘积子集, 还需要快速知道另一个子集的乘积, 还是需要所有数的乘积
        回溯三问：
            当前操作？枚举第 i 个 选的 乘积 mul1 子集, 不选的乘积 mul2 子集
            子问题？从下标 >= i 构造子集 选或不选
            下一个子问题？从下标 >= i+1 构造子集
         */

        return dfs(0, 1, 1, nums, target);
    }

    public boolean dfs(int i, long mul1, long mul2, int[] nums, long target) {
        if (mul1 > target || mul2 > target) return false;
        if (i == nums.length) return mul1 == target && mul2 == target;
        return dfs(i + 1, mul1 * nums[i], mul2, nums, target) || dfs(i + 1, mul1, mul2 * nums[i], nums, target);
    }
}
