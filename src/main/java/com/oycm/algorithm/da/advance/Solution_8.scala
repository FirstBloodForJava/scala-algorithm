package com.oycm.algorithm.da.advance

object Solution_8 {

  /**
   * 2070. 每一个查询的最大美丽值 1724
   * https://leetcode.cn/problems/most-beautiful-item-for-each-query/description/
   *
   * 求出价格小于等于 queries(i) items 中的最大美丽值
   *
   * @param items   items(i) = [price, beauty] 二维数组
   * @param queries 查询数组
   * @return
   */
  def maximumBeauty(items: Array[Array[Int]], queries: Array[Int]): Array[Int] = {
    /*
    先对 items 按价格升序排序，再从左到右 遍历出每个 items(i)(1) 的最大值（每个价格能获得的最大值）
    找 小于等于 queries(i) 价格最大的美丽值，就是找到 大于等于 queries(i) + 1 的 index - 1
     */
    val n = items.length
    // 排序
    val sorted = items.sortBy(row => row(0))
    var max = 0
    for (elem <- sorted) {
      max = Math.max(elem(1), max);
      elem(1) = max
    }

    def lowerBound(nums: Array[Array[Int]], x: Int): Int = {
      var l = -1
      var r = nums.length
      while (l + 1 < r) {
        val mid = l + (r - l) / 2
        if (nums(mid)(0) >= x) {
          r = mid
        } else {
          l = mid
        }
      }

      r
    }

    for (i <- queries.indices) {
      val x = queries(i)
      var beauty = 0
      if (x < sorted(0)(0)) {
        beauty = 0
      } else if (x >= sorted(n - 1)(0)) {
        beauty = sorted(n - 1)(1)
      } else {
        beauty = sorted(lowerBound(sorted, x + 1) - 1)(1)
      }
      queries(i) = beauty
    }

    queries;
  }

  def main(args: Array[String]): Unit = {
    println(maximumBeauty(Array(Array(1, 2), Array(3, 2), Array(2, 4), Array(5, 6), Array(3, 5)), Array(1, 2, 3, 4, 5, 6)).toSeq)
  }

}
