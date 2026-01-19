package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_14 {

  /**
   * 3623. 统计梯形的数目 I 1580
   * https://leetcode.cn/problems/count-number-of-trapezoids-i/description/
   *
   * 水平梯形: 至少一对 水平边（平行于 x 轴）
   *
   * @param points 二维整数数组, n = 100000, point(i) = (x, y) 表示第 i 点在 笛卡尔平面上的坐标
   * @return
   */
  def countTrapezoids(points: Array[Array[Int]]): Int = {
    /*
    平行 x 轴，则四点中 y 两两相同，但是又 两两不同
    可以统计相同 y 的点数量 c, 能组成的线段数量 c * (c - 1) / 2
    假设有 3 种 y，记为 y1, y2, y3
    y2 计算 y1 * y2
    y3 = (y1 + y2) * y3

    最多的情况是：两条边 c = n/2，线段数：n ^ 2/8, 答案是 n ^ 4 /64 = 1.5625 * 10 ^ 18 < Long.MaxValue
    */
    val mod = 1000000007
    var ans = 0L
    val map = scala.collection.mutable.Map[Int, Long]()
    for (point <- points) {
      map(point(1)) = map.getOrElse(point(1), 0L) + 1
    }
    var s = 0L
    for ((k, v) <- map) {
      val c = v.toLong * (v - 1) / 2
      ans += c * s
      s += c
    }

    (ans % mod).toInt
  }

  def main(args: Array[String]): Unit = {

  }
}
