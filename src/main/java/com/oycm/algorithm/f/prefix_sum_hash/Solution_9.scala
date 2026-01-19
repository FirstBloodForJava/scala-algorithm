package com.oycm.algorithm.f.prefix_sum_hash

object Solution_9 {

  /**
   * 3026. 最大好子数组和 1817
   * https://leetcode.cn/problems/maximum-good-subarray-sum/description/
   *
   * 好子数组：[i, j] 满足 |nums(i) - nums(j)| == k
   *
   * @param nums
   * @param k
   * @return 求 nums 好子数组的最大和
   */
  def maximumSubarraySum(nums: Array[Int], k: Int): Long = {
    /*
    枚举 j 查找 nums(j) - k 和 nums(j) + k 的 前缀和 sums(j+1) - sums(i)
     */
    var ans = Long.MinValue
    var sum = 0L
    // 相同 的 nums(i) 取较小前缀和
    val map = scala.collection.mutable.Map[Int, Long]()

    for (i <- nums.indices) {
      sum += nums(i)
      val k1 = nums(i) - k
      val k2 = nums(i) + k

      if (map.contains(k1)) {
        ans = Math.max(ans, sum - map(k1) + k1)
      }
      if (map.contains(k2)) {
        ans = Math.max(ans, sum - map(k2) + k2)
      }

      if (map.contains(nums(i))) {
        map(nums(i)) = Math.min(sum, map(nums(i)))
      } else {
        map(nums(i)) = sum
      }

    }
    if (ans == Long.MinValue) {
      ans = 0
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumSubarraySum(Array(1, 2, 3, 4, 5, 6), 1))
    println(maximumSubarraySum(Array(-1, 3, 2, 4, 5), 3))
    println(maximumSubarraySum(Array(-1, -2, -3, -4), 2))
  }

}
