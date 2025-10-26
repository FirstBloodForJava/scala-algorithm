package com.oycm.algorithm.a.advance

import scala.collection.mutable

object Solution_4 {

  /**
   * 3694.删除子字符串后不同的终点 1739
   * https://leetcode.cn/problems/distinct-points-reachable-after-substring-removal/description/
   * U D L R 表示在二维笛卡尔网格 上、下、左、右 各移动一格
   * 求 恰好移除长度为 k 的连续子字符串，从坐标(0,0)开始，按顺序执行剩余移动，返回可达到的不同最终坐标的数量
   *
   * @param s U D L R 组成的字符串，
   * @param k 正整数
   * @return 恰好移除长度为 k 的连续子字符串，从坐标(0,0)开始，按顺序执行剩余移动，返回可达到的不同最终坐标的数量
   */
  def distinctPoints(s: String, k: Int): Int = {
    // (0, 1)(0, 0) 会被计算成一个坐标
    val ans = mutable.Set[String]()
    var x = 0
    var y = 0
    for (i <- s.indices) {
      s(i) match {
        case 'U' => y += 1
        case 'D' => y -= 1
        case 'L' => x -= 1
        case 'R' => x += 1
        case _ =>
      }
    }
    for (i <- s.indices) {
      // 出 删除字符串
      s(i) match {
        case 'U' => y -= 1
        case 'D' => y += 1
        case 'L' => x += 1
        case 'R' => x -= 1
        case _ =>
      }
      // k 子字符串形成
      if (i - k + 1 >= 0) {
        // 更新答案
        ans.add(s"$x, $y")
        // 入
        s(i - k + 1) match {
          case 'U' => y += 1
          case 'D' => y -= 1
          case 'L' => x -= 1
          case 'R' => x += 1
          case _ =>
        }
      }
    }
    ans.size
  }

  def main(args: Array[String]): Unit = {
    println(distinctPoints("LUL", 1) == 2)
    println(distinctPoints("UDLR", 4) == 1)
    println(distinctPoints("UU", 1) == 1)
    println(distinctPoints("UD", 1) == 2)
  }
}
