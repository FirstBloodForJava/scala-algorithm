package com.oycm.algorithm.d.binary_search_ans_indirect

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


  def answer_2(points: Array[Array[Int]], s: String): Int = {
    /*
    相同的点，要只能有一个点在正方形中，则只能时该字符的最小点在正方形中
    多个点要在正方形中符合要求，假设有 点 aMin1, aMin2, bMin1, bMin2
    正方形要包含不同的 a 和 b 则必须满足 aMin1 和 aMin2 < min(aMin2, bMin2)
    所以要找出所有字符的最小点，和 次小点，min < min(min2) 的点 是正方形中能包含最多的点

    要在遍历的过程中，维护最小点和 所有字符的次小点

    时间复杂度 O(n)
    空间复杂度 O(26)
     */
    val min = Array.fill(26)(Int.MaxValue)
    var min2 = Int.MaxValue
    for (i <- points.indices) {
      val c = s(i) - 'a'
      val d = Math.max(Math.abs(points(i)(0)), Math.abs(points(i)(1)))
      if (d < min(c)) {
        // 距离小于当前最小值
        min2 = Math.min(min(c), min2)
        min(c) = d
      } else {
        // 更新次小点
        min2 = Math.min(min2, d)
      }
    }
    var ans = 0
    for(i <- points.indices) {
      if (Math.max(Math.abs(points(i)(0)), Math.abs(points(i)(1))) < min2) {
        ans += 1
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxPointsInsideSquare(Array(Array(1, 1), Array(-2, -2), Array(-2, 2)), "abb"))
    println(maxPointsInsideSquare(Array(Array(1, 1), Array(-1, -1), Array(2, -2)), "ccd"))

    println(answer_2(Array(Array(1, 1), Array(-2, -2), Array(-2, 2)), "abb"))
    println(answer_2(Array(Array(1, 1), Array(-1, -1), Array(2, -2)), "ccd"))
  }
}
