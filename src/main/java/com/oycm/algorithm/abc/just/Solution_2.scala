package com.oycm.algorithm.abc.just

object Solution_2 {

  /**
   * 1248.统计「优美子数组」 1624
   * https://leetcode.cn/problems/count-number-of-nice-subarrays/
   * 优美子数组：一个正整数数组 nums 和一个整数 k。某个连续子数组中恰好有 k 个奇数数字。
   *
   * 思路转换 将数组元素都对 2 取模，问题变成只有 0 和 1 的数组求连续子数组和为 k 和 题目 930 相同
   *
   * @param nums 正整数数组
   * @param k    正整数
   * @return
   */
  def numberOfSubarrays(nums: Array[Int], k: Int): Int = {
    /*
    前缀和解决：sum(r) - sum(l) == k => sum(l) = sum(r) - k 答案就是 sum(l) 的数量
    */
    var ans = 0
    var sum = 0
    val sumMap = scala.collection.mutable.Map[Int, Int]()
    for (num <- nums) {
      sumMap(sum) = sumMap.getOrElse(sum, 0) + 1
      // 取模 后 求和
      sum += num % 2
      ans += sumMap.getOrElse(sum - k, 0)
    }
    ans
  }

  def transform(nums: Array[Int], k: Int): Int = {
    /*
    和小于等于 k 子数组数 - 和小于等于 k-1 的子数组数
    */
    def count(nums: Array[Int], k: Int): Int = {
      var ans = 0
      // 固定 r, [l, r] 小于等于 k 时，则向右移动 r，随着 r 不断右移，l 也会不断右移，答案就是 r - l + 1
      var l = 0
      var temp = 0
      for (r <- nums.indices) {
        temp += nums(r) % 2
        while (l <= r && temp > k) {
          // l 右移
          temp -= nums(l) % 2
          l += 1
        }
        // r - l + 1 等于0 或者就是 [l, r] 就是答案
        ans += r - l + 1
      }
      ans
    }

    count(nums, k) - count(nums, k - 1)
  }

  def main(args: Array[String]): Unit = {
    println(numberOfSubarrays(Array(1, 1, 2, 1, 1), 3) == 2)
    println(numberOfSubarrays(Array(2, 4, 6), 1) == 0)
    println(numberOfSubarrays(Array(2, 2, 2, 1, 2, 2, 1, 2, 2, 2), 2) == 16)

    println(transform(Array(1, 1, 2, 1, 1), 3) == 2)
    println(transform(Array(2, 4, 6), 1) == 0)
    println(transform(Array(2, 2, 2, 1, 2, 2, 1, 2, 2, 2), 2) == 16)
  }
}
