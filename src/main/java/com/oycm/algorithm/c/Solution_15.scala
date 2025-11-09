package com.oycm.algorithm.c

object Solution_15 {

  /**
   * 2760. 最长奇偶子数组
   * https://leetcode.cn/problems/longest-even-odd-subarray-with-threshold/description/
   *
   * 从 nums 的子数组中找出以下标 l 开头、下标 r 结尾(0 <= l <= r < nums.length)，且满足以下条件的最长子数组：
   *  - nums[l] % 2 == 0
   *  - 对于范围 [l, r - 1] 内的所有下标 i ，nums[i] % 2 != nums[i + 1] % 2
   *  - 对于范围 [l, r] 内的所有下标 i ，nums[i] <= threshold
   *
   * @param nums      整数数组
   * @param threshold 整数
   * @return
   */
  def longestAlternatingSubarray(nums: Array[Int], threshold: Int): Int = {
    /*
    当 元素小于等于 阈值时， nums[i] % 2 == nums[i + 1] % 2, i 就是 r
    i+1 是一个新的子数组的开始
    */
    var ans = 0
    val n = nums.length
    var l = -1
    var i = 0
    while (i < n) {
      if (nums(i) <= threshold && nums(i) % 2 == 0) {
        // 记录开始
        l = i
        i += 1
        while (i < n && nums(i) <= threshold && nums(i - 1) % 2 != nums(i) % 2) {
          i += 1
        }
        // 记录答案， i++ 才不匹配的，随意 i 不可取
        ans = Math.max(ans, i - l)
      } else {
        i += 1
      }

    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(longestAlternatingSubarray(Array(3, 2, 5, 4), 5))
    println(longestAlternatingSubarray(Array(1, 2), 2))
    println(longestAlternatingSubarray(Array(1, 3, 4, 4), 4))
    println(longestAlternatingSubarray(Array(2, 2), 18))
    println(longestAlternatingSubarray(Array(4, 10, 3), 10))
  }
}
