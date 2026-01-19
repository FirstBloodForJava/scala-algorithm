package com.oycm.algorithm.f.prefix_sum_hash

object Solution_4 {

  /**
   * 974. 和可被 K 整除的子数组 1676
   * https://leetcode.cn/problems/subarray-sums-divisible-by-k/description/
   *
   * @param nums 整数数组
   * @param k    正整数
   * @return 求子数组和能被 k 整除的数目
   */
  def subarraysDivByK(nums: Array[Int], k: Int): Int = {
    /*
    题解思路：子数组 [l, r) 的和 = sums(r) - sums(l)
    题目要求 (sums(r) - sums(l)) % k == 0
    由模的同余，即 sums(r) % k == sums(l) % k
    由于前缀和中有负数的情况，所以需要 mod 后 再 加 mod 再 mod
    */
    var sum = 0
    var ans = 0
    // 用数组做统计
    var cnt = Array.fill(k)(0)
    for (num <- nums) {
      cnt(sum) += 1
      sum = (sum + num % k + k) % k
      ans += cnt(sum)
    }

    ans
  }

}
