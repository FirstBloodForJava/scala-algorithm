package com.oycm.algorithm.ba.opposite

object Solution_7 {

  /**
   * 977. 有序数组的平方 做到 O(n)
   * https://leetcode.cn/problems/squares-of-a-sorted-array/
   *
   * @param nums 非递减顺序 排序的整数数组
   * @return 求 每个数字的平方 组成的新数组，要求按 非递减顺序 排序
   */
  def sortedSquares(nums: Array[Int]): Array[Int] = {
    /*
    nums(i) 离 nums(?) == 0 的 index 越远则平方越大
    l = 0, r = n - 1, l^2 > r^2,则 l++ 否则 r--
    */
    val n = nums.length
    val ans = Array.fill(n)(0)
    var l = 0
    var r = n - 1
    var i = n - 1
    while (i >= 0) {
      val lSquare = nums(l) * nums(l)
      val rSquare = nums(r) * nums(r)
      if (lSquare > rSquare) {
        l += 1
        ans(i) = lSquare
      } else {
        r -= 1
        ans(i) = rSquare
      }
      i -= 1
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(sortedSquares(Array(-4, -1, 0, 3, 10)).toSeq)
    println(sortedSquares(Array(-7, -3, 2, 3, 11)).toSeq)
  }
}
