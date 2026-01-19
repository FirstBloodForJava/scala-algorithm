package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_16 {

  /**
   * 624. 数组列表中的最大距离
   * https://leetcode.cn/problems/maximum-distance-in-arrays/
   *
   * 从 两个不同的数组选择两个整数，求他们最大的绝对值
   *
   * @param arrays 长为 m 的数组，每个数组按升序排序
   * @return
   */
  def maxDistance(arrays: List[List[Int]]): Int = {
    /*
    在一堆数组中找到一个数组的最小值，和另外一个数组的最大值
    要避免 自己最大值-自己最小值的情况

    只要当前的最小值 和 历史最大值，最大值和历史最小值 比较差值，并不断更新最大值和最小值
    // 计算绝对值超时
     */
    var ans = 0
    var min = arrays.head.head
    var max = arrays.head.last
    for (i <- 1 until arrays.length) {
      ans = Math.max(ans, Math.max(Math.abs(max - arrays(i).head), Math.abs(arrays(i).last - min)))
      min = Math.min(min, arrays(i).head)
      max = Math.max(max, arrays(i).last)
    }

    ans
  }

  def maxDistance(arrays: Array[Array[Int]]): Int = {
    /*
    在一堆数组中找到一个数组的最小值，和另外一个数组的最大值
    要避免 自己最大值-自己最小值的情况

    只要当前的最小值 和 历史最大值，最大值和历史最小值 比较差值，并不断更新最大值和最小值
    优化：是否能去掉绝对值
    max(|max - head|, |last - min|)
    如果 max - head < 0 ; max < head;
    由于 last >= head, max >= min, 所以 last - min >= 0, last - min >= head - max = |max - head|


    如果 max - head > 0 ; max > head
    由于 max >= min, last >= head, 所以 last - min > 0

     */
    var ans = 0
    var min = arrays.head.head
    var max = arrays.head.last
    for (i <- 1 until arrays.length) {
      ans = Math.max(ans, Math.max(max - arrays(i).head, arrays(i).last - min))
      min = Math.min(min, arrays(i).head)
      max = Math.max(max, arrays(i).last)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxDistance(Array(Array(1, 2, 3), Array(4, 5), Array(1, 2, 3))))
    println(maxDistance(Array(Array(-1, 1), Array(-3, 1, 4), Array(-2, -1, 0, 2))))
  }


}
