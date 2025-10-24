package com.oycm.algorithm.a.advance

object Solution_2 {

  /**
   * 1052.爱生气的书店老板
   * https://leetcode.cn/problems/grumpy-bookstore-owner/
   * 当书店老板生气时，那一分钟的客户就会不满意，老板不生气则是满意的
   * 求在经营时间范围内，最多有多少顾客满意
   * 本质就是求 连续子数组中 生气时顾客数量的最大值
   *
   * @param customers 在第 i 分钟开始进入书店的顾客数量, 第 i 分钟结束离开
   * @param grumpy    在第 i 分钟书店老板是否生气 1-生气; 0-不生气
   * @param minutes   老板控制连续不生气的分钟
   * @return
   */
  def maxSatisfied(customers: Array[Int], grumpy: Array[Int], minutes: Int): Int = {
    var ans = 0
    var sum = 0
    var temp = 0

    for (i <- customers.indices) {
      // 入
      if (grumpy(i) == 0) {
        // 不生气
        sum += customers(i)
      } else {
        temp += customers(i)
      }

      if (i - minutes + 1 >= 0) {
        // 更新答案
        ans = Math.max(ans, temp)

        // 出
        val left = i - minutes + 1
        if (grumpy(left) == 1) {
          temp -= customers(left)
        }
      }

    }

    ans + sum
  }

  def main(args: Array[String]): Unit = {
    println(maxSatisfied(Array(1, 0, 1, 2, 1, 1, 7, 5), Array(0, 1, 0, 1, 0, 1, 0, 1), 3) == 16)
    println(maxSatisfied(Array(1), Array(0), 1) == 1)
  }
}
