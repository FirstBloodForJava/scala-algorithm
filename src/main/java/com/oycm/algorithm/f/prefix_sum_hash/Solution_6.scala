package com.oycm.algorithm.f.prefix_sum_hash

object Solution_6 {

  /**
   * 2588. 统计美丽子数组数目 1697
   * https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays/description/
   *
   * 对整数数组进行以下操作：
   *  - 选择两个不同的下标
   *  - 将 nums(i) 和 nums(j) 都减去 2 k 次幂
   * 如果子数组进行上次若干次操作后，子数组全变成 0 ，则程它是一个美丽子数组
   *
   * @param nums nums(i) [0, 100000]
   * @return 求 nums 中 美丽子数组的 个数
   */
  def beautifulSubarrays(nums: Array[Int]): Long = {
    /*
    如果子数组是美丽子数组则子数组所有元素进行 异或运算（^）的结果是 0
    不断对 nums 进行 异或运算 s(l) == s(r) 去掉前缀，[l, r) 就能组成 美丽子数组
    */
    var ans = 0L
    val map = scala.collection.mutable.Map[Int,Int]()
    var sum = 0
    for (num <- nums) {
      map(sum) = map.getOrElse(sum, 0) + 1
      sum = sum ^ num
      ans += map.getOrElse(sum, 0)
    }

    ans
  }

}
