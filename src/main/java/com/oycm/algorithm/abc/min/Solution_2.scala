package com.oycm.algorithm.abc.min

object Solution_2 {

  /**
   * 2962.统计最大元素出现至少 K 次的子数组 1701
   * https://leetcode.cn/problems/count-subarrays-where-max-element-appears-at-least-k-times/
   *
   * nums 正整数数组
   * k 正整数 >= 1
   * 求 nums 中最大元素 出现至少 k 次的子数组数
   *
   * @param nums 正整数数组
   * @param k    正整数 >= 1
   * @return
   */
  def countSubarrays(nums: Array[Int], k: Int): Long = {
    /*
      先求出 max(num)
      [l, r1] 中 count(max) >= k 时，则 固定左端点符合要求的子数组数 = n - r
      [l+1, r2] l 向右移动，继续寻找答案，更新
      当 r = n-1 时，还不符合要求，则 l 不需要在继续循环了
     */
    val n: Long = nums.length.toLong
    var max = 0
    for (i <- nums) {
      max = Math.max(i, max)
    }
    var ans = 0L
    var flag = true
    var l = 0
    var r = 0
    // count(1/max)
    val count = Array.fill(2)(0)
    while (l < n - k + 1 && flag) {
      while (r < n && count(1) < k) {
        count(nums(r) / max) += 1
        r += 1
      }
      if (count(1) >= k) {
        ans += n - r + 1
      } else if (r == n) {
        flag = false
      }
      count(nums(l) / max) -= 1
      l += 1
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(countSubarrays(Array(1, 3, 2, 3, 3), 2))
    println(countSubarrays(Array(1, 4, 2, 1), 3) == 0)
  }
}
