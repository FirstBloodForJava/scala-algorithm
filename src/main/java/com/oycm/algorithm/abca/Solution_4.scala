package com.oycm.algorithm.abca

import scala.collection.mutable

object Solution_4 {

  /**
   * 2762.不间断子数组 1940
   * https://leetcode.cn/problems/continuous-subarrays/
   * nums 正整数数组
   * 不间断数组：[l, r] 子数组下标区间，对于任意满足该条件的 l <= i,j <= r 的下标对 0 <= |nums(i) - nums(j)| <= 2
   * 求 nums 不间断子数组的总数目
   *
   * @param nums 正整数数组
   * @return
   */
  def continuousSubarrays(nums: Array[Int]): Long = {

    /**
     * 对于子数组 [l, r] 如果里面的 |max - min| <= 2 则 子数组中的任一子数组都符合要求
     * [l, r] 维护一个窗口，如果 |max - min| <= 2 记录答案(固定右端点 r)：r - l + 1
     * 怎么在移动右窗口过程中，维护子数组的 max 和 min ？
     * 使用 有序哈希表 记录 nums(i) 及出现的次数，key 的次数不超过 3
     */
    var ans = 0L
    val sort = mutable.TreeMap[Int, Int]()
    var l = 0
    for (r <- nums.indices) {
      sort(nums(r)) = sort.getOrElse(nums(r), 0) + 1
      //
      while (sort.lastKey - sort.firstKey > 2) {
        sort(nums(l)) = sort(nums(l)) - 1
        if (sort(nums(l)) == 0) {
          sort.remove(nums(l))
        }
        l += 1
      }
      ans += r - l + 1

    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(continuousSubarrays(Array(5, 4, 2, 4)) == 8)
    println(continuousSubarrays(Array(1,2,3)) == 6)
  }
}
