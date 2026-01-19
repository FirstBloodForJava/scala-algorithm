package com.oycm.algorithm.e.enum_mid

object Solution_5 {

  /**
   * 2874. 有序三元组中的最大值 II 1583
   * https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-ii/description/
   *
   * 下标三元组值定义： i < j < k，(nums[i] - nums[j]) * nums[k]
   *
   * @param nums 整数数组
   * @return
   */
  def maximumTripletValue(nums: Array[Int]): Long = {
    /*
    遍历过程中维护 i 前缀最大值和 i+1 之后的最小值，不断和 i+2 更新答案
    */
    var ans = 0L

    var preMax = Int.MinValue
    var preMin = Int.MaxValue

    for (i <- 0 until nums.length - 2) {
      // 问题，前面出现最小值后，没有出现较小值，出现大值后，计算的答案有误
      preMax = Math.max(preMax, nums(i))
      preMin = Math.min(preMin, nums(i + 1))

      ans = Math.max(ans, (preMax - preMin).toLong * nums(i + 2))

    }

    ans
  }

  def answer_1(nums: Array[Int]): Long = {
    /*
    题解思路：枚举中间
    (nums(i) - nums(j)) * nums(k)
    找到 [j+1, n-1] 的最大值，求后缀最大值
    找到 [0, j-1] 的最大值，求前缀最大值

    后缀最大值，从后往前遍历找出, [2, n] 后缀最大值
    sufMax(i) = Math.max(sufMax(i+1), nums(i))

    前缀最大值，从前往后遍历找出，[0,n-2] 前缀最大值
    preMax(i) = Math.max(preMax(i-1), nums(i))
    */
    var ans = 0L
    val n = nums.length
    val sufMax = Array.fill(n + 1)(0)
    for (i <- n - 1 to 2 by -1) {
      sufMax(i) = Math.max(sufMax(i + 1), nums(i))
    }
    var preMax = nums(0)
    for (j <- 1 to n - 2) {
      ans = Math.max(ans, (preMax - nums(j)).toLong * sufMax(j + 1))
      preMax = Math.max(preMax, nums(j))
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumTripletValue(Array(12, 6, 1, 2, 7)))
    println(maximumTripletValue(Array(1, 10, 3, 4, 19)))
    println(maximumTripletValue(Array(1, 2, 3)))
    println(maximumTripletValue(Array(8, 6, 3, 13, 2, 12, 19, 5, 19, 6, 10, 11, 9)))

    println(answer_1(Array(12, 6, 1, 2, 7)) == 77)
    println(answer_1(Array(1, 10, 3, 4, 19)) == 133)
    println(answer_1(Array(1, 2, 3)) == 0)
    println(answer_1(Array(8, 6, 3, 13, 2, 12, 19, 5, 19, 6, 10, 11, 9)) == 266)
  }

}
