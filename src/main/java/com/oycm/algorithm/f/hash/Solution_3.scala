package com.oycm.algorithm.f.hash

object Solution_3 {

  /**
   * 1524. 和为奇数的子数组数目 1611
   * https://leetcode.cn/problems/number-of-sub-arrays-with-odd-sum/description/
   *
   * @param arr arr(i) [1, 100], n [1, 100000]
   * @return 和为 奇数 的子数组数目
   */
  def numOfSubarrays(arr: Array[Int]): Int = {
    /*
    [l, r) 前缀和 (s(r) - s(l)) % 2 == 1
    s(r) % 2 - s(l) % 2 == 1
    s(r) % 2 - 1 == s(l) % 2
    由于 s(r) % 2 可能等于 0 所以要对 取模进行其它操作
    (s(r) % 2 + 1) % 2 == s(l) % 2

    ans 是否需要用 Long 类型？ 可能会超
    7 个 1的结果时16
    */
    val mod = 1000000007L
    var ans = 0L
    // 用数组记录 s % 2 的数量情况
    var sum = 0
    val cnt = Array.fill(2)(0)
    for (num <- arr) {
      cnt(sum) += 1
      // 优化 位运算替换取模
      sum = (sum + num) & 1
      ans += cnt((sum % 2 + 1) & 1)
    }
    (ans % mod).toInt
  }

  def main(args: Array[String]): Unit = {
    println(numOfSubarrays(Array(1, 3, 5)))
    println(numOfSubarrays(Array(2, 4, 6)))
    println(numOfSubarrays(Array(1, 2, 3, 4, 5, 6, 7)))
    println(numOfSubarrays(Array(1, 1, 1, 1, 1, 1, 1)))
    println(numOfSubarrays(Array(100, 100, 99, 99)))
    println(numOfSubarrays(Array(1)))
  }
}
