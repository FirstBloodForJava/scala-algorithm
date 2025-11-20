package com.oycm.algorithm.e.eb

object Solution_1 {

  /**
   * 2909. 元素和最小的山形三元组 II 1479
   * https://leetcode.cn/problems/minimum-sum-of-mountain-triplets-ii/
   *
   * 山形三元组 要求：
   *  - i < j < k
   *  - nums(j) > nums(i), 且 nums(j) > nums(k)
   *
   * @param nums
   * @return 找出 nums 中 元素和最小 的山形三元组, 没有则返回 -1
   */
  def minimumSum(nums: Array[Int]): Int = {
    /*
    枚举中间
    找出 nums [0, j-1] 的最小值
    找出 nums [j+1, n) 的最小值
    遍历 j 如果, 如果 nums(j) > preMin(j-1) 且 nums(j) > sufMin(j+1) 则 更新答案

    时间复杂度 O(n)
    空间复杂度 O(n)
     */
    var ans = Int.MaxValue
    val n = nums.length
    val sufMin = Array.fill(n + 1)(Int.MaxValue)
    for (i <- n - 1 to 2 by -1) {
      sufMin(i) = Math.min(sufMin(i + 1), nums(i))
    }
    var preMin = nums(0)
    for (j <- 1 to n - 2) {
      if (nums(j) > preMin && nums(j) > sufMin(j + 1)) {
        ans = Math.min(ans, preMin + nums(j) + sufMin(j + 1))
      }
      preMin = Math.min(preMin, nums(j))
    }
    if (ans == Int.MaxValue) {
      ans = -1
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minimumSum(Array(8, 6, 1, 5, 3)))
    println(minimumSum(Array(5, 4, 8, 7, 10, 2)))
    println(minimumSum(Array(6, 5, 4, 3, 4, 5)))
  }

}
