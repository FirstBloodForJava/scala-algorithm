package com.oycm.algorithm.a

object Solution_2 {

  /**
   * 找出平均数最大且长度为 k 的连续子数组
   * @param nums n 个元素组成的数组
   * @param k 整数 k
   * @return 最大平均数
   */
  def findMaxAverage(nums: Array[Int], k: Int): Double = {

    5.0
  }

  def main(args: Array[String]): Unit = {
    println(findMaxAverage(Array(1, 12, -5, -6, 50, 3), 4) == 12.75)
    println(findMaxAverage(Array(5), 4) == 5.0)
  }
}
