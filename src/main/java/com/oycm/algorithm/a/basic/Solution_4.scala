package com.oycm.algorithm.a.basic

object Solution_4 {

  /**
   * 2090.半径为 k 的子数组平均值 1358
   * https://leetcode.cn/problems/k-radius-subarray-averages/
   *
   * 半径为 k 的子数组平均值 是指：
   *   - nums 中一个以下标 i 为 中心 且 半径 为 k 的子数组中所有元素的平均值，即 [i-k,i+k] 范围内所有元素的平均值
   *   - 平均值整数截断
   *   - 下标 i 前或后不足 k 个元素，那么 半径为 k 的子数组平均值 是 -1 。
   *
   * @param nums 整数数组
   * @param k    整数，i 的前后范围 k
   * @return 长度和 nums 一致的数组，下标 i 表示的是 nums 半径为 k 的子数组平均值，
   */
  def getAverages(nums: Array[Int], k: Int): Array[Int] = {
    // 特殊情况处理
    if (k == 0) {
      nums
    }
    val ans = Array.fill(nums.length)(-1)
    // i >= 2k 才会形成半径为 k 的连续子数组
    if (nums.length - 1 < 2 * k) {
      ans
    }
    // 怎么判断子数组的形成，并计算当前子数组的的平均值
    // index >= 2k, 窗口形成，i = index - k，求平均值 temp / 2k+1
    // 要考虑数值溢出问题
    var temp: Long = 0
    for (i <- nums.indices) {
      // 入
      temp += nums(i)
      // 子数组形成
      if (i >= 2 * k) {
        // 更新答案
        ans(i - k) = (temp / (2 * k + 1)).toInt
        // 出
        temp -= nums(i - 2* k)
      }

    }

    ans
  }

  def main(args: Array[String]): Unit = {

    println(getAverages(Array(7, 4, 3, 9, 1, 8, 5, 2, 6), 3).toSeq == Array(-1,-1,-1,5,4,4,-1,-1,-1).toSeq)
    println(getAverages(Array(100000), 0).toSeq == Array(100000).toSeq)
    println(getAverages(Array(1), 100).toSeq == Array(-1).toSeq)

    println(Int.MaxValue)
  }
}
