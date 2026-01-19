package com.oycm.algorithm.f.prefix_sum_hash

object Solution_7 {

  /**
   * 525. 连续数组
   * https://leetcode.cn/problems/contiguous-array/description/
   *
   * @param nums 只有 0 或 1 的数组
   * @return 相同数量的 0 和 1 的最长连续子数组
   */
  def findMaxLength(nums: Array[Int]): Int = {
    /*
    0 和 1 个数相同的最长子数组
    [l, r) 的前缀和表示 1 的个数, r - l 表示 0 和 1 的个数
    (r-l) / (s(r) - s(l))  = 2
    r - l = 2 (s(r) - s(l))

    r - 2s(r) = l - 2s(l)

    计算前缀和的过程中不断记录 l -2s(l) 的下标，如果存在相同的 key 则不更新 value
    要想更长 相同的 key value 要更小
    */
    var ans = 0
    val map = scala.collection.mutable.Map[Int, Int]()
    var sum = 0
    for (i <- nums.indices) {
      map(i - 2 * sum) = map.getOrElse(i - 2 * sum, i)
      sum += nums(i)
      if (map.getOrElse(i + 1 - 2 * sum, -1) >= 0) {
        ans = Math.max(ans, i + 1 - map(i + 1 - 2 * sum))
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(findMaxLength(Array(0, 1)))
  }

}
