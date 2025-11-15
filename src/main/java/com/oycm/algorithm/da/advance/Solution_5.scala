package com.oycm.algorithm.da.advance

object Solution_5 {

  /**
   * 2080. 区间内查询数字的频率
   * https://leetcode.cn/problems/range-frequency-queries/description/
   *
   * 设计一个数据结构，它能求出给定 子数组 内一个给 定值的 频率
   *
   * @param _arr 数组元素
   */
  class RangeFreqQuery(_arr: Array[Int]) {
    // 记录 相同所以的下标
    private val map = scala.collection.mutable.Map[Int, scala.collection.mutable.ArrayBuffer[Int]]()
    for (i <- _arr.indices) {
      if (!map.contains(_arr(i))) {
        map(_arr(i)) = scala.collection.mutable.ArrayBuffer[Int]()
      }
      map(_arr(i)) += i
    }

    def query(left: Int, right: Int, value: Int): Int = {
      /*
      相同元素的下标，是一个升序数组
      */
      if (!map.contains(value)) {
        return 0
      }
      val sort = map(value)

      def lowerBound(nums: scala.collection.mutable.ArrayBuffer[Int], x: Int): Int = {
        var l = 0
        var r = nums.length - 1
        while (l <= r) {
          val mid = l + (r - l) / 2
          if (nums(mid) >= x) {
            r = mid - 1
          } else {
            l = mid + 1
          }
        }
        l
      }

      lowerBound(sort, right + 1) - lowerBound(sort, left)
    }

  }

  def main(args: Array[String]): Unit = {
    val range = RangeFreqQuery(Array(12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56))
    println(range.query(1, 2, 4))
    println(range.query(0, 11, 33))
  }
}
