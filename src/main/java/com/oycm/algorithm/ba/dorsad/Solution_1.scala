package com.oycm.algorithm.ba.dorsad

object Solution_1 {

  /**
   * 1793. 好子数组的最大分数
   * https://leetcode.cn/problems/maximum-score-of-a-good-subarray/description/
   *
   * 子数组 (i, j) 的 分数 定义：min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1)
   *
   * 好子数组 的两个端点下标需要满足 i <= k <= j
   *
   * @param nums 正整数 1 <= nums.length <= 10^5, 1 <= nums[i] <= 2 * 10^4 计算不会超 Int 类型
   * @param k    正整数 0 <= k < nums.length
   * @return
   */
  def maximumScore(nums: Array[Int], k: Int): Int = {
    /*
    单调栈
    初始 i = j = k
    题解：比较 nums(i-1), nums(j+1) 的大小，谁大就移动谁，一样大移动谁都可以，让最小值移动的慢点
    定理：按照这种移动方式，一定会在某个时刻恰好满足 i=L 且 j=R，值最大
    */
    var ans = nums(k)
    var i = k
    var j = k
    var min = ans
    val n = nums.length

    for (t <- 0 until n - 1) {
      if (j == n - 1 || i > 0 && nums(i - 1) > nums(j + 1)) {
        i -= 1
        min = Math.min(min, nums(i))
      } else {
        j += 1
        min = Math.min(min, nums(j))
      }
      ans = Math.max(ans, min * (j - i + 1))
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumScore(Array(1, 4, 3, 7, 4, 5), 3))
    println(maximumScore(Array(5, 5, 4, 5, 4, 1, 1, 1), 0))
    println(maximumScore(Array(6569, 9667, 3148, 7698, 1622, 2194, 793, 9041, 1670, 1872), 5))
  }

}
