package com.oycm.algorithm.db.indirect

object Solution_1 {

  /**
   * 3143. 正方形中的最多点数 1697
   * https://leetcode.cn/problems/maximum-points-inside-the-square/
   *
   * s.length == points.length
   * points[i].length == 2
   *
   * points[i] 表示第 i 个点的坐标，s[i] 表示第 i 个点的 标签。
   *
   * 正方形的中心在 (0, 0)，所有边都平行于坐标轴，且正方形内 不 存在标签相同的两个点，则称这个正方形是 合法 的。
   *
   * 返回 合法 正方形中可以包含的 最多 点数。
   *
   * @param points 二维数组
   * @param s      字符串
   * @return
   */
  def maxPointsInsideSquare(points: Array[Array[Int]], s: String): Int = {
    /*
    题解思路：正方形边长越长，越不符合要求，可以对 边长 进行二分查找
    */
    var ans = 0
    var l = -1
    var r = 1000000001

    def check(s: String, points: Array[Array[Int]], x: Int): Boolean = {
      var flag = true
      // 位运算记录 字符次数
      var cnt = 0
      for (i <- s.indices if flag) {
        // 点 在正方形中
        if (Math.abs(points(i)(0)) <= x && Math.abs(points(i)(1)) <= x) {
          val c = s(i) - 'a'
          if ((cnt >> c & 1) > 0) {
            flag = false
          }
          // 这里是用 1 左移
          cnt |= 1 << c
        }
      }
      if (flag) {
        ans = Integer.bitCount(cnt)
      }
      flag
    }
    // 闭区间 查找
    while (l + 1 < r) {
      val mid = l + (r - l) / 2
      if (check(s, points, mid)) {
        l = mid
      } else {
        r = mid
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxPointsInsideSquare(Array(Array(1, 1), Array(-2, -2), Array(-2, 2)), "abb"))
    println(maxPointsInsideSquare(Array(Array(1, 1), Array(-1, -1), Array(2, -2)), "ccd"))
  }
}
