package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_4 {

  /**
   * 2001. 可互换矩形的组数
   * https://leetcode.cn/problems/number-of-pairs-of-interchangeable-rectangles/description/
   *
   * rectangles[i] = [width, height] 表示矩形的宽度和高度
   *
   * 如果两个矩形 i 和 j（i < j）的宽高比相同，则认为这两个矩形 可互换 。
   *
   * @param rectangles 二维整数数组
   * @return
   */
  def interchangeableRectangles(rectangles: Array[Array[Int]]): Long = {
    /*
    hash 表记录 相同比值的次数，第一次进入默认是 0，当大于 0 时，就是每次相同比值矩形的答案

    时间复杂度 O(n)
    空间复杂度 O(n)
     */
    val map = scala.collection.mutable.Map[Double, Int]()
    var ans = 0L

    for (num <- rectangles) {
      val ratio = num(0).toDouble / num(1).toDouble
      map(ratio) = map.getOrElse(ratio, -1) + 1
      ans += map(ratio)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(interchangeableRectangles(Array(Array(4, 8), Array(3, 6), Array(10, 20), Array(15, 30))))
    println(interchangeableRectangles(Array(Array(4, 5), Array(7, 8))))
  }

}
