package com.oycm.algorithm.e.ea.basic

object Solution_15 {

  /**
   * 3371. 识别数组中的最大异常值 1644
   * https://leetcode.cn/problems/identify-the-largest-outlier-in-an-array/description/
   *
   * 恰好 有 n - 2 个元素是 特殊数字
   *  - 一个元素是 所有 特殊元素的 和
   *  - 另一个是异常值
   *
   * @param nums 整数数组, n [3, 100 000], nums(i) [-1000, 1000]
   * @return 求 nums 中最大的异常值
   */
  def getLargestOutlier(nums: Array[Int]): Int = {
    /*
    数组和 = 特殊元素 * 2 + 异常值

    数组和 - nums(i) * 2 的这个值是否在数组中存在，存在则是异常值
    是否需要对结果进行判断？
    如果 exception == sum
    sums = 3 sum 如果这个值存在，则元素中至少有两个相同的值 30 10 235 235
    所以要用 map 判断次数
     */
    var ans = -1000
    var sums = 0
    val map = scala.collection.mutable.Map[Int,Int]()
    for (num <- nums) {
      sums += num
      map(num) = map.getOrElse(num, 0) + 1
    }
    // 枚举 元素和，找异常值
    for (num <- nums) {
      val sum = sums - 2 * num
      if (map.contains(sum) && (sum != num || map(sum) > 1)) {
        ans = Math.max(ans, sums - 2 * num)
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
//    println(getLargestOutlier(Array(-2, -1, -3, -6, 4)))
//    println(getLargestOutlier(Array(1, 1, 1, 1, 1, 5, 5)))
//    println(getLargestOutlier(Array(2, 3, 5, 10)))
    println(getLargestOutlier(Array(6, -31, 50, -35, 41, 37, -42, 13)))
  }

}
