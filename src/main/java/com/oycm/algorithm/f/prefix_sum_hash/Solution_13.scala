package com.oycm.algorithm.f.prefix_sum_hash

object Solution_13 {

  /**
   * 3728. 边界与内部和相等的稳定子数组 1909
   * https://leetcode.cn/problems/stable-subarrays-with-equal-boundary-and-interior-sum/description/
   *
   * 稳定子数组条件：
   *  - 其长度 至少 为 3
   *  - capacity(l) == capacity(r) == capacity(l+1) + ... + capacity(r-1)
   *
   * @param capacity
   * @return 求稳定子数组数量
   */
  def countStableSubarrays(capacity: Array[Int]): Long = {
    /*
    记 sums 为 capacity 数组的前缀和数组
    a(l+1) + ... + a(r-1) = sums(r) - sums(l+1)
    题解思路：
    枚举 r，需要找到
    a(l) = a(r), 且 sums(l+1) + a(l) = sums(r)
    a(r), sums(r) 去查找 左边的二元组
    需要记录 (a(l), a(l) + sums(l+1)) 二元组的次数，同时 r - l + 1 >= 3
    为了避免判断 r - l + 1 是否大于 3，可以先查找 二元组是否存在，再把 a(l-1) + sums(l) 加入 hash 表
    r+1 - (r-1) + 1 == 3

     */
    val n = capacity.length
    // 只用到 n-1 个前缀
    var ans = 0L
    var sum = capacity(0) + 0L
    val map = scala.collection.mutable.Map[(Int, Long),Int]()

    for (i <- 1 until n) {
      ans += map.getOrElse((capacity(i), sum), 0)
      // 记录 r-1 的 a(r-1), sums(r)
      val key = (capacity(i - 1), capacity(i -1) + sum)
      map(key) = map.getOrElse(key, 0) + 1
      sum += capacity(i)
    }

    ans
  }

  def main(args: Array[String]): Unit = {

  }

}
