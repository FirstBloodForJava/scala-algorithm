package com.oycm.algorithm.e.ea.basic

object Solution_2 {

  /**
   * 2441. 与对应负数同时存在的最大正整数 1168
   * https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative/description/
   *
   * @param nums 不包含 任何零的整数数组
   * @return 自身与对应的负数都在数组中存在的最大正整数
   */
  def findMaxK(nums: Array[Int]): Int = {
    /*
    数组中 两数之和为 0 的最大正整数

    时间复杂度 O(n)
    空间复杂度 O(m) 负数的个数
     */
    var ans = -1
    val set = scala.collection.mutable.Set[Int]()
    // map 维护负数
    for (num <- nums) {
      if (num < 0) {
        set.add(num)
      }
    }
    for (num <- nums) {
      if (num > 0 && set.contains(-num)) {
        ans = Math.max(ans, num)
      }
    }

    ans
  }

  def optimize(nums: Array[Int]): Int = {
    /*
    数组中 两数之和为 0 的最大正整数

    时间复杂度 O(n)
    空间复杂度 O(n) 负数的个数
     */
    var ans = -1
    val set = scala.collection.mutable.Set[Int]()
    for (num <- nums) {
      if (set.contains((-num))) {
        ans = Math.max(ans, Math.abs(num))
      }
      set.add(num)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(findMaxK(Array(-1, 2, -3, 3)))
    println(findMaxK(Array(-1, 10, 6, 7, -7, 1)))
    println(findMaxK(Array(-10, 8, 6, 7, -2, -3)))

    println(optimize(Array(-1, 2, -3, 3)))
    println(optimize(Array(-1, 10, 6, 7, -7, 1)))
    println(optimize(Array(-10, 8, 6, 7, -2, -3)))

  }
}
