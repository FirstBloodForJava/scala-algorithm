package com.oycm.algorithm.abca

object Solution_3 {

  /**
   * 2302. 统计得分小于 K 的子数组数目
   * https://leetcode.cn/problems/count-subarrays-with-score-less-than-k/description/
   * 数组得分：数组之和 * 数组长度
   * 求 nums 连续子数组得分 小于 k 的个数，不包含非空子数组
   *
   * @param nums 正整数数组
   * @param k    正整数
   * @return 连续子数组得分 小于 k 的个数
   */
  def countSubarrays(nums: Array[Int], k: Long): Long = {
    /**
     * [l(min), r] 得分 小于 k，则 [l+1, r] [l+2, r] ... [r,r] 子数组得分都小于 k
     * 依次固定 r 求 l(min) 的最小值，随着 r 不断变大，对应的 l 也要不断变大
     */
    // 前缀和
    val sums = Array.fill(nums.length + 1)(0L)
    for (i <- nums.indices) {
      sums(i + 1) = sums(i) + nums(i)
    }
    var ans = 0L
    var l = 0
    for (r <- nums.indices) {
      var temp = (sums(r + 1) - sums(l)) * (r - l + 1)
      // 是否需要判断 l > r 的情况？
      while (temp >= k && l <= r) {
        l += 1
        temp = (sums(r + 1) - sums(l)) * (r - l + 1)
      }
      ans += r - l + 1
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(countSubarrays(Array(2, 1, 4, 3, 5), 10) == 6)
    println(countSubarrays(Array(1, 1, 1), 5) == 5)
    println(countSubarrays(Array(1, 1, 1), 10) == 6)
    println(countSubarrays(Array(9, 5, 3, 8, 4, 7, 2, 7, 4, 5, 4, 9, 1, 4, 8, 10, 8, 10, 4, 7), 4))
  }

}
