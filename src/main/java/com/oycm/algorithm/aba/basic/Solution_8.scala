package com.oycm.algorithm.aba.basic

object Solution_8 {

  /**
   * 2958. 最多 K 个重复元素的最长子数组 1535
   * https://leetcode.cn/problems/length-of-longest-subarray-with-at-most-k-frequency/description/
   *
   * 频率：一个元素在数组中出现的次数
   *
   * 如果一个数组中所有元素的频率都 小于等于 k ，那么我们称这个数组是 好数组。
   *
   * @param nums 整数数组
   * @param k    整数
   * @return 返回 nums 中 最长好子数组 的长度。
   */
  def maxSubarrayLength(nums: Array[Int], k: Int): Int = {
    /*
    map(num, time) 记录元素出现的次数
    r 不断右移 如果 map(nums(r) > k)，l 右移, 直到次数小于等于 k 记录答案

    时间复杂度 O(n)
    空间复杂度 O(n)
     */
    var ans = 0
    val map = scala.collection.mutable.Map[Int, Int]()
    var l = 0
    for (r <- nums.indices) {
      map(nums(r)) = map.getOrElse(nums(r), 0) + 1
      while (map(nums(r)) > k) {
        map(nums(l)) = map(nums(l)) - 1
        l += 1
      }
      ans = Math.max(ans, r - l + 1)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxSubarrayLength(Array(1, 2, 3, 1, 2, 3, 1, 2), 2))
    println(maxSubarrayLength(Array(1, 2, 1, 2, 1, 2, 1, 2), 1))
    println(maxSubarrayLength(Array(5, 5, 5, 5, 5, 5, 5), 4))
    println(maxSubarrayLength(Array(1, 4, 4, 3), 1))
  }
}
