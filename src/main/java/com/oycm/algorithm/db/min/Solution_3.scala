package com.oycm.algorithm.db.min

object Solution_3 {

  /**
   * 1011. 在 D 天内送达包裹的能力 1725
   * https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/
   *
   * weights(i) 表示 传送带第 i 个包裹的重量，会按给定的顺序往传送带送包裹，一天传送带能传送的包裹不能超过 最大运载重量
   *
   * @param weights 整数数组, 长为 n
   * @param days    天数限制
   * @return 求 在 days 天内，将传送带上所有包裹传送完成的 最低运载能力
   */
  def shipWithinDays(weights: Array[Int], days: Int): Int = {
    /*
    传送带的 运载能力和运载所需天数 负相关
    用一个 运载能力数组 cap[min, max] 天数 值范围 [n, 1]
    降序数组，找到第一个大于等于 days 的 cap(i) 的值
    找到最有一个 小于等于 days 的 cap(i) 的值
     */
    // 最大值取最小 为 n 天
    var l = 0
    // 求和为 1 天
    var r = 0
    for (w <- weights) {
      l = Math.max(w, l)
      r += w
    }

    def getDays(weights: Array[Int], cap: Int): Int = {
      /*var day = 0
      var cur = 0
      for (w <- weights) {
        cur += w
        if (cur == cap) {
          cur = 0
          day += 1
        } else if (cur > cap) {
          cur = w
          day += 1
        }
      }
      if (cur > 0) {
        day += 1
      }*/
      // 天数判断循环优化
      var day = 1
      var cur = 0
      for (w <- weights) {
        if (cur + w > cap) {
          day += 1
          cur = 0
        }
        cur += w
      }

      day
    }
    // 闭区间写法
    while (l <= r) {
      val mid = l + (r - l) / 2
      val day = getDays(weights, mid)
      if (day <= days) {
        r = mid - 1
      } else {
        l = mid + 1
      }
    }

    l
  }

  def main(args: Array[String]): Unit = {
    println(shipWithinDays(Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5))
    println(shipWithinDays(Array(3, 2, 2, 4, 1, 4), 3))
    println(shipWithinDays(Array(1, 2, 3, 1, 1), 4))
    println(shipWithinDays(Array(1, 2, 3, 1, 1), 1))
  }
}
