package com.oycm.algorithm.e.eb

object Solution_2 {

  /**
   * 3583. 统计特殊三元组 1510
   * https://leetcode.cn/problems/count-special-triplets/
   *
   * 特殊三元组 定义：
   *  - 0 <= i < j < k < n
   *  - nums[i] == nums[j] * 2
   *  - nums[k] == nums[j] * 2
   *
   * @param nums 整数数组
   * @return 求数组 特殊三元组 的总数，结果对 10 pow 9 + 7 取模
   */
  def specialTriplets(nums: Array[Int]): Int = {
    /*
    找到 [0, j-1] nums[j] * 2 的次数
    找到 [j+1, n) nums[j] * 2 的次数

    使用 hash 表记录 数字出现的次数

    时间复杂度 O(n)
    空间复杂度 O(n)
     */
    val n = nums.length
    var ans = 0L
    val sufFreq = scala.collection.mutable.Map[Int, Int]()
    for (i <- n - 1 to 2 by -1) {
      sufFreq(nums(i)) = sufFreq.getOrElse(nums(i), 0) + 1
    }
    val preFreq = scala.collection.mutable.Map[Int, Int]()
    preFreq(nums(0)) = 1

    for (j <- 1 to n - 2) {
      val mid = nums(j)
      val double = mid * 2
      // 100000 个 0 中间的数相乘超过 Int.MaxVal
      ans += preFreq.getOrElse(double, 0).toLong * sufFreq.getOrElse(double, 0)
      preFreq(mid) = preFreq.getOrElse(mid, 0) + 1
      // 移除元素
      sufFreq(nums(j + 1)) -= 1
    }

    (ans % 1000000007).toInt
  }

  def main(args: Array[String]): Unit = {
    println(specialTriplets(Array(6, 3, 6)))
    println(specialTriplets(Array(0, 1, 0, 0)))
    println(specialTriplets(Array(8, 4, 2, 8, 4)))
  }

}
