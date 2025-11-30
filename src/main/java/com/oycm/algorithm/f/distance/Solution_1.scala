package com.oycm.algorithm.f.distance

object Solution_1 {

  /**
   * 1685. 有序数组中差绝对值之和 1496
   * https://leetcode.cn/problems/sum-of-absolute-differences-in-a-sorted-array/description/
   *
   * 返回一个和 nums 一样长的新数组 result, result(i) 表示 nums(i) 和 所有其它元素差的绝对值之和
   *
   * @param nums 非递减 有序整数数组, nums(i) [1, 10000]
   * @return
   */
  def getSumAbsoluteDifferences(nums: Array[Int]): Array[Int] = {
    /*
    nums(i) 左边的元素差绝对值之和 = i * nums(i) - sums(i)
    nums(i) 左边的元素差绝对值之和 = sums(n) - sums(i) - (n - i) * nums(i), 把 nums(i) 自己减自己算上
    */
    val n = nums.length
    val sums = Array.fill(n + 1)(0)
    val ans = Array.fill(nums.length)(0)
    for (i <- nums.indices) {
      sums(i + 1) = sums(i) + nums(i)
    }

    for (i <- nums.indices) {
      ans(i) = i * nums(i) - sums(i) + sums(n) - sums(i) - (n - i) * nums(i)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(getSumAbsoluteDifferences(Array(2, 3, 5)).toSeq)
    println(getSumAbsoluteDifferences(Array(1, 4, 6, 8, 10)).toSeq)
  }
}
