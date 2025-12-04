package com.oycm.algorithm.db.min

class Solution_4 {

  /**
   * 875. 爱吃香蕉的珂珂 1766
   * https://leetcode.cn/problems/koko-eating-bananas/description/
   *
   * 珂珂可以决定吃香蕉的速度 k(根/小时), 每个小时选择一堆香蕉从中吃掉 k 根，
   * 如果这堆香蕉小于 k 根，这一个小时内不会再吃香蕉
   *
   * @param piles 表示每堆 i 香蕉的数量
   * @param h     警卫离开的小时
   * @return 求在 h 小时内吃完所有香蕉的最小速度
   */
  def minEatingSpeed(piles: Array[Int], h: Int): Int = {
    /*
    间接二分答案，piles 的 k 和 h 成 反比，k 越大，h 越小
    找到第一个大于等于 h 的 k，找到最后一个大于 h 的 k，加 1 就是答案
    本质是倒序数组 查找 第一个大于等于 h 的 index，转换成 最后一个大于 h 的 index + 1
     */
    var l = 0;
    var r = 0;
    for (elem <- piles) {
      r = Math.max(r, elem)
    }
    r += 1

    def getH(nums: Array[Int], k: Int): Long = {
      // 注意计算小时溢出
      var h = 0L
      for (elem <- nums) {
        // (a + b - 1) / b 下取整
        h += (elem + k - 1) / k
      }
      h
    }
    // 开区间 二分查找
    while (l + 1 < r) {
      val mid = l + (r - l) / 2
      if (getH(piles, mid) > h) {
        l = mid
      } else {
        r = mid
      }
    }

    l + 1
  }

  def main(args: Array[String]): Unit = {
    println(minEatingSpeed(Array(3, 6, 7, 11), 8))
    println(minEatingSpeed(Array(30, 11, 23, 4, 20), 5))
    println(minEatingSpeed(Array(30, 11, 23, 4, 20), 6))
    println(minEatingSpeed(Array(805306368, 805306368, 805306368), 1000000000))
  }

}
