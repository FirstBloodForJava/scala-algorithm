package com.oycm.algorithm.aba.basic

object Solution_4 {

  /**
   * 3634.使数组平衡的最少移除数目
   * https://leetcode.cn/problems/minimum-removals-to-balance-array/
   * 数组平衡：一个数组的 最大元素的值 至多 是其 最小元素的 k 倍
   * 注意：长度为 1 的数组被认为是平衡的。最大值和最小值相对
   *
   * @param nums 整数数组
   * @param k    整数
   * @return
   */
  def minRemoval(nums: Array[Int], k: Int): Int = {
    val n = nums.length
    if (n == 1) {
      return 0
    }
    // 求最小，取最大
    var ans = Int.MaxValue
    // 任意位置都可以，先对数组排序，升序数组
    // [l,r] 如果满足 nums(l) * k <= nums(r) 则删除的元素数量 l + n-1-r
    val sorted = nums.sorted
    var l = 0
    for (r <- 1 until n) {
      // sorted(l) * k 越界问题
      while (sorted(r) > sorted(l) * k.toLong) {
        l += 1
      }
      ans = Math.min(ans, l + n - 1 - r)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minRemoval(Array(2, 1, 5), 2) == 1)
    println(minRemoval(Array(1, 6, 2, 9), 3) == 2)
    println(minRemoval(Array(4, 6), 2) == 0)
  }
}
