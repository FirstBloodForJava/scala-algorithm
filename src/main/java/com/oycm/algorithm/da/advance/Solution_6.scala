package com.oycm.algorithm.da.advance

object Solution_6 {

  /**
   * 3488. 距离最小相等元素查询 1699
   * https://leetcode.cn/problems/closest-equal-element-queries/
   *
   * 求满足 nums[j] == nums[queries[i]] 的最小距离，不存在返回 -1
   *
   * @param nums    环形 数组
   * @param queries 数组的值是 nums 数组的下标
   * @return
   */
  def solveQueries(nums: Array[Int], queries: Array[Int]): List[Int] = {
    /*
    nums 相同的 值 的下标记录到集合，下标是升序的
    相同数量下标大于 1, 可 二分查找 i 左边第一个数，i 右边第一个数 求距离的最小值

    时间复杂度 O (n log k)
    */
    val n = nums.length
    val ans = Array.fill(queries.length)(-1)
    val map = scala.collection.mutable.Map[Int, scala.collection.mutable.ArrayBuffer[Int]]()
    for (i <- nums.indices) {
      if (!map.contains(nums(i))) {
        map(nums(i)) = scala.collection.mutable.ArrayBuffer[Int]()
      }
      map(nums(i)).addOne(i)
    }

    def lowerBound(sort: scala.collection.mutable.ArrayBuffer[Int], x: Int): Int = {
      var l = -1
      var r = sort.length
      while (l + 1 < r) {
        val mid = l + (r - l) / 2
        if (sort(mid) >= x) {
          r = mid
        } else {
          l = mid
        }
      }
      r
    }

    for (i <- queries.indices) {
      val x = queries(i)
      val sort = map(nums(x))
      if (sort.size > 1) {
        val left = lowerBound(sort, x) - 1
        val right = left + 2
        var min = Int.MaxValue
        // 环形数组 left = -1 left 可以是 sort.length - 1
        // 环形数组 right = sort.length right 可以是 0
        // 不可能同时存在 -1 和 n 这里是不是可以作为优化点？
        if (left >= 0) {
          min = Math.min(x - sort(left), sort(left) + n - x)
        } else {
          min = Math.min(sort.last - x, x + n - sort.last)
        }
        if (right < sort.length) {
          min = Math.min(Math.min(min, sort(right) - x), x + n - sort(right))
        } else {
          min = Math.min(Math.min(min, x - sort(0)), sort(0) + n - x)
        }

        ans(i) = min
      }
    }

    ans.toList
  }

  def main(args: Array[String]): Unit = {
    println(solveQueries(Array(1, 3, 1, 4, 1, 3, 2), Array(0, 3, 5)))
    println(solveQueries(Array(1, 2, 3, 4), Array(0, 1, 2, 3)))
    println(solveQueries(Array(14, 14, 4, 2, 19, 19, 14, 19, 14), Array(2, 4, 8, 6, 3)))
    println(solveQueries(Array(5, 20, 12, 14, 12, 17, 13, 7, 7, 5, 20, 5, 5, 5, 12, 19, 6), Array(3, 14, 2, 0, 8, 12, 16, 6, 7, 11, 4, 10)))
  }

}
