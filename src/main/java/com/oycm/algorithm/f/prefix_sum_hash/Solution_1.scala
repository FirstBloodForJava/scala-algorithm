package com.oycm.algorithm.f.prefix_sum_hash

object Solution_1 {

  /**
   * 560. 和为 K 的子数组
   *
   * @param nums 整数数组
   * @param k    整数
   * @return 统计 连续子数组和为 k 的个数
   */
  def subarraySum(nums: Array[Int], k: Int): Int = {
    /*
    前缀和 + 枚举
    遍历数组的前缀和 sums(j) = sums(i) + k 则[i, j) 数组符合要求
    一次即可，计算
     */
    var sum = 0
    val map = scala.collection.mutable.Map[Int, Int]()
    map(0) = 1
    var ans = 0
    for (num <- nums) {
      sum += num
      if (map.getOrElse(sum - k, 0) > 0) {
        ans += map(sum - k)
      }
      map(sum) = map.getOrElse(sum, 0) + 1
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(subarraySum(Array(1, 1, 1), 2))
    println(subarraySum(Array(1, 2, 3), 3))
    println(subarraySum(Array(0, 0, 0), 0))
  }

}
