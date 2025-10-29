package com.oycm.algorithm.abb.basic

object Solution_4 {

  /**
   * 2875.无限数组的最短子数组 1914
   * https://leetcode.cn/problems/minimum-size-subarray-in-infinite-array/description/
   *
   * infinite_nums 数组表示 nums 数组的无限循环
   * 找出 infinite_nums 数组中和等于 target 的最短子数组
   *
   * @param nums   长度为 n 的数组，nums[i] 大于 0
   * @param target 整数
   * @return infinite_nums 数组中和等于 target 的最短子数组
   */
  def minSizeSubarray(nums: Array[Int], target: Int): Int = {
    var ans = Int.MaxValue
    /**
     * 假设存在 子数组和为 target，则子数组应该只有以下两种情况
     *  - 当 target <= sums(n) nums 的子数组 [0, 2n -1]，
     *  - 当 target > sums(n) pre_sums(i) + k * sums(n) + suf_sums(j)，k = target / total
     */
    val n = nums.length
    // 先计算总合
    var sum = 0L
    for (i <- nums.indices) {
      sum += nums(i)
    }
    // 问题转换成在长度 2n 的数组中求 和为 (target % sum) 的最短子数组
    // 暴力遍历：[l,2n-1] l 范围 [0, 2n-1]
    // 由于 nums(i) >= 1，当 [l,r] 大于 目标值，l 就可以舍弃了，进入 l+1
    val rem = target % sum
    var l = 0
    var temp = 0
    // 为什么没有对 rem = 0 做处理？做处理无非就是少循环罢了
    for (r <- 0 until 2 * n) {
      temp += nums(r % n)
      while (temp > rem) {
        temp -= nums(l % n)
        l += 1
      }
      if (temp == rem) {
        // 当 rem = 0 时 这里结果会是 0
        ans = Math.min(ans, r - l + 1)
      }
    }
    if (ans == Int.MaxValue) {
      ans = -1
    } else {
      ans += (target / sum * n).toInt
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minSizeSubarray(Array(1, 2, 3), 6) == 3)

    println(minSizeSubarray(Array(1, 2, 3), 5) == 2)
    println(minSizeSubarray(Array(1, 1, 1, 2, 3), 4) == 2)
    println(minSizeSubarray(Array(2, 4, 6, 8), 3) == -1)
  }
}
