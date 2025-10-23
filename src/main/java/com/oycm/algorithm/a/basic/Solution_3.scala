package com.oycm.algorithm.a.basic

object Solution_3 {

  /**
   * 1343.大小为 K 且平均值大于等于阈值的子数组数目
   * https://leetcode.cn/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/description/
   *
   * @param arr       整数数组
   * @param k         长度为 k 的子数组
   * @param threshold 子数组的平均值阈值
   * @return 返回长度为 k 且平均值 大于等于 threshold 的子数组数目
   */
  def numOfSubarrays(arr: Array[Int], k: Int, threshold: Int): Int = {
    leftNumOfSubarrays(arr, k, threshold)
  }

  private def leftNumOfSubarrays(arr: Array[Int], k: Int, threshold: Int): Int = {
    var ans = 0
    var temp = 0
    val flag = threshold * k
    for (i <- arr.indices) {
      // 入
      temp += arr(i)
      if (i - k + 1 >= 0) {
        // 更新
        if (temp >= flag) {
          ans += 1
        }
        // 出
        val left = i - k + 1
        temp -= arr(left)
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(numOfSubarrays(Array(2, 2, 2, 2, 5, 5, 5, 8), 3, 4) == 3)
    println(numOfSubarrays(Array(11, 13, 17, 23, 29, 31, 7, 5, 2, 3), 3, 5) == 6)
  }
}
