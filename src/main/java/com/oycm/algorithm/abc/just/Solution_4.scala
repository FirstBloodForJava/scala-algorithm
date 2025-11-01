package com.oycm.algorithm.abc.just

object Solution_4 {

  /**
   * 992.K 个不同整数的子数组 2210
   * https://leetcode.cn/problems/subarrays-with-k-different-integers/
   * nums 正整数数组
   * k 整数
   * 好子数组：子数组中不同整数的个数恰好为 k。
   *  - [1,2,1,2,3] k = 2，[1,2,1]
   *
   * @param nums
   * @param k 整数
   * @return
   */
  def subarraysWithKDistinct(nums: Array[Int], k: Int): Int = {

    /*
    转换成 至少 k - 至少 k+1 问题
    求 子数组至少 k 个元素的子数组个数
    */

    def countLeast(nums: Array[Int], k: Int): Int = {
      var ans = 0
      val cntMap = scala.collection.mutable.Map[Int, Int]()
      var l = 0
      // 固定 r 找 l min
      for (r <- nums.indices) {
        cntMap(nums(r)) = cntMap.getOrElse(nums(r), 0) + 1
        while (cntMap.size == k) {
          if (cntMap(nums(l)) == 1) {
            cntMap.remove(nums(l))
          } else {
            cntMap(nums(l)) = cntMap.getOrElse(nums(l), 0) - 1
          }
          l += 1
        }
        ans += l
      }

      ans
    }

    countLeast(nums, k) - countLeast(nums, k + 1)
  }

  def main(args: Array[String]): Unit = {

    println(subarraysWithKDistinct(Array(1, 2, 1, 2, 3), 2))
    println(subarraysWithKDistinct(Array(1, 2, 1, 3, 4), 3))
  }
}
