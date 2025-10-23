package com.oycm.algorithm.a.basic

object Solution_2 {

  /**
   * 643.子数组最大平均数 I
   * https://leetcode.cn/problems/maximum-average-subarray-i/description/
   * 找出平均数最大且长度为 k 的连续子数组
   *
   * @param nums n 个元素组成的数组
   * @param k    整数 k
   * @return 最大平均数
   */
  def findMaxAverage(nums: Array[Int], k: Int): Double = {
    leftFindMaxAverage(nums, k)
  }

  // 左到右
  private def leftFindMaxAverage(nums: Array[Int], k: Int): Double = {
    // 初始化不能是 0
    var ans = Int.MinValue
    var temp = 0

    for (i <- nums.indices) {
      // 入
      temp += nums(i)
      if (i - k + 1 >= 0) {
        // 更新
        ans = math.max(ans, temp)

        // 出
        val left = i - k + 1;
        temp -= nums(left)
      }
    }
    ans.toDouble / k
  }

  def main(args: Array[String]): Unit = {
    println(findMaxAverage(Array(1, 12, -5, -6, 50, 3), 4) == 12.75)
    println(findMaxAverage(Array(5), 1) == 5.0)
    println(findMaxAverage(Array(-1), 1) == -1.0)
  }
}
