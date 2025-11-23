package com.oycm.algorithm.f.basic

class Solution_1 {

  /**
   * 303. 区域和检索 - 数组不可变
   * https://leetcode.cn/problems/range-sum-query-immutable/description/
   *
   * NumArray 类 使用数组 nums 初始化对象
   *
   * sumRange 返回数组 nums 中索引 left 和 right 之间的元素的 总和, [left, right]
   *
   * @param _nums
   */
  class NumArray(_nums: Array[Int]) {
    val sums = Array.fill(_nums.length + 1)(0)
    for (i <- _nums.indices) {
      sums(i + 1) = sums(i) + _nums(i)
    }

    def sumRange(left: Int, right: Int): Int = {

      sums(right + 1) - sums(left)
    }

  }
}
