package com.oycm.algorithm.aba.basic

object Solution_3 {

  /**
   * 1493.删掉一个元素以后全为 1 的最长子数组 1423
   * https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/
   * 删掉一个元素，返回最长的 且只包含 1 的非空子数组的长度。
   *
   * @param nums 二进制数组(只有 0|1)
   * @return 删掉一个元素，返回最长的 且只包含 1 的非空子数组的长度。没有则返回 0
   */
  def longestSubarray(nums: Array[Int]): Int = {
    var ans = 0
    var l = 0
    // 0 出现的次数
    var zeroTime = 0
    var oneTime = 0
    for (r <- nums.indices) {
      oneTime += nums(r)
      if (0 == nums(r)) {
        zeroTime += 1
      }
      while (zeroTime > 1) {
        if (0 == nums(l)) {
          zeroTime -= 1
        } else {
          oneTime -= 1
        }
        l += 1
      }
      // 怎么更新答案？通过 r-l+1 不正确
      // 通过 1 出现的次数
      ans = Math.max(ans, oneTime)
    }
    // 全是 1
    if (zeroTime == 0) {
      ans -= 1
    } else {
      // 只有一个 0 的情况下
      ans = Math.max(ans, oneTime)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(longestSubarray(Array(1, 1, 0, 1)) == 3)
    println(longestSubarray(Array(0, 1, 1, 1, 0, 1, 1, 0, 1)) == 5)
    println(longestSubarray(Array(1, 1, 1)) == 2)
  }
}
