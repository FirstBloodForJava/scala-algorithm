package com.oycm.algorithm.a.advance

object Solution_1 {

  /**
   * 3679.使库存平衡的最少丢弃次数 1639
   * 注意：丢弃的货物不能在出窗
   * https://leetcode.cn/problems/minimum-discards-to-balance-inventory/
   * 物品管理规则：
   *  - 每个到达的物品可以 保留 或 丢弃
   *  - 对于每一天，考虑天数时间窗口 [max(1, i - w + 1), i]：
   *       - 对于任何的时间窗口，被保留的物品类型，每种类型最多只能出现 w 次
   *       - 如果在第 i 天保留该物品类型会导致其类型在该窗口中出现次数超过 m 次，那么物品必须被丢弃
   *
   * 求 返回满足在每个 w 天窗口中每个类型最多出现 m 次，最少需要丢弃的物品数量
   *
   * @param arrivals 1 <= length <= 100000  整数数组 arrivals(i) 表示第 i 天到达的物品类型(天数表示从 1 开始)
   * @param w        1 <= w < 100000      整数，时间窗口，w天数时间窗口
   * @param m        1 <= m <= w    整数，在 时间窗口内，每种物品类型出现的最多次数
   * @return 满足在每个 w 天窗口中每个类型最多出现 m 次，最少需要丢弃的物品数量
   */
  def minArrivalsToDiscard(arrivals: Array[Int], w: Int, m: Int): Int = {
    arrayHandle(arrivals, w, m)
  }

  private def arrayHandle(arrivals: Array[Int], w: Int, m: Int): Int = {
    var ans = 0
    if (m == arrivals.length || m == w || w == 1) {
      ans
    }
    val time = Array.fill(100001)(0)
    // 丢弃记录数组
    val discard = Array.fill(arrivals.length)(0)
    for (i <- arrivals.indices) {
      // 入
      time(arrivals(i)) += 1
      if (time(arrivals(i)) > m) {
        // 丢弃 货物 更新答案
        time(arrivals(i)) -= 1
        ans += 1
        // 记录丢弃
        discard(i) = 1
      }
      // 窗口形成
      if (i - w + 1 >= 0) {
        val left = i - w + 1
        // 出 要考虑到 丢弃的货物不能出
        if (discard(left) == 0) {
          time(arrivals(left)) -= 1
        }
      }

    }
    ans
  }

  def main(args: Array[String]): Unit = {

    println(minArrivalsToDiscard(Array(1, 2, 1, 3, 1), 4, 2) == 0)
    println(minArrivalsToDiscard(Array(1, 2, 3, 3, 3, 4), 3, 2) == 1)
    println(minArrivalsToDiscard(Array(10, 4, 3, 6, 4, 5, 6, 1, 4), 7, 1) == 2)

    println(minArrivalsToDiscard(Array(7, 3, 9, 9, 7, 3, 5, 9, 7, 2, 6, 10, 9, 7, 9, 1, 3, 6, 2, 4, 6, 2, 6, 8, 4, 8, 2, 7, 5, 6), 10, 1) == 13)

  }
}
