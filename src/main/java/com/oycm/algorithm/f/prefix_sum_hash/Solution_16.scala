package com.oycm.algorithm.f.prefix_sum_hash

object Solution_16 {

  /**
   * 1590. 使数组和能被 P 整除 2039
   * https://leetcode.cn/problems/make-sum-divisible-by-p/description/
   *
   * 移除最短子数组，使得 剩余元素和能被 p 整除，不允许将整个子数组移除。如没有则 返回 -1
   *
   * @param nums nums(i) [1, 10 pow 9], n in [1, 10 pow 5]
   * @param p    [1, 10 pow 9]
   * @return
   */
  def minSubarray(nums: Array[Int], p: Int): Int = {
    /*
    nums 数组和 记为 sums(n), 需要移除子数组记为 [l, r), 其和可记为 sums(r) - sums(l)
    (sums(n) - (sums(r) - sums(l))) % p == 0
    sums(n) % p - sums(r) % p + sums(l) % p == 0

    sums(r) % p = sums(n) % p + sums(l) % p

    (sums(r) % p - sums(n) % p + p) % p = sums(l) % p

    先计算整个 sums % p, 遍历 sums(r) 的 过程中，查找 是否存在 sums(r) % p - sums(n) % p 的 sums(l)
    这里有减法，取模需要注意负数的情况
    */
    val n = nums.length
    var ans = n
    var sum = 0
    for (num <- nums) {
      sum = (sum + num % p) % p
    }
    // 整个数组已经符合条件
    if (sum == 0) {
      return 0
    }
    val map = scala.collection.mutable.Map[Int, Int]()
    var s = 0
    for (i <- nums.indices) {
      // 求最短，要不算 更新 相同的 sum index
      map(s) = i
      // 计算 sums(r)
      s = (s + nums(i) % p) % p
      // 更新答案 r 未 + 1
      ans = Math.min(ans, i - map.getOrElse((s - sum + p) % p, i - n) + 1)
    }
    if (ans == n) ans = -1
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minSubarray(Array(3, 1, 4, 2), 6))
    println(minSubarray(Array(3, 1, 4, 2), 1))
  }
}
