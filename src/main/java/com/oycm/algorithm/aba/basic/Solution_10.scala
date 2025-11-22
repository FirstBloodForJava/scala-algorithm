package com.oycm.algorithm.aba.basic

object Solution_10 {

  /**
   * 1004. 最大连续1的个数 III 1656
   * https://leetcode.cn/problems/max-consecutive-ones-iii/description/
   *
   * @param nums 二进制数组
   * @param k    翻转 k 个 0
   * @return 执行翻转后连续 1 的最大个数
   */
  def longestOnes(nums: Array[Int], k: Int): Int = {
    /*
    问题就是求 0 不超过 k 个的最长子数组
    */
    var ans = 0
    var l = 0
    var cnt = 0
    for (r <- nums.indices) {
      if (nums(r) == 0) {
        cnt += 1
      }
      while (cnt > k) {
        if (nums(l) == 0) {
          cnt -= 1
        }
        l += 1
      }
      ans = Math.max(ans, r - l + 1)
    }

    ans
  }
}
